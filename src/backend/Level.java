package backend;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
/**
 * 
 * @author mario_oliver93
 *
 */
=======
import backend.game_object.entities.Entity;

>>>>>>> 5096c0afdc311e3f29bb6256e06b9c3d94af500c
public class Level {

	private List<Entity> entities;

	public Level() {
		entities = new ArrayList<Entity>();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	public void addToEntities(Entity entity) {
		entities.add(entity);

	}

}
