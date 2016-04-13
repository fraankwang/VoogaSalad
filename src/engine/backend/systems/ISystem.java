package engine.backend.systems;

import java.util.List;

import authoring.backend.factories.InGameEntityFactory;
import engine.backend.entities.IEntity;

public interface ISystem {
	
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory);
	
}
