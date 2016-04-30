package engine.backend.systems.Events;

public class OutOfMapEvent extends EntityEvent {

	public OutOfMapEvent(int entityID) {
		super.addEntityID(entityID);
	}

}
