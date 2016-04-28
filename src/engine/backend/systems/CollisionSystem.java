package engine.backend.systems;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CollisionEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.utilities.ComponentTagResources;

/**
 * Created by colinduffy on 4/10/16., raghav kedia
 */
public class CollisionSystem extends GameSystem{
 
    @Override
    public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond){
    	Collection<IEntity> collidableEntities = getEntitiesWithTag(myLevel.getEntities().values(), ComponentTagResources.collisionComponentTag);
    	collidableEntities.stream().forEach(entity1 -> collidableEntities.stream()
    			.filter(entity2 -> checkIntersection(entity1, entity2))
    			.forEach(entity2 -> {
    				IEvent event = getCollisionEvent(entity1, entity2);
    				Set<IEntity> entitySet = new HashSet<IEntity>();
    				entitySet.add(entity1);
    				entitySet.add(entity2);
    				addToEventMap(myEventMap, event, entitySet);
    			}));
    }
    
	private IEvent getCollisionEvent(IEntity entity1, IEntity entity2){
//		System.out.println("Collision Detected!");
		CollisionEvent collisionEvent = new CollisionEvent(entity1.getID(), entity2.getID());
		collisionEvent.setEventID(Arrays.asList(entity1.getName(), entity2.getName()));
		return collisionEvent;
	}
    
    private boolean checkIntersection(IEntity entity1, IEntity entity2){
    	PositionComponent positionOne = (PositionComponent) entity1.getComponent(ComponentTagResources.positionComponentTag);
    	PositionComponent positionTwo = (PositionComponent) entity2.getComponent(ComponentTagResources.positionComponentTag);
    	SizeComponent sizeOne = (SizeComponent) entity1.getComponent(ComponentTagResources.sizeComponentTag);
    	SizeComponent sizeTwo = (SizeComponent) entity2.getComponent(ComponentTagResources.sizeComponentTag);
    	
    	return  positionOne.getX() < positionTwo.getX() + sizeTwo.getWidth() &&
    			positionOne.getX() + sizeOne.getWidth() > positionTwo.getX() &&
    			positionOne.getY() < positionTwo.getY() + sizeTwo.getHeight() &&
    			positionOne.getY() + sizeOne.getHeight() > positionTwo.getY();
    }
}
