package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;

public interface ISystem{
	
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources);
	
}
