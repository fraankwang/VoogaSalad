package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class FireEvent extends Event{
	
	public FireEvent(IEntity firingEntity){
		super.setEntity(firingEntity);
	}
	
}
