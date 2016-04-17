package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CriticalHealthEvent;
import engine.backend.systems.Events.DeathEvent;

import java.util.Observable;

/**
 * 
 * @author raghavkedia
 *
 */



public class HealthSystem extends Observable implements ISystem{

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity entity : entities){
			if(entity.hasComponent(myComponentTagResources.getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(myComponentTagResources.getString("Health"));
				
				if(healthComp.getHealth() <= healthComp.getCriticalHealth()){
					sendCritialHealthEvent(entity);
					continue;
				}
				
				if(healthComp.getHealth() <= 0){
					sendDeathEvent(entity);
					continue;
				}
			}
		}
	}
	
	private void sendDeathEvent(IEntity deadEntity){
		DeathEvent deathEvent = new DeathEvent(deadEntity);
		notifyObservers(deathEvent);
	}
	
	private void sendCritialHealthEvent(IEntity criticalEntity){
		CriticalHealthEvent criticalHealthEvent = new CriticalHealthEvent(criticalEntity);
		notifyObservers(criticalHealthEvent);
	}


}
