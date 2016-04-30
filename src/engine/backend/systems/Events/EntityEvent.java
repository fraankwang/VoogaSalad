package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EntityEvent implements IEvent {

	private List<Integer> entityIDs = new ArrayList<Integer>();
	private String eventID;

	/**
	 * Sets the event id based on a list of identifiers given.
	 * 
	 * @param identifiers
	 */
	public void setEventID(List<String> identifiers) {
		this.eventID = (identifiers.get(0) + this.getClass().getSimpleName());
	}

	/**
	 * Sets the event id based on a single identifier.
	 * 
	 * @param indentifier
	 */
	public void setEventID(String indentifier) {
		this.eventID = (indentifier + this.getClass().getSimpleName());
	}

	/**
	 * 
	 * @return A collection of entity IDs of entities associated with this
	 *         event.
	 */
	public List<Integer> getEntityIDs() {
		return entityIDs;
	}
	
	/**
	 * 
	 * @return First entity in list if the list is not empty, otherwise returns -1;
	 */
	public int getFirstEntityID() {
		return entityIDs.size() != 0 ? entityIDs.get(0) : -1;
	}

	/**
	 * Adds an entity ID.
	 * 
	 * @param entityID
	 */
	public void addEntityID(int entityID) {
		this.entityIDs.add(entityID);
	}

	/**
	 * Adds a list of entity IDs associated with this event.
	 * 
	 * @param entityIDs
	 */
	public void addEntityID(Collection<Integer> entityIDs) {
		entityIDs.forEach(i -> this.entityIDs.add(i));
	}

	public String getEventID() {
		return eventID;
	}
}
