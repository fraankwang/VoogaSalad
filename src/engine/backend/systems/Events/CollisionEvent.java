package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;
import engine.backend.entities.IEntity;

public class CollisionEvent extends EntityEvent {

	private IEntity entity1;
	private IEntity entity2;

	public CollisionEvent(IEntity entity1, IEntity entity2) {
		this.entity1 = entity1;
		this.entity2 = entity2;
	}

	@Override
	public Collection<IEntity> getEntities() {
		Collection<IEntity> collidingEntities = new ArrayList<IEntity>();
		collidingEntities.add(entity1);
		collidingEntities.add(entity2);
		return collidingEntities;
	}

	@Override
	public String[] getEventID() {
		String[] array = { entity1.getName() + entity2.getName() + this.getClass().getSimpleName(),
				entity2.getName() + entity1.getName() + this.getClass().getSimpleName() };
		return array;
	}
}
