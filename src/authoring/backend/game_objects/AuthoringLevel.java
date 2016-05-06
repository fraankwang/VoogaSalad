//This isn't part of the masterpiece, but I refactored this class to become subclassed by AuthoringObject.
//This is to show how the authoring environment objects have changed following the masterpiece. 

package authoring.backend.game_objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;
import engine.backend.rules.IAction;
import engine.backend.rules.Rule;

public class AuthoringLevel extends AuthoringObject {
	
	private static final String WAVE_DELAY = "WaveDelayTimer";
	private static final String MAP_IMAGE = "MapBackgroundImage";
	private static final String MAP_WIDTH = "MapWidth";
	private static final String MAP_HEIGHT = "MapHeight";
	private static final String PATH = "Paths";
	private static final String ENTITIES = "EntityNames";
	private static final String SPAWN = "SpawnEntities";
	private static final String RULE = "Rules";
	private static final String SPAWN_COMP = "SpawnerComponent";

	private GameMap myMap;
	private double waveDelayTimer;

	private Set<String> entities;
	private List<AuthoringObject> spawnEntities;
	private List<Rule> ruleAgenda;

	public AuthoringLevel(String myName, GameMap myMap, double waveDelayTimer) {
		super(myName);
		this.myMap = myMap;
		this.waveDelayTimer = waveDelayTimer;
		this.entities = new HashSet<String>();
		this.spawnEntities = new ArrayList<AuthoringObject>();
	}

	public AuthoringLevel(Level level) {
		super(level.getName());
		this.myMap = level.getMap();
		this.waveDelayTimer = level.getWaveDelayTimer();
		this.spawnEntities = new ArrayList<AuthoringObject>();
		this.entities = new HashSet<String>();
		setUpEntities(level);
		setUpSpawnEntities(level);
		setRuleAgenda(level.getRuleAgenda());
	}
	
	@Override
	protected void initializeSpecificInfo() {
		getInfo().put(WAVE_DELAY, waveDelayTimer + EMPTY);
		getInfo().put(MAP_IMAGE, myMap.getMapImage());
		getInfo().put(MAP_WIDTH, myMap.getMapWidth() + EMPTY);
		getInfo().put(MAP_HEIGHT, myMap.getMapHeight() + EMPTY);
		getInfo().put(PATH, myMap.getPathsInfo());
		getInfo().put(ENTITIES, getEntityNames());
		getInfo().put(SPAWN, getSpawnEntityInfo());
		getInfo().put(RULE, getRuleAgendaInfo());
	}
	
	private void setUpEntities(Level level) {
		List<IEntity> entities = level.getAuthoredEntities();
		Set<String> entityNames = new HashSet<String>();
		for (IEntity entity : entities) {
			this.entities.add(entity.getName());
		}
		setEntities(entityNames);
	}

	private void setUpSpawnEntities(Level level) {
		List<IEntity> entities = level.getAuthoredEntities();
		List<AuthoringObject> spawnEntities = new ArrayList<AuthoringObject>();
		for (IEntity entity : entities) {
			if (entity.hasComponent(SPAWN_COMP)) {
				AuthoringObject authoringEntity = new AuthoringEntity(entity);
				spawnEntities.add(authoringEntity);
			}
		}
		setSpawnEntities(spawnEntities);
	}

	public Set<String> getEntities() {
		return entities;
	}

	public GameMap getMap() {
		return myMap;
	}

	public double getWaveDelayTimer() {
		return waveDelayTimer;
	}

	public List<AuthoringObject> getSpawnEntities() {
		return spawnEntities;
	}

	public void setEntities(Set<String> entities) {
		this.entities = entities;
		getInfo().put(ENTITIES, getEntityNames());
	}

	public void setSpawnEntities(List<AuthoringObject> spawnEntities) {
		this.spawnEntities = spawnEntities;
		getInfo().put(SPAWN, getSpawnEntityInfo());
	}

	private String getSpawnEntityInfo() {
		if (spawnEntities.isEmpty()) {
			return EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			for (AuthoringObject entity : spawnEntities) {
				Map<String, String> info = entity.getInfo();
				String spawnInfo = info.get(SPAWN_COMP);
				sb.append(spawnInfo);
				sb.append(COMMA_SPLIT);
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}

	private String getEntityNames() {
		if (entities.isEmpty()) {
			return EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			for (String entity : entities) {
				sb.append(entity);
				sb.append(SPACE_SPLIT);
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}

	public void setRuleAgenda(List<Rule> ruleAgenda) {
		this.ruleAgenda = ruleAgenda;
		getInfo().put(RULE, getRuleAgendaInfo());
	}

	private String getRuleAgendaInfo() {
		if (ruleAgenda.isEmpty()) {
			return EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			List<String> actions = new ArrayList<String>();
			List<String> events = new ArrayList<String>();
			for (Rule rule : ruleAgenda) {
				StringBuilder sb2 = new StringBuilder();
				for (IAction action : rule.getActions()) {
					sb2.append(action.toString());
					sb2.append(PLUS_SPLIT);
				}
				sb2.deleteCharAt(sb2.length() - 1);
				actions.add(sb2.toString());
				events.addAll(rule.getEvents());
			}
			for (int i = 0; i < actions.size(); i++) {
				StringBuilder sb2 = new StringBuilder();
				String event = events.get(i);
				String action = actions.get(i);
				sb2.append(event);
				sb2.append(SEMICOLON_SPLIT);
				sb2.append(action);
				sb.append(sb2.toString());
				sb.append(SPACE_SPLIT);
			}
			sb.deleteCharAt(sb.length() - 1);

			return sb.toString();
		}
	}

	public List<Rule> getRuleAgenda() {
		return ruleAgenda;
	}

}
