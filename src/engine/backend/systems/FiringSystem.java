package engine.backend.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringSystem extends GameSystem{

	@Override 
	public void update(Level myLevel,  List<IEvent> myEventList, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		for(IEntity shootingEntity : entities){

			if(!shootingEntity.hasComponent(myComponentTagResources.getString("Firing"))){
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

		myEventList.add(getAddEntityEvent(newEntities));

	} 
	
	private void updateFiring(IEntity shootingEntity, IEntity targetEntity, Collection<IEntity> newEntities, 
			double currentSecond, InGameEntityFactory myEntityFactory){
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(myComponentTagResources.getString("Firing"));
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent(myComponentTagResources.getString("Position"));
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent(myComponentTagResources.getString("Position"));

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
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(myComponentTagResources.getString("Firing"));
		List<String> targets = firingComponent.getTargets();
		return targets.contains(targetEntity.getName());
	}

	private boolean targetIsInRange(IEntity shootingEntity, IEntity targetEntity){
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent(myComponentTagResources.getString("Firing"));
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent(myComponentTagResources.getString("Position"));
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent(myComponentTagResources.getString("Position"));

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

		PositionComponent firedPosComponent = (PositionComponent) ammoEntity.getComponent(myComponentTagResources.getString("Position"));
		MovementComponent firedMovComponent = (MovementComponent) ammoEntity.getComponent(myComponentTagResources.getString("Movement"));

		firedPosComponent.setPositionVector(new Vector(positionVector));

		Vector velVector = new Vector(directionToFire);

		velVector = velVector.normalize();
		velVector.scale(speed);
		firedMovComponent.setCurrentVelocityVector(velVector);
		firedMovComponent.setDefaultVelocityVector(velVector);

		return ammoEntity;		
	}

}
