package engine.backend.systems.Events;

public class CriticalPositionEvent extends EntityEvent{
	
	public CriticalPositionEvent(int entityID){
		super.addEntityID(entityID);
	}
	
}
