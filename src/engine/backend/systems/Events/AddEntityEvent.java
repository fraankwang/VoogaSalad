package engine.backend.systems.Events;

import java.util.Collection;
import java.util.List;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends EntityEvent{
	
	private Collection<IEntity> newEntities;
	
	public AddEntityEvent(Collection<IEntity> newEntities){
		this.newEntities = newEntities;
	}
	
	public Collection<IEntity> getNewEntity(){
		return newEntities;
	}

	@Override
	public String getEventID(List<String> identifiers) {
		// TODO Auto-generated method stub
		return null;
	}
}
