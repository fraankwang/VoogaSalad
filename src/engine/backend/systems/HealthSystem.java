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

	@Override
	public void update(SystemSetUp setUp) {

		for (IEntity entity : setUp.getEntitiesWithTag(setUp.getCurrentLevel().getEntities().values(),
				ComponentTagResources.healthComponentTag)) {
			HealthComponent healthComp = (HealthComponent) entity
					.getComponent(ComponentTagResources.healthComponentTag);
			if (healthComp.getHealth() <= 0) {
				addToEventMap(setUp.getMyEventMap(), getDeathEvent(entity), Arrays.asList(entity));
				continue;
			}
			if (healthComp.getHealth() <= healthComp.getCriticalHealth()) {
				addToEventMap(setUp.getMyEventMap(), getCriticalHealthEvent(entity), entity);
				continue;
			}

		}
		
	}

}
