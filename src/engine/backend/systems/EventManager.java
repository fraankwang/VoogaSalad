package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.rules.Action;
import engine.backend.systems.Events.IEvent;


public class EventManager extends Observable implements Observer {

	private Level myCurrentLevel;
	ResourceBundle myComponentTagResources;
	private SystemsController mySystemsController;
	private Map<String, List<Action>> myCustomEvents;

	public EventManager(ResourceBundle myComponentTagResources) {
		this.myComponentTagResources = myComponentTagResources;
		mySystemsController = new SystemsController(this)
	}
	
	public void setLevel(Level level) {
		myCurrentLevel = level;
		myCustomEvents = level.getCustomEvents();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		handleCustomEvent((IEvent) arg);
	}

	public void setCustomEvents(Map<String, List<Action>> myCustomEvents) {
		this.myCustomEvents = myCustomEvents;
	}

	private void handleCustomEvent(IEvent myEvent) {
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
			if(myCustomEvents.get(id) != null) {
				return myCustomEvents.get(id);
			}
		}
		return null;
	}
	
	public void handleAddEntity(IEvent myEvent) { 
		myEvent.getEntities().forEach(e -> myCurrentLevel.addToEntities(e));
	}
	
	public void handleEnemyMissed(){
		//gets events, send event to level manager
	}
}
