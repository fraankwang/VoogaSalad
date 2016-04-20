package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalHealthEvent extends EntityEvent{
	
	public CriticalHealthEvent(IEntity criticalEntity){
		super.setEntity(criticalEntity);
	}
}
