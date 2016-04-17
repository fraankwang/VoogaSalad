package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class DeathEvent extends Event{
	
	private IEntity deadEntity;
	
	public DeathEvent(IEntity deadEntity){
		this.deadEntity = deadEntity;
	}
	
	public IEntity getDeadEntity(){
		return deadEntity;
	}
	
	
}
