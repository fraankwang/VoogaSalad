package backend.systems;

import backend.game_object.components.CollisionComponent;
import backend.game_object.components.DamageComponent;
import backend.game_object.components.HealthComponent;
import backend.game_object.components.MovementComponent;
import backend.game_object.entities.IEntity;

import java.util.List;

/**
 * Created by colinduffy on 4/10/16.
 */
public class CollisionSystem extends Systemm implements ISystem {
 
    
    public void update2(List<IEntity> entities){
    	
    	for(IEntity damageEntity : entities){
    		
    		if(!damageEntity.hasComponent(getComponentTagResources().getString("Damage")) && 
    				!damageEntity.hasComponent(getComponentTagResources().getString("Collision"))){
    			continue;
    		}
    		
    		for(IEntity targetEntity : entities){
    			
    			if(!damageEntity.hasComponent(getComponentTagResources().getString("Collision")) || targetEntity.equals(damageEntity)){
        			continue;
        		}
    			
    			if(checkIntersection2(damageEntity, targetEntity)){
                    updateIsCollided2(damageEntity, targetEntity);
                }
    			
    		}
    		
    	}
    	
    }
    @Override
    /**
     * Implemented in O^2 for now.  Will eventually scale down to quadrant checks
     */
    public void update(List<IEntity> entities) {
        for(IEntity outEntity : entities){
            for(IEntity inEntity : entities){
                //default isCollided  = false.  Only update if there is a collision
                if(outEntity.hasComponent(getComponentTagResources().getString("Collision")) && inEntity.hasComponent(getComponentTagResources().getString("Collision"))){
                    if(checkIntersection((CollisionComponent)outEntity, (CollisionComponent)inEntity)){
                        updateIsCollided((CollisionComponent) outEntity, (CollisionComponent) inEntity);
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
    
    private void updateIsCollided(CollisionComponent outEntity, CollisionComponent inEntity) {
        outEntity.setCollided(true);
        inEntity.setCollided(true);
    }
    
    private boolean checkIntersection2(IEntity entity1, IEntity entity2){
    	CollisionComponent componentOne = (CollisionComponent) entity1.getComponent(getComponentTagResources().getString("Collision"));
    	CollisionComponent componentTwo = (CollisionComponent) entity2.getComponent(getComponentTagResources().getString("Collision"));
    	
    	return  componentOne.getMyX() < componentTwo.getMyX() + componentTwo.getMyWidth() &&
                componentOne.getMyX() + componentOne.getMyWidth() > componentTwo.getMyX() &&
                componentOne.getMyY() < componentTwo.getMyY() + componentTwo.getMyHeight() &&
                componentOne.getMyY() + componentOne.getMyHeight() > componentTwo.getMyY();
    }

    private boolean checkIntersection(CollisionComponent componentOne, CollisionComponent componentTwo) {

        return  componentOne.getMyX() < componentTwo.getMyX() + componentTwo.getMyWidth() &&
                componentOne.getMyX() + componentOne.getMyWidth() > componentTwo.getMyX() &&
                componentOne.getMyY() < componentTwo.getMyY() + componentTwo.getMyHeight() &&
                componentOne.getMyY() + componentOne.getMyHeight() > componentTwo.getMyY();
    }
}
