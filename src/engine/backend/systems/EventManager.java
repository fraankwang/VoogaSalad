package engine.backend.systems;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.rules.Action;
import engine.backend.systems.Events.CollisionEvent;
import engine.backend.systems.Events.DeathEvent;
import engine.backend.systems.Events.Event;
import engine.backend.systems.Events.FireEvent;
import engine.backend.systems.Events.IEvent;


public class EventManager implements Observer {

	private Level myCurrentLevel;
	ResourceBundle myComponentTagResources;

	private Map<String, List<Action>> myCustomEvents;

	public EventManager(Level currentLevel, ResourceBundle myComponentTagResources) {
		this.myComponentTagResources = myComponentTagResources;
		myCustomEvents = currentLevel.getCustomEvents();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		String eventType = arg.getClass().getSimpleName();
	}

	public void setCustomEvents(Map<String, List<Action>> myCustomEvents) {
		this.myCustomEvents = myCustomEvents;
	}

	public void handleCustomEvent(IEvent myEvent) {
		List<Action> myActions = myCustomEvents.get(myEvent.getEventID()[0]); // handle
																				// both
		Collection<IEntity> myEntities = myEvent.getEntities();
	}

	public void handleAddEntity(IEvent myEvent) { 
		myEvent.getEntities().forEach(e -> myCurrentLevel.addToEntities(e));
	}
}
