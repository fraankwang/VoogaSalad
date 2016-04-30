package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.EntityEvent;
import engine.backend.systems.Events.IEvent;

/**
 * System receives user input from user.
 * @author Christine Zhou (clz4)
 *
 */
public class UserInputSystem extends GameSystem{
	private Collection<IEvent> nonMapEvents;
	private Collection<EntityEvent> myMapEvents;
	
	public UserInputSystem() {
		nonMapEvents = new ArrayList<IEvent>();
		myMapEvents = new ArrayList<EntityEvent>();
	}
	
	@Override
	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory,
			double currentSecond) {
		myMapEvents.forEach(event -> addToEventMap(myEventMap, event, event.getEntityIDs()));
		myMapEvents.clear();
	}

	/**
	 * Handles a user event by sort events into events that were defined in user (in map) and
	 * events that were not defined by user(nonMap).
	 * @param event
	 */
	public void handleUserEvent(IEvent event) {
		if (event instanceof EntityEvent) {
			myMapEvents.add((EntityEvent) event);
		} else {
			nonMapEvents.add(event);
		}
	}
	
	public Collection<IEvent> getNonMapEvents() {
		try {
			return nonMapEvents;
		} finally {
			nonMapEvents.clear();			
		}
	}
}