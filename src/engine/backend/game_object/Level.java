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
import engine.backend.map.MapObject;


public class Level {

	private List<IEntity> entities;
	private int myID;
	private String myParentModeName;
	private MapObject map = new MapObject();

	public Level(int id) {
		entities = new ArrayList<IEntity>();
		this.myID = id;
	}
	
	public int getId(){
		return myID;
	}

	public List<IEntity> getEntities() {
		return entities;
	}
	
	public MapObject getMap(){
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
	
	public void setModeName(String modeID) {
		this.myParentModeName = modeID;
	}
	
	public String getModeID() {
		return myParentModeName;
	}
	
}
