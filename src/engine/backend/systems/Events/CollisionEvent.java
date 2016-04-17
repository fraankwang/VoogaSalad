package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import engine.backend.entities.IEntity;

public class CollisionEvent extends Event {
	
	private IEntity entity1;
	private IEntity entity2;
	
	public CollisionEvent(IEntity entity1, IEntity entity2){
		this.entity1 = entity1;
		this.entity2 = entity2;
	}
	
	public List<IEntity> getCollidingEntites(){
		List<IEntity> collidingEntities = new ArrayList<IEntity>();
		collidingEntities.add(entity1);
		collidingEntities.add(entity2);
		return collidingEntities;
	}
	
	public Set<String> getCollidingEntitesNames(){
		Set<String> names = new HashSet<String>();
		names.add(entity1.getName());
		names.add(entity2.getName());
		return names;
	}
	
	public boolean thisEventMatches(CollisionEvent collEvent){
		
		return getCollidingEntitesNames().equals(collEvent.getCollidingEntitesNames());
		
	}
	
}
