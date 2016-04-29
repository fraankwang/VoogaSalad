package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.parser.GlobalParser;
import engine.backend.components.IComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import engine.backend.rules.Rule;

public class AuthoringLevelFactory {
	
	public AuthoringLevelFactory() {
		
	}
	
	public AuthoringLevel createLevel(Map<String, String> data) {
		GameMap map = new GameMap();
		Set<String> entities = new HashSet<String>();
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		String rawEvents = "";
		String rawActions = "";
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
			case "Events":
				rawEvents = data.get(key);
				break;
			case "Actions":
				rawActions = data.get(key);
				break;
			}
		}
		AuthoringLevel level = new AuthoringLevel(name, map, waveDelayTimer);
		level.setEntities(entities);
		level.setSpawnEntities(spawnEntities);
		level.setEvents(parseReadEvents(rawEvents));
		level.setRuleAgenda(createRules(rawEvents, rawActions));
		return level;
	}
	
	private List<Rule> createRules(String rawEvents, String rawActions) {
		List<Rule> ruleAgenda = new ArrayList<Rule>();
		String[] eventslist = rawEvents.split(" ");
		String[] actionslist = rawActions.split(" ");
		
		for (int i = 0; i < eventslist.length; i++) {
			List<IAction> actions = parseActions(actionslist[i]);
			List<String> events = parseEvents(eventslist[i]);
			Rule rule = new Rule(events, actions);
			ruleAgenda.add(rule);
		}
		return ruleAgenda;
	}
	
	private List<IAction> parseActions(String actions) {
		List<IAction> actionAgenda = new ArrayList<IAction>();
		String[] actionlist = actions.split(":");
		for (String str : actionlist) {
			String[] actionInfo = str.split("_");
			String type = actionInfo[0];
			if (type.equals("Entity")) {
				IAction action = new EntityAction(actionInfo[1], actionInfo[2], actionInfo[3], actionInfo[4]);
				actionAgenda.add(action);
			} else if (type.equals("Level")) {
				IAction action = new LevelAction(actionInfo[1], actionInfo[2]);
				actionAgenda.add(action);
			} else {
				System.out.println("Error, type not recognized");
				return null;
			}
			
		}
		return actionAgenda;
	}
	
	private List<String> parseEvents(String events) {
		List<String> eventAgenda = new ArrayList<String>();
		String[] eventlist = events.split(":");
		for (String str : eventlist) {
			String[] eventInfo = str.split("_");
			if (eventInfo.length == 2) {
				String event = eventInfo[0] + eventInfo[1];
				eventAgenda.add(event);
			} else {
				String event = eventInfo[1] + eventInfo[1] + eventInfo[2];
				eventAgenda.add(event);
			}
		}
		return eventAgenda;
	}
	
	private List<List<String>> parseReadEvents(String rawEvents) {
		List<List<String>> events = new ArrayList<List<String>>();
		String[] eventlist = rawEvents.split(" ");
		for (String str : eventlist) {
			String[] eventInfo = str.split(":");
			List<String> list = new ArrayList<String>();
			for (String s : eventInfo) {
				list.add(s);
			}
			events.add(list);
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
