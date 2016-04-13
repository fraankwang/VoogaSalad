package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import authoring.backend.factories.InGameEntityFactory;
import engine.backend.entities.IEntity;

public interface ISystem {
	
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources);
	
}
