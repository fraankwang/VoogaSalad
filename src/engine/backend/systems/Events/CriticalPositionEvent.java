package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalPositionEvent extends Event{
	
	public CriticalPositionEvent(IEntity entityInCriticalPosition){
		super.setEntity(entityInCriticalPosition);
	}
	
}
