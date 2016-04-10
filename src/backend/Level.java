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

	public Level() {
		entities = new ArrayList<IEntity>();
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

}
