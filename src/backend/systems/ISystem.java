package backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import backend.game_object.entities.Entity;

public interface ISystem {
	
	public void update(List<Entity> entities);
	
}
