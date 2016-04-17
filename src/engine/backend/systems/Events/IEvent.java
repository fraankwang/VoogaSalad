package engine.backend.systems.Events;

import java.util.Collection;

import engine.backend.entities.IEntity;

public interface IEvent {
	
	public String[] getEventID();
	public Collection<IEntity> getEntities();
	
}
