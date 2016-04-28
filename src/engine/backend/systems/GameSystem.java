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
//		Set<Integer> myIDs = new HashSet<Integer>();
		entities.forEach(e -> putEventInMap(myEventMap, event, e));
//		if (myEventMap.containsKey(event.getEventID())) {
//			myEventMap.get(event.getEventID()).addAll(myIDs);
//		} else {
//			myEventMap.put(event.getEventID(), myIDs);
//		}
	}
	
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, IEntity entity) {
		putEventInMap(myEventMap, event, entity);
	}

	private void putEventInMap(Map<String, Set<Integer>> myEventMap, IEvent event, IEntity entity) {
		if (myEventMap.containsKey(event.getEventID())) {
			myEventMap.get(event.getEventID()).add(entity.getID());
		} else {
			myEventMap.put(event.getEventID(), new HashSet<Integer>(entity.getID()));
		}
	}
	
	/**
	 * Filters a collection by their component tag.
	 * @param entities
	 * @param tag
	 * @return A list of IEntities that have the tag specified.
	 */
	public List<IEntity> getEntitiesWithTag(Collection<IEntity> entities, String tag) {
		return entities.stream().filter(e -> e.hasComponent(tag)).collect(Collectors.toList());
	}

}
