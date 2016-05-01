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
	 * 
	 * @param myEventMap
	 * @param event
	 * @param collection
	 */
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, Collection<IEntity> entities) {
		if (event != null) {
			entities.forEach(e -> putEventInMap(myEventMap, event, e.getID()));
		}
	}

	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, List<Integer> entityIDs) {
		if (event != null) {
			entityIDs.forEach(id -> putEventInMap(myEventMap, event, id));
		}
	}

	/**
	 * Adds an event to the map of eventIDs to entities with those events.
	 * 
	 * @param myEventMap
	 *            The map to put the event into.
	 * @param event
	 * @param entityID
	 *            The entity ID corresponding with the event.
	 */
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, int entityID) {
		if (event != null) {
			putEventInMap(myEventMap, event, entityID);
		}
	}

	/**
	 * Adds an event to the map of eventIDs to entities with those events.
	 * 
	 * @param myEventMap
	 * @param event
	 * @param entity
	 */
	public void addToEventMap(Map<String, Set<Integer>> myEventMap, IEvent event, IEntity entity) {
		if (event != null) {
			putEventInMap(myEventMap, event, entity.getID());
		}
	}

	private void putEventInMap(Map<String, Set<Integer>> myEventMap, IEvent event, int entityID) {
		if (myEventMap.containsKey(event.getEventID())) {
			myEventMap.get(event.getEventID()).add(entityID);
		} else {
			Set<Integer> set = new HashSet<Integer>();
			set.add(entityID);
			myEventMap.put(event.getEventID(), set);
		}
	}

	/**
	 * Filters a list of entities given with the give string of tags. Returns
	 * the filtered list of entities.
	 * 
	 * @param entities
	 * @param tag
	 * @return
	 */
	public List<IEntity> getEntitiesWithTag(Collection<IEntity> entities, String tag) {
		return entities.stream().filter(e -> e.hasComponent(tag)).collect(Collectors.toList());
	}

}
