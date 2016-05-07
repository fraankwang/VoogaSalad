package engine.backend.systems;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import engine.backend.components.HealthComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.CriticalHealthEvent;
import engine.backend.systems.Events.DeathEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.utilities.ComponentTagResources;

/**
 * 
 * @author raghavkedia
 *
 */

public class HealthSystem extends GameSystem {

	@Override
	public void update(boolean playing, Level myLevel, Map<String, Set<Integer>> myEventMap,
			InGameEntityFactory myEntityFactory, double currentSecond) {

		if (!playing) {
			return;
		}

		Collection<IEntity> applicableEntities = getEntitiesWithTag(myLevel.getEntities().values(),
				ComponentTagResources.healthComponentTag);
		for (IEntity entity : applicableEntities) {
			HealthComponent healthComp = (HealthComponent) entity
					.getComponent(ComponentTagResources.healthComponentTag);
			if (healthComp.getHealth() <= 0) {
				addToEventMap(myEventMap, getDeathEvent(entity), Arrays.asList(entity));
				continue;
			}
			if (healthComp.getHealth() <= healthComp.getCriticalHealth()) {
				addToEventMap(myEventMap, getCriticalHealthEvent(entity), entity);
				continue;
			}

		}
	}

	private IEvent getDeathEvent(IEntity entity) {
		DeathEvent deathEvent = new DeathEvent(entity.getID());
		deathEvent.setEventID(entity.getName());
		return deathEvent;
	}

	private IEvent getCriticalHealthEvent(IEntity entity) {
		CriticalHealthEvent criticalHealthEvent = new CriticalHealthEvent(entity.getID());
		criticalHealthEvent.setEventID(entity.getName());
		return criticalHealthEvent;
	}

}
