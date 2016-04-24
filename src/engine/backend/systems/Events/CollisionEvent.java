package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import engine.backend.entities.IEntity;

public class CollisionEvent extends EntityEvent {

	private int entityID1;
	private int entityID2;

	public CollisionEvent(int entityID1, int entityID2) {
		this.entityID1 = entityID1;
		this.entityID2 = entityID2;
	}

	@Override
	public Collection<Integer> getEntities() {
		Collection<Integer> collidingEntities = new ArrayList<Integer>();
		collidingEntities.add(entityID1);
		collidingEntities.add(entityID2);
		return collidingEntities;
	}

	@Override
	public List<String> getEventID(List<String> identifiers) {
		List<String> eventIDs = new ArrayList<String>();
		eventIDs.add(identifiers.get(0) + identifiers.get(1) + this.getClass().getSimpleName());
		eventIDs.add(identifiers.get(1) + identifiers.get(0) + this.getClass().getSimpleName());
		return eventIDs;
	}
}
