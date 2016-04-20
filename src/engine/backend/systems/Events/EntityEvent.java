package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;

import engine.backend.entities.IEntity;

public abstract class EntityEvent implements IEvent {

	private IEntity entity;

	public String[] getEventID() {
		String[] array = { entity.getName() + this.getClass().getSimpleName() };
		return array;
	}

	public Collection<IEntity> getEntities() {
		Collection<IEntity> entities = new ArrayList<IEntity>();
		entities.add(entity);
		return entities;
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
}
