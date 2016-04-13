package engine.backend.systems;

/**
 * author raghavkedia
 */

import java.util.List;
import java.util.ResourceBundle;

import authoring.backend.factories.InGameEntityFactory;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;

public class MobilizeSystem implements ISystem {
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
		
	}
	
	//Bézier curve
	
	//THINGS TO DO
	//Check for boundaries
	//Check for rules
	//Make default velocity vector
	
	@Override
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		
		for(IEntity entity : entities){
			
			if(!entity.hasComponent(myComponentTagResources.getString("Movement"))){
				continue;
			}
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent(myComponentTagResources.getString("Movement"));
			PositionComponent posComponent = (PositionComponent) entity.getComponent(myComponentTagResources.getString("Position"));
			
			
			if(entity.hasComponent(myComponentTagResources.getString("Path"))){
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
		
//		Map.getPath().updatePositionOnPath(entity);
		
	}
	

}
