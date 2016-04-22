package engine.backend.systems;

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
    	
    	List<IEntity> entities = myLevel.getEntities();
    	
    	for(IEntity entity1 : entities){
    		
    		if(!entity1.hasComponent(myComponentTagResources.getString("Collision"))){
    			continue;
    		}
    		
    		for(IEntity entity2 : entities){
    			
    			if(!entity1.hasComponent(myComponentTagResources.getString("Collision")) || entity2.equals(entity1)){
        			continue;
        		}
    			
<<<<<<< HEAD
    			if(checkIntersection(damageEntity, targetEntity)){
                    updateIsCollided2(damageEntity, targetEntity);
                }
=======
    			if(checkIntersection(entity1, entity2, myComponentTagResources)){
    				sendCollisionEvent(entity1, entity2);
    			}
>>>>>>> origin/authoring_backend
    			
    		}
    		
    	}
    	
    }
<<<<<<< HEAD
    @Override
    /**
     * Implemented in O^2 for now.  Will eventually scale down to quadrant checks
     */
    public void update(List<IEntity> entities) {
        for(IEntity outEntity : entities){
            for(IEntity inEntity : entities){
                //default isCollided  = false.  Only update if there is a collision
                if(outEntity.hasComponent(getComponentTagResources().getString("Collision")) && inEntity.hasComponent(getComponentTagResources().getString("Collision"))){
                    if(checkIntersection(outEntity, inEntity)){
                        updateIsCollided(outEntity,  inEntity);
                    }
                }
            }
                    /*
                    ** May or may not implement this using syntactic sugar.  Java 8 is annoying
                    entities.stream()
                    .map((y) -> updateIsCollided((CollisionComponent)outEntity, (CollisionComponent)y)
                    .filter((x) -> checkIntersection((CollisionComponent)outEntity, (CollisionComponent) x));
                    */
        }


    }

    private void updateIsCollided2(IEntity damageEntity, IEntity targetEntity){
    	//get components for damageEntity
    	CollisionComponent dCollisionComponent = (CollisionComponent) damageEntity.getComponent(getComponentTagResources().getString("Collision"));
    	DamageComponent damageComponent = (DamageComponent) damageEntity.getComponent(getComponentTagResources().getString("Damage"));
    	
    	//get components for targetEntity
    	CollisionComponent tCollisionComponent = (CollisionComponent) targetEntity.getComponent(getComponentTagResources().getString("Collision"));
    	HealthComponent healthComponent = (HealthComponent) targetEntity.getComponent(getComponentTagResources().getString("Health"));
    	MovementComponent movComponent = (MovementComponent) targetEntity.getComponent(getComponentTagResources().getString("Movement"));
    	
    	healthComponent.setDamage(damageComponent.getDamageToHealth());
    	movComponent.getCurrentVelocityVector().scale(damageComponent.getDamageToVelocity());
    	
    	dCollisionComponent.setCollided(true);
    	tCollisionComponent.setCollided(true);
    	
    }


    private void updateIsCollided(IEntity outEntity, IEntity inEntity) {
        CollisionComponent out = (CollisionComponent)outEntity.getComponent(getComponentTagResources().getString("Collision"));
        CollisionComponent in = (CollisionComponent)inEntity.getComponent(getComponentTagResources().getString("Collision"));
        out.setCollided(true);
        in.setCollided(true);

    }

    /**
     * Uses 2d rectangle overlap to determine whether two components have collided
     * @param outEntity
     * @param inEntity
     * @return true if two entieis overlap with each other
     */
    private boolean checkIntersection(IEntity outEntity, IEntity inEntity) {
        CollisionComponent componentOne = (CollisionComponent)outEntity.getComponent(getComponentTagResources().getString("Collision"));
        CollisionComponent componentTwo = (CollisionComponent)inEntity.getComponent(getComponentTagResources().getString("Collision"));

        return  componentOne.getMyX() < componentTwo.getMyX() + componentTwo.getMyWidth() &&
                componentOne.getMyX() + componentOne.getMyWidth() > componentTwo.getMyX() &&
                componentOne.getMyY() < componentTwo.getMyY() + componentTwo.getMyHeight() &&
                componentOne.getMyY() + componentOne.getMyHeight() > componentTwo.getMyY();
=======
    
	private void sendCollisionEvent(IEntity entity1, IEntity entity2){
		CollisionEvent collisionEvent = new CollisionEvent(entity1, entity2);
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
>>>>>>> origin/authoring_backend
    }
}
