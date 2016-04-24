package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CriticalHealthEvent;
import engine.backend.systems.Events.DeathEvent;
import engine.backend.systems.Events.IEvent;

import java.util.Observable;

/**
 * 
 * @author raghavkedia
 *
 */



public class HealthSystem extends GameSystem{

	@Override
	public void update(Level myLevel,  List<IEvent> myEventList, InGameEntityFactory myEntityFactory, double currentSecond, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		for(IEntity entity : entities){
			if(entity.hasComponent(myComponentTagResources.getString("Health"))){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(myComponentTagResources.getString("Health"));
				
				if(healthComp.getHealth() <= healthComp.getCriticalHealth()){
					myEventList.add(getCritialHealthEvent(entity.getID()));
					continue;
				}
				
				if(healthComp.getHealth() <= 0){
					myEventList.add(getDeathEvent(entity.getID()));
					continue;
				}
			}
		}
	}
	
	private IEvent getDeathEvent(int entityID){
		DeathEvent deathEvent = new DeathEvent(entityID);
		return deathEvent;
	}
	
	private IEvent getCritialHealthEvent(int entityID){
		CriticalHealthEvent criticalHealthEvent = new CriticalHealthEvent(entityID);
		return criticalHealthEvent;
	}


}
