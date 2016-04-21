package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalHealthEvent extends Event{
	
	public CriticalHealthEvent(IEntity criticalEntity){
		super.setEntity(criticalEntity);
	}
}
