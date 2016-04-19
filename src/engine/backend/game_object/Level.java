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

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;
import engine.backend.rules.Action;


public class Level {

	private final String myName;
	private GameMap map;
	private List<IEntity> entities;
	private Set<String> entityNames;
	private Map<String, String> levelInfo;
	private double levelTimer;
	private double waveDelayTimer;
	private double timer;
	
	public Level(String myName, GameMap map, Set<String> entityNames) {
		this.myName = myName;
		this.map = map;
		this.entities = new ArrayList<IEntity>();
		this.levelInfo = new HashMap<String, String>();
		this.entityNames = entityNames;
		initializeInfo();
	}
	
	private void initializeInfo() {
		levelInfo.put("Type", "Level");
		levelInfo.put("MapBackgroundImage", map.getMapImage());
		levelInfo.put("LevelTimer", levelTimer + "");
		levelInfo.put("WaveDelayTimer", waveDelayTimer + "");
	}

	public Level(String myName) {
		this.entities = new ArrayList<IEntity>();
		this.myName = myName;
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
	
	public GameMap getMap(){
		return map;
	}
	

	public Map<String, String> getInfo() {
		return levelInfo;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void setMap(GameMap map){
		this.map = map;
	}
	
	public void addEntityName(String name) {
		entityNames.add(name);
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

	public Map<String, List<Action>> getCustomEvents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}
	
}
