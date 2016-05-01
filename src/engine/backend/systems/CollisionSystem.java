package engine.backend.systems;

import java.awt.Rectangle;
import java.util.ArrayList;
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
 * Created by colinduffy on 4/10/16., raghav kedia, Christine Zhou
 */
public class CollisionSystem extends GameSystem{

 
    @Override
    public void update(boolean playing, Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond){
    	
    	if(!playing){
			return;
		}
    	
    	QuadTree quad = setUpQuadTree(myLevel);
    	Collection<IEntity> collidableEntities = getEntitiesWithTag(myLevel.getEntities().values(), ComponentTagResources.collisionComponentTag);
    	collidableEntities.forEach(entity -> quad.insert(entity));
    	Collection<IEntity> retrievedCollidables = new ArrayList<IEntity>();
		collidableEntities.stream().forEach(entity1 -> {
			retrievedCollidables.clear();
			quad.retrieve(retrievedCollidables, entity1);
			retrievedCollidables.stream().filter(entity2 -> checkIntersection(entity1, entity2)).forEach(entity2 -> {
				IEvent event = getCollisionEvent(entity1, entity2);
				Set<IEntity> entitySet = new HashSet<IEntity>();
				entitySet.add(entity1);
				entitySet.add(entity2);
				addToEventMap(myEventMap, event, entitySet);
			});
		});
    					 
    }

	private QuadTree setUpQuadTree(Level myLevel) {
		Rectangle r =new Rectangle();
    	r.setBounds(0, 0, (int) myLevel.getMap().getMapWidth(), (int) myLevel.getMap().getMapHeight());
    	QuadTree quad = new QuadTree(0, r);
		return quad;
	}

    
	private IEvent getCollisionEvent(IEntity entity1, IEntity entity2){
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
