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
import backend.game_object.entities.IEntity;

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
	public void update(List<IEntity> entities) {
		
		for(IEntity entity : entities){
			
			if(!entity.hasComponent(getComponentTagResources().getString("Movement"))){
				continue;
			}
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent(getComponentTagResources().getString("Movement"));
			PositionComponent posComponent = (PositionComponent) entity.getComponent(getComponentTagResources().getString("Position"));
			
			//movement if on path
			Vector velVector = movComponent.getCurrentVelocityVector();
			double speed = velVector.calculateMagnitude();
			
			
			//do movement
			Vector posVector = posComponent.getPositionVector();
//			Vector velVector = movComponent.getCurrentVelocityVector();
			posVector.add(velVector);
			
			//do rotation
			double theta = movComponent.getTheta();
			double omega = movComponent.getCurrentOmega();
			movComponent.setTheta(theta+omega);
			
			
		}

	}

	

}
