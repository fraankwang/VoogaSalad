package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import authoring.backend.factories.InGameEntityFactory;
import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;

/**
 * 
 * @author raghavkedia
 *
 */

public class HealthSystem implements ISystem{

	@Override
	public void update(List<IEntity> entities, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		for(IEntity entity : entities){
			if(entity.hasComponent(myComponentTagResources.getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(myComponentTagResources.getString("Health"));
				healthComp.setHealth(healthComp.getHealth() - healthComp.getDamage());
				healthComp.setDamage(0);
			}
		}
	}


}
