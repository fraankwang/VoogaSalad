package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class CriticalHealthEvent extends Event{
	
	private IEntity criticalEntity;
	
	public CriticalHealthEvent(IEntity criticalEntity){
		this.criticalEntity = criticalEntity;
	}
	
	public boolean thisEventMatches(CriticalHealthEvent event){
		return criticalEntity.getName().equals(event.getCriticalEntity().getName());
	}
	
	public IEntity getCriticalEntity(){
		return criticalEntity;
	}
	
}
