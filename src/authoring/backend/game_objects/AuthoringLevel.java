package authoring.backend.game_objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;
import engine.backend.rules.IAction;
import engine.backend.rules.Rule;

public class AuthoringLevel {
	
	private String myName;
	private GameMap myMap;
	private double waveDelayTimer;
	
	private Set<String> entities;
	private Map<String, String> myInfo;
	private List<AuthoringEntity> spawnEntities;
	private List<Rule> ruleAgenda;
	
	public AuthoringLevel(String myName, GameMap myMap, double waveDelayTimer) {
		this.myName = myName;
		this.myMap = myMap;
		this.waveDelayTimer = waveDelayTimer;
		this.entities = new HashSet<String>();
		this.myInfo = new HashMap<String, String>();
		this.spawnEntities = new ArrayList<AuthoringEntity>();
		initializeInfo();
	}
	
	public AuthoringLevel(Level level) {
		this.myName = level.getName();
		this.myMap = level.getMap();
		this.waveDelayTimer = level.getWaveDelayTimer();
		this.myInfo = new HashMap<String, String>();
		setUpSpawnEntities(level);
		setUpRuleAgenda(level);
		initializeInfo();
	}
	
	private void initializeInfo() {
		myInfo.put("Type", "Level");
		myInfo.put("Name", myName);
		myInfo.put("WaveDelayTimer", waveDelayTimer + "");
		myInfo.put("MapBackgroundImage", myMap.getMapImage());
		myInfo.put("MapWidth", myMap.getMapWidth() + "");
		myInfo.put("MapHeight", myMap.getMapHeight() + "");
		myInfo.put("Paths", myMap.getPathsInfo());
	}
	
	private void setUpSpawnEntities(Level level) {
		List<IEntity> entities = level.getAuthoredEntities();
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		for (IEntity entity : entities) {
			if (entity.hasComponent("SpawnerComponent")) {
				AuthoringEntity authoringEntity = new AuthoringEntity(entity);
				spawnEntities.add(authoringEntity);
			}
		}
		setSpawnEntities(spawnEntities);
	}
	
	private void setUpRuleAgenda(Level level) {
		List<Rule> ruleAgenda = level.getRuleAgenda();
		List<String> events = new ArrayList<String>();
		for (Rule rule : ruleAgenda) {
			List<String> ruleEvents = (List<String>) rule.getEvents();
			events.addAll(ruleEvents);
		}
		setRuleAgenda(level.getRuleAgenda(), events);
	}
	
	public Set<String> getEntities() {
		return entities;
	}
	
	public Map<String, String> getInfo() {
		return myInfo;
	}
	
	public String getName() {
		return myName;
	}
	
	public GameMap getMap() {
		return myMap;
	}
	
	public double getWaveDelayTimer() {
		return waveDelayTimer;
	}
	
	public List<AuthoringEntity> getSpawnEntities() {
		return spawnEntities;
	}
	
	public void setEntities(Set<String> entities) {
		this.entities = entities;
		this.myInfo.put("EntityNames", getEntityNames());
	}
	
	public void setSpawnEntities(List<AuthoringEntity> spawnEntities) {
		this.spawnEntities = spawnEntities;
		this.myInfo.put("SpawnEntities", getSpawnEntityInfo());
	}
	
	private String getSpawnEntityInfo() {
		if (spawnEntities.isEmpty()) {
			return "empty";
		} else {
			StringBuilder sb = new StringBuilder();
			for (AuthoringEntity entity : spawnEntities) {
				Map<String, String> info = entity.getInfo();
				String spawnInfo = info.get("SpawnerComponent");
				sb.append(spawnInfo);
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
	
	private String getEntityNames() {
		if (entities.isEmpty()) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for (String entity : entities) {
				sb.append(entity);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
	
	public void setRuleAgenda(List<Rule> ruleAgenda, List<String> events) {
		this.ruleAgenda = ruleAgenda;
		this.myInfo.put("Rules", getRuleAgendaInfo(events));
	}
	
	private String getRuleAgendaInfo(List<String> events) {
		StringBuilder sb = new StringBuilder();
		List<String> actions = new ArrayList<String>();
		for (Rule rule : ruleAgenda) {
			StringBuilder sb2 = new StringBuilder();
			for (IAction action : rule.getActions()) {
				sb2.append(action.toString());
				sb2.append("+");
			}
			sb2.deleteCharAt(sb2.length() - 1);
			actions.add(sb2.toString());
		}
		for (int i = 0; i < actions.size(); i++) {
			StringBuilder sb2 = new StringBuilder();
			String event = events.get(i);
			String action = actions.get(i);
			sb2.append(event);
			sb2.append(":");
			sb2.append(action);
			sb.append(sb2.toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	
	public List<Rule> getRuleAgenda() {
		return ruleAgenda;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof AuthoringLevel) {
			AuthoringLevel level = (AuthoringLevel) o;
			if (this.myName.equals(level.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
