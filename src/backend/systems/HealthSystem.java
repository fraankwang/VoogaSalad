package backend.systems;

import java.util.List;

import backend.game_object.components.HealthComponent;
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;

/**
 * 
 * @author raghavkedia
 *
 */

public class HealthSystem extends Systemm implements ISystem{

	@Override
	public void update(List<Entity> entities) {
		// TODO Auto-generated method stub
		
		for(IEntity entity : entities){
			if(entity.hasComponent("Health")){
				HealthComponent healthComp = (HealthComponent) entity.getComponent("Health");
				healthComp.setHealth(healthComp.getHealth() - healthComp.getDamage());
			}
		}
		
	}


}
