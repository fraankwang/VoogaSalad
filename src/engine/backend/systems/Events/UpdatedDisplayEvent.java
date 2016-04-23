package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.Collection;

import engine.backend.entities.IEntity;

public class UpdatedDisplayEvent extends Event {

	private IEntity entity1;

	public UpdatedDisplayEvent(IEntity entity1) {
		this.entity1 = entity1;
	}
	
	public UpdatedDisplayEvent(){
		System.out.println("hello");
	}

	@Override
	public Collection<IEntity> getEntities() {
		Collection<IEntity> collidingEntities = new ArrayList<IEntity>();
		collidingEntities.add(entity1);
		return collidingEntities;
	}

	@Override
	public String[] getEventID() {
		String[] array = { entity1.getName() + this.getClass().getSimpleName() };
		return array;
	}

	@Override
	public String toString() {
		return "I am a display event";
	}
	
	

}
