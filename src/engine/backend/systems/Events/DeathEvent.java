package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class DeathEvent extends EntityEvent{
	
	public DeathEvent(IEntity deadEntity){
		super.setEntity(deadEntity);
	}
	
}
