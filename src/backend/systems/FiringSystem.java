package backend.systems;

import java.util.List;

import backend.game_object.components.FiringComponent;
import backend.game_object.components.PositionComponent;
import backend.game_object.components.Vector;
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;

public class FiringSystem extends Systemm implements ISystem{

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		
		for(IEntity shootingEntity : entities){
			
			if(shootingEntity.hasComponent("Firing")){
				
				for(IEntity targetEntity : entities){
					
					if(shootingEntity.equals(targetEntity)){
						continue;
					}
					
					//needs to check if it's something it can fire at
					if(targetEntity.getLabel().equals("Enemy")){
						
						handleFiring(shootingEntity, targetEntity);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public void handleFiring(IEntity shootingEntity, IEntity targetEntity){
		
		FiringComponent firingComponent = (FiringComponent) shootingEntity.getComponent("Firing");
		PositionComponent shootingPosComponent = (PositionComponent) shootingEntity.getComponent("Position");
		PositionComponent targetPosComponent = (PositionComponent) targetEntity.getComponent("Position");
		
		Vector shootingPosVector = shootingPosComponent.getPositionVector();
		Vector targetPosVector = targetPosComponent.getPositionVector();
		
		if(shootingPosVector.calculateDistance(targetPosVector) <= firingComponent.getEnemyInSightRange()){
			//fire
		}

	}

}
