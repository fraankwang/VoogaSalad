package engine.backend.systems;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CriticalHealthEvent;
import engine.backend.systems.Events.DeathEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.utilities.ComponentTagResources;

import java.util.Observable;

/**
 * 
 * @author raghavkedia
 *
 */



public class HealthSystem extends GameSystem{

	@Override
	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub
		Collection<IEntity> entities = myLevel.getEntities().values();
		for(IEntity entity : entities){
			if(entity.hasComponent(ComponentTagResources.healthComponentTag)){
				HealthComponent healthComp = (HealthComponent) entity.getComponent(ComponentTagResources.healthComponentTag);
				
				if(healthComp.getHealth() <= healthComp.getCriticalHealth()){
					IEvent event = getDeathEvent(entity); 
					addToEventMap(myEventMap, event, Arrays.asList(entity));
					continue;
				}
				
				if(healthComp.getHealth() <= 0){
					IEvent event = getCriticalHealthEvent(entity);
					addToEventMap(myEventMap, event, Arrays.asList(entity));
					continue;
				}
			}
		}
	}

	
	private IEvent getDeathEvent(IEntity entity){
		DeathEvent deathEvent = new DeathEvent(entity.getID());
		deathEvent.setEventID(entity.getName());
		return deathEvent;
	}
	
	private IEvent getCriticalHealthEvent(IEntity entity){
		CriticalHealthEvent criticalHealthEvent = new CriticalHealthEvent(entity.getID());
		criticalHealthEvent.setEventID(entity.getName());
		return criticalHealthEvent;
	}


}
