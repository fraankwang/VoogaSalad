package authoring.backend.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.parser.GlobalParser;
import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.rules.Rule;

public class AuthoringLevelFactory {
	
	private RuleFactory ruleFactory;
	
	public AuthoringLevelFactory() {
		this.ruleFactory = new RuleFactory();
	}
	
	public AuthoringLevel createLevel(Map<String, String> data) {
		GameMap map = new GameMap();
		Set<String> entities = new HashSet<String>();
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		String ruleInfo = "";
		String name = "";
		double waveDelayTimer = 0;
		for (String key : data.keySet()) {
			switch (key) {

			case "Paths":
				map.setPaths(getPaths(data.get(key)));
				break;
			case "MapBackgroundImage":
				map.setMapImage(data.get(key));
				break;
			case "MapWidth":
				double width = Double.parseDouble(data.get(key));
				map.setMapWidth(width);
				break;
			case "MapHeight":
				double height = Double.parseDouble(data.get(key));
				map.setMapHeight(height);
				break;
			case "Name":
				name = data.get(key);
				break;
			case "WaveDelayTimer":
				waveDelayTimer = Double.parseDouble(data.get(key));
				break;
			case "Entities":
				String raw = data.get(key);
				entities = getEntityNames(raw);
				break;
			case "SpawnEntities":
				String spawnInfo = data.get(key);
				spawnEntities = createSpawnEntities(spawnInfo);
				break;
			case "Rules":
				ruleInfo = data.get(key);
				break;
			}
		}
		AuthoringLevel level = new AuthoringLevel(name, map, waveDelayTimer);
		level.setEntities(entities);
		if (!spawnEntities.isEmpty()) {
			level.setSpawnEntities(spawnEntities);
		}
		if (!ruleInfo.equals("")) {
			level.setRuleAgenda(createRules(ruleInfo), parseReadEvents(ruleInfo));
		}
		return level;
	}
	
	private List<Rule> createRules(String rawRules) {
		String[] rawRuleSet = rawRules.split(" ");
		String[] events = new String[rawRuleSet.length];
		String[] actions = new String[rawRuleSet.length];
		List<Rule> rules = new ArrayList<Rule>();
		for (int i = 0; i < rawRuleSet.length; i++) {
			String rawRule = rawRuleSet[i];
			String[] ruleParts = rawRule.split(":");
			events[i] = ruleParts[0];
			actions[i] = ruleParts[1];
		}
		for (int i = 0; i < events.length; i++) {
			String eventInfo = events[i];
			String actionInfo = actions[i];
			rules.add(ruleFactory.createRule(parseEvents(eventInfo), parseActions(actionInfo)));
		}
		return rules;
	}
	
	private List<String> parseEvents(String eventInfo) {
		String[] eventData = eventInfo.split("\\+");
		List<String> events = new ArrayList<String>();
		for (String event : eventData) {
			String[] eventElements = event.split("_");
			StringBuilder sb = new StringBuilder();
			if (eventElements.length > 2) {
				String[] eventEntities = new String[2];
				eventEntities[0] = eventElements[0];
				eventEntities[1] = eventElements[1];
				Arrays.sort(eventEntities);
				for (String str : eventEntities) {
					sb.append(str);
				}
				sb.append(eventElements[2]);
				events.add(sb.toString());
			} else {
				for (String str : eventElements) {
					sb.append(str);
				}
				events.add(sb.toString());
			}			
		}
		return events;
	}
	
	private List<List<String>> parseActions(String actionInfo) {
		String[] actionData = actionInfo.split("\\+");
		List<List<String>> actions = new ArrayList<List<String>>();
		for (String action : actionData) {
			List<String> elements = new ArrayList<String>();
			String[] actionElements = action.split("_");
			for (String str : actionElements) {
				elements.add(str);
			}
			actions.add(elements);
		}
		return actions;
	}
	
	private List<String> parseReadEvents(String ruleInfo) {
		List<String> events = new ArrayList<String>();
		for (String rulePair : ruleInfo.split(" ")) {
			String eventSet = rulePair.split(":")[0];
			events.add(eventSet);
		}
		return events;
	}
	
	private List<AuthoringEntity> createSpawnEntities(String info) {
		Map<String, String[]> spawnInfo = GlobalParser.spawnParse(info);
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		for (String key : spawnInfo.keySet()) {
			int pathID = Integer.parseInt(key);
			String entityName = "Spawn" + key;
			String genre = "Spawner";
			String[] spawnObjectInfo = spawnInfo.get(key);
			List<Spawn> spawns = new ArrayList<Spawn>();
			for (String spawn : spawnObjectInfo) {
				String[] list = spawn.split("\\.");
				String entity = list[0];
				int wave = Integer.parseInt(list[1]);
				int numSpawn = Integer.parseInt(list[2]);
				int rate = Integer.parseInt(list[3]);
				spawns.add(new Spawn(entity, rate, wave, numSpawn));
			}
			IComponent spawner = new SpawnerComponent(spawns, pathID);
			AuthoringEntity spawnEntity = new AuthoringEntity(entityName, genre);
			spawnEntity.addComponent(spawner);
			spawnEntity.addComponent(new DisplayComponent());
			spawnEntity.addComponent(new PositionComponent());
			spawnEntities.add(spawnEntity);
		}
		return spawnEntities;
	}
		
	private Set<String> getEntityNames(String str) {
		String[] names = str.split(" ");
		Set<String> entityNames = new HashSet<String>();
		for (int i = 0; i < names.length; i++) {
			entityNames.add(names[i]);
		}
		return entityNames;
	}
	
	private Path[] getPaths(String str) {
		Map<String, String[]> temp = GlobalParser.pathParse(str);
		Path[] paths = new Path[temp.size()];
		int count = 0;
		for (String key : temp.keySet()) {
			paths[count] = createPath(key, temp.get(key));
			count++;
		}
		return paths;
	}
	
	private Path createPath(String ID, String[] curves) {
		List<BezierCurve> temp = new ArrayList<BezierCurve>();
		for (String curve : curves) {
			temp.add(createCurve(curve));
		}
		int pathID = Integer.parseInt(ID);
		return new Path(temp, pathID);
	}
	
	private BezierCurve createCurve(String curve) {
		String[] raw = curve.split(",");
		double[] points = new double[raw.length * 2];
		int count = 0;
		for (String point : raw) {
			String[] xy = point.split("-");
			double x = Double.parseDouble(xy[0]);
			points[count] = x;
			count++;
			double y = Double.parseDouble(xy[1]);
			points[count] = y;
			count++;
		}
		return new BezierCurve(points);
	}
	
}
