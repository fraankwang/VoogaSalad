package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import engine.backend.entities.IEntity;

public abstract class EntityEvent implements IEvent {

	private int entityID;

	public List<String> getEventID(List<String> identifiers) {
		List<String> eventIDs = new ArrayList<String>();
		eventIDs.add(identifiers.get(0) + this.getClass().getSimpleName());
		return eventIDs;
	}

	public Collection<Integer> getEntities() {
		Collection<Integer> entities = new ArrayList<Integer>();
		entities.add(entityID);
		return entities;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
}
