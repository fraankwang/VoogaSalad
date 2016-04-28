package engine.backend.systems.Events;

public class CriticalHealthEvent extends EntityEvent{
	
	public CriticalHealthEvent(int entityID){
		super.addEntityID(entityID);
	}
}
