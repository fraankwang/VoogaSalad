/**
 * 
 * @author mario_oliver93
 *
 */
package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mario_oliver93
 *
 */
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;
import backend.game_object.map.Mapp;


public class Level {

	private List<IEntity> entities;
	private Mapp map = new Mapp();
	private int myId;
	private int myParentId;

	public Level(int id) {
		entities = new ArrayList<IEntity>();
		this.myId = id;
	}

	public List<IEntity> getEntities() {
		return entities;
	}
	
	public Mapp getMap(){
		return map;
	}

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	public void addToEntities(Entity entity) {
		entities.add(entity);
	}
	
	public int getId(){
		return this.myId;
	}
	
	public int getParentId(){
		return this.myParentId;
	}

}
