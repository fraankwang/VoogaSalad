package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.rules.EntityAction;
import engine.backend.systems.Events.EntityEvent;
import engine.backend.systems.Events.IEvent;

public class EventManager extends Observable implements Observer {

	private Level myCurrentLevel;
	ResourceBundle myComponentTagResources;
	private SystemsController mySystemsController;
	private Map<String, List<EntityAction>> myCustomEntityEvents;

	public EventManager(ResourceBundle myComponentTagResources) {
		this.myComponentTagResources = myComponentTagResources;
		mySystemsController = new SystemsController(this)
	}

	public void setLevel(Level level) {
		myCurrentLevel = level;
		myCustomEntityEvents = level.getCustomEvents();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		handleCustomEntityEvent((IEvent) arg);
	}

	public void setCustomEvents(Map<String, List<EntityAction>> myCustomEntityEvents) {
		this.myCustomEntityEvents = myCustomEntityEvents;
	}

	private void handleCustomEntityEvent(IEvent myEvent) {
		if (myEvent instanceof EntityEvent) {
			EntityEvent myEntityEvent = (EntityEvent) myEvent;
			List<String> identifiers = myEntityEvent.getEntities().stream()
													.map(e -> myCurrentLevel.getEntities().get(e).getName())
													.collect(Collectors.toList());
			List<EntityAction> myActions = checkPossibleIDs(myEvent.getEventID(identifiers));
			if (myActions != null) {
				Collection<Integer> myEntitiesIDs = myEntityEvent.getEntities();
				Collection<IEntity> myEntities = myEntitiesIDs.stream()
															  .map(e -> myCurrentLevel.getEntities().get(e);))
															  .collect(Collectors.toCollection()));
				myActions.forEach(a -> {
					for (IEntity entity : myEntities) {
						if (a.getEntityName().equals(entity.getName())) {
							entity.applyAction(a, myComponentTagResources);
						}
					}
				});
			}
		}
	}

	private List<EntityAction> checkPossibleIDs(List<String> ids) {
		for (String id : ids) {
			if (myCustomEntityEvents.get(id) != null) {
				return myCustomEntityEvents.get(id);
			}
		}
		return null;
	}

	public void handleAddEntity(IEvent myEvent) {
		//we have a problem :/
//		EntityEvent myEntityEvent = (EntityEvent) myEvent;
//		myEntityEvent.getEntities().stream()
//			forEach(e -> myCurrentLevel.addToEntities(e));
	}

	public void handleEnemyMissed() {
		// gets events, send event to level manager
	}
}
