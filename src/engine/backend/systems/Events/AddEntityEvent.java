package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends EntityEvent{
	
	private IEntity newEntity;
	
	public AddEntityEvent(IEntity newEntity){
		this.newEntity = newEntity;
	}
	
	public IEntity getNewEntity(){
		return newEntity;
	}
}
