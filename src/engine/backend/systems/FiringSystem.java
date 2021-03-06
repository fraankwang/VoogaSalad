package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.MultiDirectionalFiringComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.TrackingMovementComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.utilities.ComponentTagResources;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringSystem extends GameSystem {

	@Override
	public void update(boolean playing, Level myLevel, Map<String, Set<Integer>> myEventMap,
			InGameEntityFactory myEntityFactory, double currentSecond) {

		if (!playing) {
			return;
		}

		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		Collection<IEntity> shootingEntities = getEntitiesWithTag(myLevel.getEntities().values(),
				ComponentTagResources.firingComponentTag);
		shootingEntities.stream().forEach(shootingEntity -> {
			myLevel.getEntities().values().stream()
					.filter(entity -> isTarget(shootingEntity, entity) && targetIsInRange(shootingEntity, entity))
					.forEach(entity -> updateFiring(shootingEntity, entity, newEntities, currentSecond,
							myEntityFactory));

		});

		sendAddEntityEvent(newEntities);

	}

	/**
	 * Updates the firing by getting the firing component from the shooting
	 * entity. Checks whether or not the entity can shoot and instantiates the
	 * entity being fired.
	 * 
	 * @param shootingEntity
	 * @param targetEntity
	 * @param newEntities
	 * @param currentSecond
	 * @param myEntityFactory
	 */
	private void updateFiring(IEntity shootingEntity, IEntity targetEntity, Collection<IEntity> newEntities,
			double currentSecond, InGameEntityFactory myEntityFactory) {

		FiringComponent firingComponent = (FiringComponent) shootingEntity
				.getComponent(ComponentTagResources.firingComponentTag);

		if (firingComponent.getTimer() <= 0 || firingComponent.fireNow()) {
			for (int i = 0; i < firingComponent.getNumDirections(); i++) {
				Vector firedVelVector = null;

				if (firingComponent instanceof MultiDirectionalFiringComponent) {
					firedVelVector = ((MultiDirectionalFiringComponent) firingComponent).getDirectionAtIndex(i);
				} else {
					firedVelVector = calculateVelocityVector(shootingEntity, targetEntity);

				}
				IEntity newEntity = initilizeFire(firingComponent.getAmmunition(),
						getEntityPositionVector(shootingEntity), firedVelVector, firingComponent.getAmmunitionSpeed(),
						targetEntity, myEntityFactory);
				newEntities.add(newEntity);
			}

			firingComponent.setFireNow(false);
			firingComponent.resetTimer();
		}

		else {
			firingComponent.decrementTimer();
		}
	}

	private Vector calculateVelocityVector(IEntity shootingEntity, IEntity targetEntity) {
		Vector shootingPosVector = getEntityPositionVector(shootingEntity);
		Vector targetPosVector = getEntityPositionVector(targetEntity);
		double xComp = targetPosVector.getX() - shootingPosVector.getX();
		double yComp = targetPosVector.getY() - shootingPosVector.getY();
		return new Vector(xComp, yComp);
	}

	private Vector getEntityPositionVector(IEntity entity) {
		PositionComponent posComponent = (PositionComponent) entity
				.getComponent(ComponentTagResources.positionComponentTag);
		return posComponent.getPositionVector();
	}

	private boolean isTarget(IEntity shootingEntity, IEntity targetEntity) {
		FiringComponent firingComponent = (FiringComponent) shootingEntity
				.getComponent(ComponentTagResources.firingComponentTag);
		List<String> targets = firingComponent.getTargets();
		return targets.contains(targetEntity.getName());
	}

	private boolean targetIsInRange(IEntity shootingEntity, IEntity targetEntity) {
		FiringComponent firingComponent = (FiringComponent) shootingEntity
				.getComponent(ComponentTagResources.firingComponentTag);
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity
				.getComponent(ComponentTagResources.positionComponentTag);
		PositionComponent targetPosComponent = (PositionComponent) targetEntity
				.getComponent(ComponentTagResources.positionComponentTag);

		Vector shootingPosVector = shootingPosComponent.getPositionVector();
		Vector targetPosVector = targetPosComponent.getPositionVector();
		return shootingPosVector.calculateDistance(targetPosVector) <= firingComponent.getEnemyInSightRange();
	}

	private IEntity initilizeFire(String entityName, Vector positionVector, Vector directionToFire, double speed,
			IEntity targetEntity, InGameEntityFactory myEntityFactory) {

		IEntity ammoEntity = myEntityFactory.createEntity(entityName);
		PositionComponent firedPosComponent = (PositionComponent) ammoEntity
				.getComponent(ComponentTagResources.positionComponentTag);
		MovementComponent firedMovComponent = (MovementComponent) ammoEntity
				.getComponent(ComponentTagResources.movementComponentTag);

		firedPosComponent.setPositionVector(new Vector(positionVector));

		if (firedMovComponent instanceof TrackingMovementComponent) {
			((TrackingMovementComponent) firedMovComponent).setEntityToTrack(targetEntity);
			((TrackingMovementComponent) firedMovComponent).setSpeed(speed);
			((TrackingMovementComponent) firedMovComponent).setPosition(firedPosComponent);
		}

		Vector velVector = new Vector(directionToFire);
		velVector.normalize();
		velVector.scale(speed);
		firedMovComponent.setCurrentVelocityVector(velVector);
		firedMovComponent.setDefaultVelocityVector(velVector);
		return ammoEntity;
	}

	private void sendAddEntityEvent(Collection<IEntity> newEntities) {
		AddEntityEvent event = new AddEntityEvent(newEntities);
		setChanged();
		notifyObservers(event);
	}
}
