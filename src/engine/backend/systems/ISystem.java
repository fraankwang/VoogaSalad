package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.IEvent;

public interface ISystem{
	
	public void update(Level myLevel, List<IEvent> myEventList, InGameEntityFactory myEntityFactory, double currentSecond, ResourceBundle myComponentTagResources);
	
}
