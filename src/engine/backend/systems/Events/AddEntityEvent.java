package engine.backend.systems.Events;

import java.util.Collection;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends EntityEvent{
	
	private Collection<IEntity> newEntities;
	
	public AddEntityEvent(Collection<IEntity> newEntities){
		this.newEntities = newEntities;
	}
	
	public Collection<IEntity> getNewEntity(){
		return newEntities;
	}
}
