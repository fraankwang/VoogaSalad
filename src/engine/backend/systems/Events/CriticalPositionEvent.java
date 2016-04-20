package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalPositionEvent extends EntityEvent{
	
	public CriticalPositionEvent(IEntity entityInCriticalPosition){
		super.setEntity(entityInCriticalPosition);
	}
	
}
