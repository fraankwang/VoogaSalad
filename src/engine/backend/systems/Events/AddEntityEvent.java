package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends Event{
	
	public AddEntityEvent(IEntity entity){
		super.setEntity(entity);
	}
}
