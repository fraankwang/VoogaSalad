/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.game_object;

import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;
import engine.backend.rules.EntityAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Level {

	private int myID;
	private String myParentModeName;
	private GameMap map;
	private Map<Integer, IEntity> entities;
	private Set<String> entityNames;
	private Map<String, String> levelInfo;
	private double levelTimer;
	private double waveDelayTimer;
	private double timer;


	public Level(int myID, GameMap map) {
		this.myID = myID;
		this.map = map;
		initializeInfo();
	}
	
	private void initializeInfo() {
		levelInfo.put("Type", "Level");
		levelInfo.put("MapBackgroundImage", map.getMapImage());
		levelInfo.put("LevelTimer", levelTimer + "");
		levelInfo.put("WaveDelayTimer", waveDelayTimer + "");
	}


	

	
	public Set<String> getEntityNames() {
		return entityNames;
	}

	public Level(int myID) {
		this.entities = new HashMap<Integer, IEntity>();
		this.myID = myID;
	}

	public int getId() {
		return myID;
	}

	public Map<Integer, IEntity> getEntities() {
		return entities;
	}

	public GameMap getMap() {
		return map;
	}


	public Map<String, String> getInfo() {
		return levelInfo;
	}


	public void setMap(GameMap map){
		this.map = map;
	}
	

	public void addToEntities(IEntity entity) {
		entity.setLevelID(myID);
		entities.put(entity.getID(), entity);
	}

	public void setModeName(String modeID) {
		this.myParentModeName = modeID;
	}

	public String getModeID() {
		return myParentModeName;

	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Level) {
			Level temp = (Level) o;
			if (this.getId() == temp.getId()) {
				return true;
			} else return false;
		}else return false;
	}

	public Map<String, List<EntityAction>> getCustomEvents() {
		// TODO Auto-generated method stub
		return null;
	}


}
