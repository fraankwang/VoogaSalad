package engine.backend.systems.Events;

public class FireEvent extends EntityEvent {

	public FireEvent(int entityID) {
		super.addEntityID(entityID);
	}

}
