package engine.backend.systems;

import java.util.List;

import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;

public interface ISystem {
	
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory);
	
}
