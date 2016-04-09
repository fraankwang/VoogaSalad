package backend.systems;

/**
 * author raghavkedia
 */

import java.util.ArrayList;
import java.util.List;

import backend.game_object.components.Component;
import backend.game_object.components.MovementComponent;
import backend.Level;
import backend.game_object.components.*;
import backend.game_object.entities.Entity;

public class MobilizeSystem extends Systemm implements ISystem {
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
		
	}
	
	//Bézier curve
	
	//THINGS TO DO
	//Check for boundaries
	//Check for rules
	//Make default velocity vector
	
	@Override
	public void update(List<Entity> entities) {

		List<Entity> movableEntities = filter(entities, );
		
		for(Entity entity : movableEntities){
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent("Movement");
			PositionComponent posComponent = (PositionComponent) entity.getComponent("Position");
			
			//movement if on path
			Vector velVector = movComponent.getCurrentVelocityVector();
			double speed = velVector.calculateMagnitude();
			
			
			//do movement
			Vector posVector = posComponent.getPositionVector();
			Vector velVector = movComponent.getCurrentVelocityVector();
			posVector.add(velVector);
			
			//do rotation
			double theta = movComponent.getTheta();
			double omega = movComponent.getCurrentOmega();
			movComponent.setTheta(theta+omega);
			
			
		}

	}
	
	public void updateRotations(List<Entity> rotatableEntities){
		
	}
	
	public void updateMovements(List<Entity> movableEntities){
		
	}

	@Override
	public void execute(List<Level> list) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
