package engine.backend.systems.Events;

import java.util.Collections;
import java.util.List;

public class CollisionEvent extends EntityEvent {

	private int entityID1;
	private int entityID2;
	private String eventID;

	public CollisionEvent(int entityID1, int entityID2) {
		super.addEntityID(entityID1);
		super.addEntityID(entityID2);
	}

	@Override
	public void setEventID(List<String> identifiers) {
		Collections.sort(identifiers);
		eventID = (identifiers.get(0) + "-" + identifiers.get(1) + "-" + this.getClass().getSimpleName());
	}

	@Override
	public String getEventID() {
		return eventID;
	}
}
