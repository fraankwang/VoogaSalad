package backend.systems;

import java.util.List;

import backend.game_object.entities.IEntity;

public interface ISystem {
	
	public void update(List<IEntity> entities);
	
}
