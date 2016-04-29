package authoring.backend.game_objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private List<List<String>> events;
	
	public AuthoringLevel(String myName, GameMap myMap, double waveDelayTimer) {
		this.myName = myName;
		this.myMap = myMap;
		this.waveDelayTimer = waveDelayTimer;
		this.entities = new HashSet<String>();
		this.myInfo = new HashMap<String, String>();
		this.spawnEntities = new ArrayList<AuthoringEntity>();
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
	
	public void setRuleAgenda(List<Rule> ruleAgenda) {
		this.ruleAgenda = ruleAgenda;
		String[] ruleAgendaInfo = getRuleAgendaInfo();
		this.myInfo.put("Events", ruleAgendaInfo[0]);
		this.myInfo.put("Actions", ruleAgendaInfo[1]);
	}
	
	private String[] getRuleAgendaInfo() {
		String[] info = new String[2];
		info[0] = getEventsInfo();
		info[1] = getActionsInfo();
		return info;
	}
	
	public void setEvents(List<List<String>> events) {
		this.events = events;
	}
	
	private String getEventsInfo() {
		StringBuilder sb = new StringBuilder();
		for (List<String> list : events) {
			StringBuilder sb2 = new StringBuilder();
			for (String s : list) {
				sb2.append(s);
				sb2.append(":");
			}
			sb2.deleteCharAt(sb2.length() - 1);
			sb.append(sb2.toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	private String getActionsInfo() {
		StringBuilder sb = new StringBuilder();
		for (Rule rule : ruleAgenda) {
			List<IAction> actions = (List<IAction>) rule.getActions();
			StringBuilder sb2 = new StringBuilder();
			for (IAction action : actions) {
				sb2.append(action.toString());
				sb2.append(":");
			}
			sb2.deleteCharAt(sb2.length() - 1);
			sb.append(sb2.toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
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
