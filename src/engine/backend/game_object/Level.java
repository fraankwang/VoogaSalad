/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.game_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;
import engine.backend.rules.EntityAction;

public class Level {

	private final String myName;
	private GameMap map;
	private List<IEntity> entities;
	private Set<String> entityNames;
	private Map<String, String> levelInfo;
	private double levelTimer;
	private double waveDelayTimer;
	private double timer;
	
	public Level(String myName, GameMap map) {
		this.myName = myName;
		this.map = map;
		this.entities = new ArrayList<IEntity>();
		this.levelInfo = new HashMap<String, String>();
		initializeInfo();
	}	

	public Level(String myName) {
		this.entities = new ArrayList<IEntity>();
		this.myName = myName;
	}
	
	private void initializeInfo() {
		levelInfo.put("Type", "Level");
		levelInfo.put("Name", myName);
		levelInfo.put("MapBackgroundImage", map.getMapImage());
		levelInfo.put("MapWidth", map.getMapWidth() + "");
		levelInfo.put("MapHeight", map.getMapHeight() + "");
		levelInfo.put("Paths", map.getPathsInfo());
	}
	
	public String getName(){
		return myName;
	}
	
	public List<IEntity> getEntities() {
		return entities;
	}
	
	public Set<String> getEntityNames() {
		return entityNames;
	}
	
	private String stringEntityNames() {
		StringBuilder sb = new StringBuilder();
		for (String entity : entityNames) {
			sb.append(entity);
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public void setEntityNames(Set<String> entityNames) {
		this.entityNames = entityNames;
		this.levelInfo.put("EntityNames", stringEntityNames());
	}
	
	public GameMap getMap(){
		return map;
	}

	public Map<String, String> getInfo() {
		return levelInfo;
	}

	public void addEntity(IEntity entity) {
		entities.add(entity);
	}

	public void setMap(GameMap map){
		this.map = map;
	}
	
	public void addEntityName(String name) {
		entityNames.add(name);
	}
	
	public void setWaveDelayTimer(double timer) {
		this.waveDelayTimer = timer;
		this.levelInfo.put("WaveDelayTimer", waveDelayTimer + "");
	}
	
	public void setLevelTimer(double timer) {
		this.levelTimer = timer;
		this.levelInfo.put("LevelTimer", levelTimer + "");
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Level) {
			Level temp = (Level) o;
			if (this.myName.equals(temp.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Map<String, List<EntityAction>> getCustomEvents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}
	
}
