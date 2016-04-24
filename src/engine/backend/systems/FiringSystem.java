package engine.backend.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.EntityEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.utilities.ComponentTagResources;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringSystem extends GameSystem{

	@Override 
	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		for(IEntity shootingEntity : entities){

			if(!shootingEntity.hasComponent(ComponentTagResources.movementComponentTag)){
				continue;
			}

			for(IEntity targetEntity : entities){

				//needs to check if it's something it can fire at, and if it's in range
				if(isTarget(shootingEntity, targetEntity) && 
						targetIsInRange(shootingEntity, targetEntity)){

					updateFiring(shootingEntity, targetEntity, newEntities, currentSecond, myEntityFactory);
					
				}

			}

		}
		
		List<Integer> ids = new ArrayList<Integer>();
		for(IEntity entity : newEntities){
			ids.add(entity.getID());
		}

		addToEventMap(myEventMap, getAddEntityEvent(newEntities), newEntities);

	} 
	
	private void updateFiring(IEntity shootingEntity, IEntity targetEntity, Collection<IEntity> newEntities, 
			double currentSecond, InGameEntityFactory myEntityFactory){
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(ComponentTagResources.firingComponentTag);
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent(ComponentTagResources.positionComponentTag);
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent(ComponentTagResources.positionComponentTag);

		Vector shootingPosVector = shootingPosComponent.getPositionVector();
		Vector targetPosVector = targetPosComponent.getPositionVector();
		
		if(firingComponent.getTimer() == 0 || firingComponent.fireNow()){
			double xComp = targetPosVector.getX() - shootingPosVector.getX();
			double yComp = targetPosVector.getY() - shootingPosVector.getY();
			Vector firedVelVector = new Vector(xComp, yComp);

			IEntity newEntity = initilizeFire(firingComponent.getAmmunition(), shootingPosVector, firedVelVector, 
					firingComponent.getAmmunitionSpeed(), myEntityFactory);
			newEntities.add(newEntity);
			firingComponent.setFireNow(false);
			firingComponent.resetTimer();
		}
		else{
			firingComponent.setTimer(currentSecond);
		}
	}

	private boolean isTarget(IEntity shootingEntity, IEntity targetEntity){
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(ComponentTagResources.firingComponentTag);
		List<String> targets = firingComponent.getTargets();
		return targets.contains(targetEntity.getName());
	}

	private boolean targetIsInRange(IEntity shootingEntity, IEntity targetEntity){
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(ComponentTagResources.firingComponentTag);
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent(ComponentTagResources.positionComponentTag);
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent(ComponentTagResources.positionComponentTag);

		Vector shootingPosVector = shootingPosComponent.getPositionVector();
		Vector targetPosVector = targetPosComponent.getPositionVector();

		return shootingPosVector.calculateDistance(targetPosVector) <= firingComponent.getEnemyInSightRange();

	}

	private IEvent getAddEntityEvent(Collection<IEntity> newEntities){
		AddEntityEvent event = new AddEntityEvent(newEntities);
		return event;
	}

	private IEntity initilizeFire(String entityName, Vector positionVector, Vector directionToFire, double speed, InGameEntityFactory myEntityFactory){

		IEntity ammoEntity = myEntityFactory.createEntity(entityName);

		PositionComponent firedPosComponent = (PositionComponent) ammoEntity.getComponent(ComponentTagResources.positionComponentTag);
		MovementComponent firedMovComponent = (MovementComponent) ammoEntity.getComponent(ComponentTagResources.movementComponentTag);

		firedPosComponent.setPositionVector(new Vector(positionVector));

		Vector velVector = new Vector(directionToFire);

		velVector = velVector.normalize();
		velVector.scale(speed);
		firedMovComponent.setCurrentVelocityVector(velVector);
		firedMovComponent.setDefaultVelocityVector(velVector);

		return ammoEntity;		
	}

}
