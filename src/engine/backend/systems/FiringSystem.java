package engine.backend.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.IEvent;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringSystem extends GameSystem {

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, double currentSecond,
			ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		for (IEntity shootingEntity : entities) {

			if (shootingEntity.hasComponent(myComponentTagResources.getString("Firing"))) {
				FiringComponent firingComponent = (FiringComponent) shootingEntity
						.getComponent(myComponentTagResources.getString("Firing"));

				for (IEntity targetEntity : entities) {

					// needs to check if it's something it can fire at
					if (firingComponent.getTargets().contains(targetEntity.getName())) {

						PositionComponent shootingPosComponent = (PositionComponent) shootingEntity
								.getComponent(myComponentTagResources.getString("Position"));
						PositionComponent targetPosComponent = (PositionComponent) targetEntity
								.getComponent(myComponentTagResources.getString("Position"));
						Vector shootingPosVector = shootingPosComponent.getPositionVector();
						Vector targetPosVector = targetPosComponent.getPositionVector();

						if (targetIsInRange(firingComponent.getEnemyInSightRange(), shootingPosVector,
								targetPosVector)) {

							if (firingComponent.getTimer() == 0 || firingComponent.fireNow()) {
								double xComp = targetPosVector.getX() - shootingPosVector.getX();
								double yComp = targetPosVector.getY() - shootingPosVector.getY();
								Vector firedVelVector = new Vector(xComp, yComp);
								System.out.println("added here.");
								IEntity newEntity = initilizeFire(firingComponent.getAmmunition(), shootingPosVector,
										firedVelVector, firingComponent.getAmmunitionSpeed(), myEntityFactory,
										myComponentTagResources);
								// that entity name is not the name of the
								// bullet
								newEntities.add(newEntity);
								firingComponent.setFireNow(false);
								firingComponent.resetTimer();
							} else {
								firingComponent.setTimer(currentSecond);
							}
						}

					}

				}

			}
		}
		sendAddEntityEvent(newEntities);
	}

	private boolean targetIsInRange(double range, Vector shootingPosVector, Vector targetPosVector) {

		return shootingPosVector.calculateDistance(targetPosVector) <= range;

	}

	private void sendAddEntityEvent(Collection<IEntity> newEntities) {
		IEvent event = new AddEntityEvent(newEntities);
		this.setChanged();
		notifyObservers(event);
	}

	private IEntity initilizeFire(String entityName, Vector positionVector, Vector directionToFire, double speed,
			InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		System.out.println("hi.....................");
		IEntity ammoEntity = myEntityFactory.createEntity(entityName);
		PositionComponent firedPosComponent = (PositionComponent) ammoEntity
				.getComponent(myComponentTagResources.getString("Position"));
		MovementComponent firedMovComponent = (MovementComponent) ammoEntity
				.getComponent(myComponentTagResources.getString("Movement"));

		firedPosComponent.setPositionVector(new Vector(positionVector));

		Vector velVector = new Vector(directionToFire);

		velVector = velVector.normalize();
		velVector = velVector.scale(speed);
		System.out.println(speed);
		System.out.println(velVector.getX() + "  " + velVector.getY());
		firedMovComponent.setCurrentVelocityVector(velVector);
		firedMovComponent.setDefaultVelocityVector(velVector);

		return ammoEntity;
		// add ammoEntity to list of Entites in level;

	}

}
