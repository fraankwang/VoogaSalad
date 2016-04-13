package engine.backend.systems;

import java.util.List;

import authoring.backend.factories.EntityFactory;
import authoring.backend.factories.InGameEntityFactory;
import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringSystem extends Systemm implements ISystem{

	@Override 
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory) {
		// TODO Auto-generated method stub
		
		for(IEntity shootingEntity : entities){
			
			if(shootingEntity.hasComponent(getComponentTagResources().getString("Firing"))){
				
				for(IEntity targetEntity : entities){
					
					if(shootingEntity.equals(targetEntity)){
						continue;
					}
					
					//needs to check if it's something it can fire at
					if(targetEntity.getName().equals("Enemy")){
						
						IEntity firedEntity = handleFiring(shootingEntity, targetEntity);
						if(firedEntity == null){
							continue;
						}
						else{
							entities.add(firedEntity);
						}
						
					}
					
				}
				
			}
			
		}
	}
	
	private IEntity handleFiring(IEntity shootingEntity, IEntity targetEntity){
		
		//Get firing component from shooting Entity
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(getComponentTagResources().getString("Firing"));
		//Get Position component of shooting entity
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent(getComponentTagResources().getString("Position"));
		MovementComponent shootingMovComponent = (MovementComponent) shootingEntity.getComponent(getComponentTagResources().getString("Movement"));
		//Get position component of target entity;
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent(getComponentTagResources().getString("Position"));
		
		//Get position of shooting entity
		Vector shootingPosVector = shootingPosComponent.getPositionVector();
		//get position of target entity;
		Vector targetPosVector = targetPosComponent.getPositionVector();
		
		//if target is in range of shooter
		if(shootingPosVector.calculateDistance(targetPosVector) <= firingComponent.getEnemyInSightRange() 
				&& firingComponent.getAmmunitionAmount() > 0){
			
			//get an instance of the shooters ammo
			String firedEntityName = firingComponent.getAmmunition();
			IEntity firedEntity = EntityFactory.getEntity(firedEntityName);
			
			//set the position and movement components of the ammo
			PositionComponent firedPosComponent = (PositionComponent) firedEntity.getComponent(getComponentTagResources().getString("Position"));
			MovementComponent firedMovComponent = (MovementComponent) firedEntity.getComponent(getComponentTagResources().getString("Movement"));
			
			firedPosComponent.setPositionVector(shootingPosVector);
			
			double xComp = targetPosVector.getX() - shootingPosVector.getX();
			double yComp = targetPosVector.getY() - shootingPosVector.getY();
			Vector firedVelVector = new Vector(xComp, yComp);
			firedVelVector = firedVelVector.normalize();
			firedVelVector.scale(firingComponent.getAmmunitionSpeed());
			firedMovComponent.setCurrentVelocityVector(firedVelVector);
			firedMovComponent.setDefaultVelocityVector(firedVelVector);
			
			shootingMovComponent.setTheta(targetPosVector.calculateDirection(shootingPosVector));
			
			return firedEntity;
			
		}
		
		return null;

	}

}
