package engine.backend.systems.Events;

public class UpdatedDisplayEvent extends EntityEvent {

	public UpdatedDisplayEvent(int entityID) {
		super.addEntityID(entityID);
	}

}
