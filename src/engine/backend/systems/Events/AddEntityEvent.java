package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends EntityEvent{
	
	public AddEntityEvent(IEntity entity){
		super.setEntity(entity);
	}
}
