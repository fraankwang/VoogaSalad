/**
 * 
 * @author mario_oliver93
 *
 */

package game_object.copy;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.map.MapObject;


public class Level {

	private List<IEntity> entities;
	private int id;
	private MapObject map = new MapObject();

	public Level(int id) {
		entities = new ArrayList<IEntity>();
		this.id = id;
	}
	
	public Level(){
		entities = new ArrayList<IEntity>();
		
	}
	
	public int getId(){
		return id;
	}

	public List<IEntity> getEntities() {
		return entities;
	}
	
	public MapObject getMap(){
		return map;
	}

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";// +
													// entities.get(0).toString();
	}

	public void addToEntities(Entity entity) {
		entities.add(entity);

	}
	
}
