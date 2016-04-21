package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class EndOfPathEvent extends EntityEvent{
	
	public EndOfPathEvent(int entityID){
		super.setEntityID(entityID);
	}
	
}
