/**
 * 
 * @author mario_oliver93
 *
 */

package backend;

import java.util.ArrayList;
import java.util.List;
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;
import backend.game_object.map.MapObject;


public class Level {

	private List<IEntity> entities;
	private MapObject map = new MapObject();

	public Level() {
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
		return "Level [entities=" + entities + "] ";// +
													// entities.get(0).toString();
	}

	public void addToEntities(Entity entity) {
		entities.add(entity);

	}

}
