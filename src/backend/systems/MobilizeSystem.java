package backend.systems;

import java.util.ArrayList;
import java.util.List;

import backend.game_object.components.Component;
import backend.game_object.components.MovementComponent;
import backend.game_object.components.*;
import backend.game_object.entities.Entity;

public class MobilizeSystem extends Systems {
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
		
	}
	
	//THINGS TO DO
	//Check for boundaries
	//Check for rules
	
	@Override
	public void update(List<Entity> entities) {

		List<Entity> movableEntities = filter(entities, );
		
		for(Entity entity : movableEntities){
			
			MovementComponent movComponent = (MovementComponent) entity.getComponent("Movement");
			PositionComponent posComponent = (PositionComponent) entity.getComponent("Position");
			
			if(movComponent.canMove()){
				//do movement
				Vector posVector = posComponent.getPositionVector();
				Vector velVector = movComponent.getVelocityVector();
				posVector.add(velVector);
			}
			if(movComponent.canRotate()){
				//do rotation
				double theta = movComponent.getTheta();
				double angle = movComponent.getAngle();
				movComponent.setTheta(theta+angle);
			}
			
		}

	}
	
	public void updateRotations(List<Entity> rotatableEntities){
		
	}
	
	public void updateMovements(List<Entity> movableEntities){
		
	}
	
	
	

}
