package engine.backend.systems;

/**
 * author raghavkedia
 */

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;

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
		
		map.getPath().updatePositionOnPath(entity, myComponentTagResources);
		
	}
	

}
