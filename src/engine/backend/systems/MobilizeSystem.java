package engine.backend.systems;

/**
 * author raghavkedia
 */

import java.util.List;

import engine.backend.components.*;
import engine.backend.entities.IEntity;

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
			
			
			if(entity.hasComponent(getComponentTagResources().getString("Path"))){
				//if on path
				updatePathMovement(entity);
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
			
			
		}

	}
	
	private void updatePathMovement(IEntity entity){
		//needs access to path
		
		Map.getPath().updatePositionOnPath(entity);
		
	}
	

}
