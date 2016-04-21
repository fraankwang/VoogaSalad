package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class FireEvent extends EntityEvent{
	
	public FireEvent(IEntity firingEntity){
		super.setEntity(firingEntity);
	}
	
}
