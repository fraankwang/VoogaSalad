package engine.backend.systems;

import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import engine.backend.components.DisplayComponent;
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

public class MobilizeSystem extends GameSystem{
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Level myLevel, Map<Integer, IEvent> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond, ResourceBundle myComponentTagResources) {

		Collection<IEntity> entities = myLevel.getEntities().values();
		for(IEntity entity : entities){
			
			if(!entity.hasComponent(myComponentTagResources.getString("Movement"))){
				continue;
			}
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent(myComponentTagResources.getString("Movement"));
			PositionComponent posComponent = (PositionComponent) entity.getComponent(myComponentTagResources.getString("Position"));
			
			if(entity.hasComponent(myComponentTagResources.getString("Path"))){
				
				PathComponent pathComponent = (PathComponent) entity.getComponent(myComponentTagResources.getString("Path"));
				//if on path
				IEvent event = updatePositionOnPath(entity.getID(), posComponent, movComponent, pathComponent, myLevel.getMap().getPath(), myComponentTagResources);
				
				if(event != null){
					//add to event map
				}
				
			}
			else{
				
				IEvent event = updatePosition(entity.getID(), posComponent, movComponent, myLevel.getMap(), myComponentTagResources);
				
				if(event != null){
					//add to event map;
				}
				
			}
			
			updateRotation(movComponent);
			
			entity.setHasBeenModified(true);
			
		}

	}
	
	
	/**
	 * 
	 * This method updates the rotation of an entity, by adding it's angular velocity omega to it'current angle theta
	 */
	private void updateRotation(MovementComponent movComponent){
		//do rotation
		double theta = movComponent.getTheta();
		double omega = movComponent.getCurrentOmega();
		movComponent.setTheta(theta+omega);
	}
	
	/**
	 * 
	 *This method updates the position of an entity that DOES NOT exist on a path. 
	 *It takes it's current position vector, and adds it's velocity vector to it, updating it's position
	 *If it's new position is outside of the Map, it will generate an OutOfMap event
	 */
	private IEvent updatePosition(int entityID, PositionComponent posComponent, MovementComponent movComponent, GameMap map, ResourceBundle myComponentTagResources){
		
		//do movement
		Vector posVector = posComponent.getPositionVector();
		Vector velVector = movComponent.getCurrentVelocityVector();
		Vector newPos = posVector.add(velVector);
		posComponent.setPositionVector(newPos);
		
		if(outOfMap(newPos, map.getMapHeight(), map.getMapWidth())){
			IEvent event = getOutOfMapEvent(entityID);
			return event;
		}
		return null;
	}
	
	/**
	 * 
	 * Checks to see if the position Vector of an entity is outside of the map
	 */
	private boolean outOfMap(Vector posVector, double height, double width){
		if(posVector.getX() < 0 || posVector.getX() > width){
			return true;
		}
		if(posVector.getY() < 0 || posVector.getY() > height){
			return true;
		}
		return false;
	}
	
	private IEvent getEndOfPathEvent(int entityID){
		EndOfPathEvent event = new EndOfPathEvent(entityID);
		return event;
	}
	
	private IEvent getOutOfMapEvent(int entityID){
		OutOfMapEvent event = new OutOfMapEvent(entityID);
		return event;
	}
	
	/**
	 * 
	 * This method updates the position of entites that move ON A PATH
	 * It gets the current bezier curve for an object, and based on it's velocity it gets the new bezier time, 
	 * and adds the new time to the current time to updates it's position along the curve
	 * If the entity reaches the end of the curve (bezier time is max), it will generate an EndOfPathEvent
	 */
	private IEvent updatePositionOnPath(int entityID, PositionComponent posComponent, MovementComponent movComponent, PathComponent pathComponent, Path path, ResourceBundle myComponentTagResources){
		
		double currBezTime = pathComponent.getBezierTime();

		if((currBezTime >= path.numCurves() - 0.01 && pathComponent.movesWithTime())){
			
			//create end of path event
			IEvent event = getEndOfPathEvent(entityID);
			return event;
			
		}
		
		Vector newPos = new Vector();
		Vector newVel = new Vector();
		
		Vector velVector = movComponent.getCurrentVelocityVector();
		
		BezierCurve currCurve = path.getCurveFromTime(currBezTime);
		double speed = velVector.calculateMagnitude();
		double bezTimeStep = ((pathComponent.movesWithTime()) ? 1 : -1 ) * speed / currCurve.getLength();
		
		double newBezTime = currBezTime + bezTimeStep;
		
		BezierCurve newCurve = path.getCurveFromTime(newBezTime);
		newPos = newCurve.calculateNewBezierPoint(newBezTime - Math.floor(newBezTime));
		newVel = newCurve.calculateNewBezierTangent(newBezTime - Math.floor(newBezTime));
		newVel = newVel.normalize();
		newVel = newVel.scale(speed);
		
		pathComponent.setCurveID((int) Math.floor(newBezTime));
		posComponent.setPositionVector(newPos);
		pathComponent.setBezierTime(newBezTime);
		movComponent.setCurrentVelocityVector(newVel);
		
		return null;
		
	}
	

}
