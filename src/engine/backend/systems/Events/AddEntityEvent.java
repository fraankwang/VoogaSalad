package engine.backend.systems.Events;

public class AddEntityEvent extends EntityEvent{
	
	public AddEntityEvent(int entityID){
		super.setEntityID(entityID);
	}
}
