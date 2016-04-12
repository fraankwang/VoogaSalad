/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.map.MapObject;


public class Level {

	private List<IEntity> entities;
	private MapObject map = new MapObject();

	public Level(int id) {
		entities = new ArrayList<IEntity>();
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
		entities.add(entity);
	}
	
}
