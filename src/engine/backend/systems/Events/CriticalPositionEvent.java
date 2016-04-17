package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalPositionEvent extends Event{
	
	private IEntity entityInCriticalPosition;
	
	public CriticalPositionEvent(IEntity entityInCriticalPosition){
		this.entityInCriticalPosition = entityInCriticalPosition;
	}
	
	public IEntity getEntityInCriticalPosition(){
		return this.entityInCriticalPosition;
	}
	
}
