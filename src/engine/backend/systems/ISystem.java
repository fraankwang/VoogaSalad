package engine.backend.systems;

import java.util.List;

import engine.backend.entities.IEntity;

public interface ISystem {
	
	public void update(List<IEntity> entities);
	
}
