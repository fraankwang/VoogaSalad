package engine.backend.systems.Events;

public class EndOfPathEvent extends EntityEvent{
	
	public EndOfPathEvent(int entityID){
		super.addEntityID(entityID);
	}
	
}
