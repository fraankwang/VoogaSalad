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
	private int myParentModeID;
	private GameMap map;

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

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	public void addToEntities(Entity entity) {
		entity.setLevelID(myID);
		entities.add(entity);
	}
	
	public void setModeID(int modeID) {
		this.myParentModeID = modeID;
	}
	
	public int getModeID() {
		return myParentModeID;
	}
	
}
