package backend.systems;

import java.util.List;

import backend.game_object.entities.Entity;

public interface Systems {
	
	public void update(List<Entity> entities);
	
}
