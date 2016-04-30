package engine.backend.systems;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;

import engine.backend.entities.IEntity;
import engine.backend.systems.Events.IEvent;

public abstract class GameSystem extends Observable implements ISystem {

	/**
	 * Adds an event to the map of eventIDs to entities with those events.
	 * @param myEventMap
	 * @param event
	 * @param entities
	 */
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, Collection<IEntity> entities) {
		if (event != null) {
			entities.forEach(e -> putEventInMap(myEventMap, event, e));
		}
		entities.forEach(e -> putEventInMap(myEventMap, event, e));
	}
	
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, IEntity entity) {
		if (event != null) {
			putEventInMap(myEventMap, event, entity);
		}
	}

	private void putEventInMap(Map<String, Set<Integer>> myEventMap, IEvent event, IEntity entity) {
		if (myEventMap.containsKey(event.getEventID())) {
			myEventMap.get(event.getEventID()).add(entity.getID());
		} else {
			Set<Integer> set = new HashSet<Integer>();
			set.add(entity.getID());
			myEventMap.put(event.getEventID(), set);
		}
	}

	public List<IEntity> getEntitiesWithTag(Collection<IEntity> entities, String tag) {
		return entities.stream().filter(e -> e.hasComponent(tag)).collect(Collectors.toList());
	}

}
