package engine.backend.systems.Events;

public class DeathEvent extends EntityEvent{
	
	public DeathEvent(int entityID){
		super.setEntityID(entityID);
	}
	
}
