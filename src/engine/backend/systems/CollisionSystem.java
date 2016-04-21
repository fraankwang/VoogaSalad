package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import engine.backend.components.CollisionComponent;
import engine.backend.components.DamageComponent;
import engine.backend.components.HealthComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CollisionEvent;
import engine.backend.systems.Events.DeathEvent;

/**
 * Created by colinduffy on 4/10/16., raghav kedia
 */
public class CollisionSystem extends GameSystem{
 
    @Override
    public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources){
    	
    	Collection<IEntity> entities = myLevel.getEntities().values();
    	
    	for(IEntity entity1 : entities){
    		
    		if(!entity1.hasComponent(myComponentTagResources.getString("Collision"))){
    			continue;
    		}
    		
    		for(IEntity entity2 : entities){
    			
    			if(!entity1.hasComponent(myComponentTagResources.getString("Collision")) || entity2.equals(entity1)){
        			continue;
        		}
    			
    			if(checkIntersection(entity1, entity2, myComponentTagResources)){
    				sendCollisionEvent(entity1.getID(), entity2.getID());
    			}
    			
    		}
    		
    	}
    	
    }
    
	private void sendCollisionEvent(int entityID1, int entityID2){
		CollisionEvent collisionEvent = new CollisionEvent(entityID1, entityID2);
		notifyObservers(collisionEvent);
	}
    
    private boolean checkIntersection(IEntity entity1, IEntity entity2, ResourceBundle myComponentTagResources){
    	PositionComponent positionOne = (PositionComponent) entity1.getComponent(myComponentTagResources.getString("Position"));
    	PositionComponent positionTwo = (PositionComponent) entity2.getComponent(myComponentTagResources.getString("Position"));
    	SizeComponent sizeOne = (SizeComponent) entity1.getComponent(myComponentTagResources.getString("Size"));
    	SizeComponent sizeTwo = (SizeComponent) entity2.getComponent(myComponentTagResources.getString("Size"));
    	
    	return  positionOne.getX() < positionTwo.getX() + sizeTwo.getWidth() &&
    			positionOne.getX() + sizeOne.getWidth() > positionTwo.getX() &&
    			positionOne.getY() < positionTwo.getY() + sizeTwo.getHeight() &&
    			positionOne.getY() + sizeOne.getHeight() > positionTwo.getY();
    }
}
