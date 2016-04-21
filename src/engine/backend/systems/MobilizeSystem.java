package engine.backend.systems;

/**
 * author raghavkedia
 */

import java.util.List;
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

public class MobilizeSystem extends GameSystem{
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity entity : entities){
			
			if(!entity.hasComponent(myComponentTagResources.getString("Movement"))){
				continue;
			}
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent(myComponentTagResources.getString("Movement"));
			PositionComponent posComponent = (PositionComponent) entity.getComponent(myComponentTagResources.getString("Position"));
			
			if(entity.hasComponent(myComponentTagResources.getString("Path"))){
				//if on path
				updatePathMovement(entity, myLevel.getMap(), myComponentTagResources);
			}
			else{
				//do movement
				Vector posVector = posComponent.getPositionVector();
				Vector velVector = movComponent.getCurrentVelocityVector();
				posVector.add(velVector);
			}
			
			//do rotation
			double theta = movComponent.getTheta();
			double omega = movComponent.getCurrentOmega();
			movComponent.setTheta(theta+omega);
			
			entity.setHasBeenModified(true);
			
		}

	}
	
	private void updatePathMovement(IEntity entity, GameMap map, ResourceBundle myComponentTagResources){
		//needs access to path
		
		updatePositionOnPath(entity, map.getPath(), myComponentTagResources);
		
	}
	
	public void updatePositionOnPath(IEntity entity, Path path, ResourceBundle myComponentTagResources){
		
		PathComponent pathComponent = (PathComponent) entity.getComponent(myComponentTagResources.getString("Path"));
		double currBezTime = pathComponent.getBezierTime();

		//turn off display component and return
		if((currBezTime >= path.numCurves() - 0.01 && pathComponent.movesWithTime())){
			
			DisplayComponent dispComponent = (DisplayComponent) entity.getComponent(myComponentTagResources.getString("Display"));
			dispComponent.doNotShow();
			pathComponent.setReachedEndOfPath(true);
			//create end of path event
			return;
		}
		
		PositionComponent posComponent = (PositionComponent) entity.getComponent(myComponentTagResources.getString("Position"));
		MovementComponent movComponent = (MovementComponent) entity.getComponent(myComponentTagResources.getString("Movement"));
		
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
		
	}
	

}
