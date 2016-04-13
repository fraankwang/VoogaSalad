package engine.backend.systems;

import java.util.List;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;

/**
 * 
 * @author raghavkedia
 *
 */

public class HealthSystem extends Systemm implements ISystem{

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		for(IEntity entity : entities){
			if(entity.hasComponent(getComponentTagResources().getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(getComponentTagResources().getString("Health"));
				healthComp.setHealth(healthComp.getHealth() - healthComp.getDamage());
				healthComp.setDamage(0);
			}
		}
	}


}
