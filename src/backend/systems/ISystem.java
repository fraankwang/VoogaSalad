package backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;

public interface ISystem {
	
	public void update(List<IEntity> entities);
	
}
