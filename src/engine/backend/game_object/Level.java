/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;


public class Level {

	private List<IEntity> entities;
	private int myID;
	private String myParentModeName;
	private GameMap map;
	
	public Level(int myID, GameMap map) {
		this.myID = myID;
		this.map = map;
	}

	public Level(int myID) {
		this.entities = new ArrayList<IEntity>();
		this.myID = myID;
	}
	
	public int getId(){
		return myID;
	}

	public List<IEntity> getEntities() {
		return entities;
	}
	
	public GameMap getMap(){
		return map;
	}
	
	public void setMap(GameMap map){
		this.map = map;
	}

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	public void addToEntities(IEntity entity) {
		entity.setLevelID(myID);
		entities.add(entity);
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
			if (this.myID == temp.myID) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
