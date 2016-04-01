package backend;

import java.util.ArrayList;
import java.util.List;

import backend.game_object.entities.Entity;

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
		return "Level [entities=" + entities + "] ";// +
													// entities.get(0).toString();
	}

	public void addToEntities(Entity entity) {
		entities.add(entity);

	}

}
