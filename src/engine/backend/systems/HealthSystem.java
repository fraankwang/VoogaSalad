package engine.backend.systems;

import java.util.Collection;
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



public class HealthSystem extends GameSystem{

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		for(IEntity entity : entities){
			if(entity.hasComponent(myComponentTagResources.getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(myComponentTagResources.getString("Health"));
				
				if(healthComp.getHealth() <= healthComp.getCriticalHealth()){
					sendCritialHealthEvent(entity.getID());
					continue;
				}
				
				if(healthComp.getHealth() <= 0){
					sendDeathEvent(entity.getID());
					continue;
				}
			}
		}
	}
	
	private void sendDeathEvent(int entityID){
		DeathEvent deathEvent = new DeathEvent(entityID);
		notifyObservers(deathEvent);
	}
	
	private void sendCritialHealthEvent(int entityID){
		CriticalHealthEvent criticalHealthEvent = new CriticalHealthEvent(entityID);
		notifyObservers(criticalHealthEvent);
	}


}
