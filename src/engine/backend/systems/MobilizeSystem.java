package engine.backend.systems;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.systems.Events.EndOfPathEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.OutOfMapEvent;
import engine.backend.utilities.ComponentTagResources;

public class MobilizeSystem extends GameSystem {

	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(SystemSetUp setUp) {

		Collection<IEntity> movableEntities = setUp.getEntitiesWithTag(setUp.getCurrentLevel().getEntities().values(),
				ComponentTagResources.movementComponentTag);
		for (IEntity entity : movableEntities) {

			MovementComponent movComponent = (MovementComponent) entity
					.getComponent(ComponentTagResources.movementComponentTag);
			PositionComponent posComponent = (PositionComponent) entity
					.getComponent(ComponentTagResources.positionComponentTag);

			if (entity.hasComponent(ComponentTagResources.pathComponentTag)) {
				PathComponent pathComponent = (PathComponent) entity
						.getComponent(ComponentTagResources.pathComponentTag);
				addToEventMap(setUp.getMyEventMap(), updatePositionOnPath(entity, posComponent, movComponent, pathComponent,
						setUp.getCurrentLevel().getMap().getPath(pathComponent.getPathID())), entity);
			} else {
				addToEventMap(setUp.getMyEventMap(), updatePosition(entity, posComponent, movComponent, setUp.getCurrentLevel().getMap()), entity);
			}
			updateRotation(movComponent);
			entity.setHasBeenModified(true);
		}
	}

	/**
	 * 
	 * This method updates the rotation of an entity, by adding it's angular
	 * velocity omega to it'current angle theta
	 */
	private void updateRotation(MovementComponent movComponent) {
		// do rotation
		double theta = movComponent.getTheta();
		double omega = movComponent.getCurrentOmega();
		movComponent.setTheta(theta + omega);
	}

	/**
	 * 
	 * This method updates the position of an entity that DOES NOT exist on a
	 * path. It takes it's current position vector, and adds it's velocity
	 * vector to it, updating it's position If it's new position is outside of
	 * the Map, it will generate an OutOfMap event
	 */
	private IEvent updatePosition(IEntity entity, PositionComponent posComponent, MovementComponent movComponent,
			GameMap map) {

		// do movement
		Vector posVector = posComponent.getPositionVector();
		Vector velVector = movComponent.getCurrentVelocityVector();
		Vector newPos = posVector.add(velVector);
		posComponent.setPositionVector(newPos);

		if (outOfMap(newPos, map.getMapHeight(), map.getMapWidth())) {
			IEvent event = getOutOfMapEvent(entity);
			return event;
		}
		return null;
	}

	/**
	 * 
	 * Checks to see if the position Vector of an entity is outside of the map
	 */
	private boolean outOfMap(Vector posVector, double height, double width) {
		if (posVector.getX() < 0 || posVector.getX() > width) {
			return true;
		}
		if (posVector.getY() < 0 || posVector.getY() > height) {
			return true;
		}
		return false;
	}

	private IEvent getEndOfPathEvent(IEntity entity) {
		EndOfPathEvent event = new EndOfPathEvent(entity.getID());
		event.setEventID(entity.getName());
		return event;
	}

	private IEvent getOutOfMapEvent(IEntity entity) {
		OutOfMapEvent event = new OutOfMapEvent(entity.getID());
		event.setEventID(entity.getName());
		return event;
	}

	/**
	 * 
	 * This method updates the position of entites that move ON A PATH It gets
	 * the current bezier curve for an object, and based on it's velocity it
	 * gets the new bezier time, and adds the new time to the current time to
	 * updates it's position along the curve If the entity reaches the end of
	 * the curve (bezier time is max), it will generate an EndOfPathEvent
	 */
	private IEvent updatePositionOnPath(IEntity entity, PositionComponent posComponent, MovementComponent movComponent,
			PathComponent pathComponent, Path path) {
		double currBezTime = pathComponent.getBezierTime();

		if ((currBezTime >= path.numCurves() - 0.01)) {

			// create end of path event

			IEvent event = getEndOfPathEvent(entity);
			return event;

		}

		Vector newPos = new Vector();
		Vector newVel = new Vector();

		Vector velVector = movComponent.getCurrentVelocityVector();

		BezierCurve currCurve = path.getCurveFromTime(currBezTime);

		double speed = velVector.calculateMagnitude();

		double bezTimeStep = speed / currCurve.getLength();

		double newBezTime = currBezTime + bezTimeStep;

		BezierCurve newCurve = path.getCurveFromTime(newBezTime);
		newPos = newCurve.calculateNewBezierPoint(newBezTime - Math.floor(newBezTime));
		newVel = newCurve.calculateNewBezierTangent(newBezTime - Math.floor(newBezTime));

		newVel.normalize();
		newVel.scale(speed);

		pathComponent.setCurveID((int) Math.floor(newBezTime));
		posComponent.setPositionVector(newPos);
		pathComponent.setBezierTime(newBezTime);
		movComponent.setCurrentVelocityVector(newVel);
		return null;

	}

}
