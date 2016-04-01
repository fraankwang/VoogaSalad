package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mario_oliver93
 *
 */
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
