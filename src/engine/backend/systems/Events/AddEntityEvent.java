package engine.backend.systems.Events;

import java.util.Collection;

import engine.backend.entities.IEntity;

public class AddEntityEvent extends EntityEvent {

	private Collection<IEntity> newEntities;

	public AddEntityEvent(Collection<IEntity> newEntities) {
		this.newEntities = newEntities;
	}

	public Collection<IEntity> getNewEntities() {
		return newEntities;
	}

	@Override
	public String getEventID() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

}
