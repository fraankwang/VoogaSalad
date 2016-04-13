package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;

/**
 * 
 * @author raghavkedia
 *
 */

public class HealthSystem implements ISystem{

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity entity : entities){
			if(entity.hasComponent(myComponentTagResources.getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(myComponentTagResources.getString("Health"));
				healthComp.setHealth(healthComp.getHealth() - healthComp.getDamage());
				healthComp.setDamage(0);
			}
		}
	}


}
