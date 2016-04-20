package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.rules.Action;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.UpdateEntityEvent;

public class EventManager extends Observable implements Observer {

	private Level myCurrentLevel;
	ResourceBundle myComponentTagResources;
	private Map<String, List<Action>> myCustomEvents;
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	IEngineController myEngineController;
	GameWorld myGameWorld;

	public EventManager(IEngineController engineController, GameWorld game) {
		this.myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");
		myGameWorld = game;
	}

	public void setCurrentLevel(Level level) {
		myCurrentLevel = level;
		myCustomEvents = level.getCustomEvents();
	}

	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		handleCustomEvent((IEvent) arg);
	}
	
	public void sendUpdatedEntity(UpdateEntityEvent myEvent){
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	public void setCustomEvents(Map<String, List<Action>> myCustomEvents) {
		this.myCustomEvents = myCustomEvents;
	}

	private void handleCustomEvent(IEvent myEvent) {
		
		if(myEvent instanceof UpdateEntityEvent){
			sendUpdatedEntity((UpdateEntityEvent) myEvent);
		}
		
		List<Action> myActions = checkPossibleIDs(myEvent.getEventID());
		if (myActions != null) {
			Collection<IEntity> myEntities = myEvent.getEntities();
			myActions.forEach(a -> {
				for (IEntity entity : myEntities) {
					if (a.getEntityName().equals(entity.getName())) {
						entity.applyAction(a, myComponentTagResources);
					}
				}
			});
		}
	}

	private List<Action> checkPossibleIDs(String[] ids) {
		for (String id : ids) {
			if (myCustomEvents.get(id) != null) {
				return myCustomEvents.get(id);
			}
		}
		return null;
	}

	public void handleAddEntity(IEvent myEvent) {
		myEvent.getEntities().forEach(e -> myCurrentLevel.addToEntities(e));
	}

	public void handleEnemyMissed() {
		// gets events, send event to level manager
	}
}
