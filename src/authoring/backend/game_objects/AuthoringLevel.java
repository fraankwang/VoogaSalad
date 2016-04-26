package authoring.backend.game_objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.map.GameMap;

public class AuthoringLevel {
	
	private String myName;
	private GameMap myMap;
	private double levelTimer;
	private double waveDelayTimer;
	
	private Set<String> entities;
	private Map<String, String> myInfo;
	private List<AuthoringEntity> spawnEntities;
	
	public AuthoringLevel(String myName, GameMap myMap, double levelTimer, double waveDelayTimer) {
		this.myName = myName;
		this.myMap = myMap;
		this.levelTimer = levelTimer;
		this.waveDelayTimer = waveDelayTimer;
		this.entities = new HashSet<String>();
		this.myInfo = new HashMap<String, String>();
		this.spawnEntities = new ArrayList<AuthoringEntity>();
		initializeInfo();
	}
	
	private void initializeInfo() {
		myInfo.put("Type", "Level");
		myInfo.put("Name", myName);
		myInfo.put("LevelTimer", levelTimer + "");
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
	
	public void setEntities(Set<String> entities) {
		this.entities = entities;
		this.myInfo.put("EntityNames", getEntityNames());
	}
	
	public void setSpawnEntities(List<AuthoringEntity> spawnEntities) {
		this.spawnEntities = spawnEntities;
		this.myInfo.put("SpawnEntities", getSpawnEntityInfo());
	}
	
	private String getSpawnEntityInfo() {
		StringBuilder sb = new StringBuilder();
		for (AuthoringEntity entity : spawnEntities) {
			Map<String, String> info = entity.getInfo();
			String spawnInfo = info.get("SpawnComponent");
			sb.append(spawnInfo);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	private String getEntityNames() {
		StringBuilder sb = new StringBuilder();
		for (String entity : entities) {
			sb.append(entity);
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
