package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.ModeStatistics;
import engine.backend.rules.EntityAction;
import engine.backend.systems.Events.*;
import engine.controller.IEngineController;

public class EventManager implements Observer {

	private Level myCurrentLevel;
	ResourceBundle myComponentTagResources;
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	IEngineController myEngineController;
	GameWorld myGameWorld;
	ModeStatistics currentModeStatistics;

	private SystemsController mySystemsController;
	private Map<String, List<EntityAction>> myCustomEntityEvents;

	public EventManager(IEngineController engineController, GameWorld game) {
		this.myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");
		setLevel(game.getLevelWithId(0));
		myEngineController = engineController;
		myGameWorld = game;
		//pass in right values
		//currentModeStatistics = new ModeStatistics();
	}

	public void setLevel(Level level) {
		myCurrentLevel = level;
		myCustomEntityEvents = level.getCustomEvents();
	}

	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg.getClass().getName()); 
		handleCustomEvent((IEvent) arg);
	}
	
	public void sendUpdatedEntity(UpdateEntityEvent myEvent){
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(), myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	public void setCustomEvents(Map<String, List<EntityAction>> myCustomEntityEvents) {
		this.myCustomEntityEvents = myCustomEntityEvents;
	}

	private void handleCustomEvent(IEvent myEvent) {
		System.out.println(myEvent.getClass().getName());
		if(myEvent instanceof UpdateEntityEvent){
			sendUpdatedEntity((UpdateEntityEvent) myEvent);
			return;
		}
		
		if(myEvent instanceof AddEntityEvent){
			handleAddEntityEvent(myEvent);
		}
		
		if (myEvent instanceof EntityEvent) {
			EntityEvent myEntityEvent = (EntityEvent) myEvent;
			List<String> identifiers = myEntityEvent.getEntities().stream()
													.map(id -> myCurrentLevel.getEntities().get(id).getName())
													.collect(Collectors.toList());
			List<EntityAction> myActions = checkPossibleIDs(myEvent.getEventID(identifiers));
			if (myActions != null) {
				Collection<Integer> myEntitiesIDs = myEntityEvent.getEntities();
				Collection<IEntity> myEntities = myEntitiesIDs.stream()
															  .map(e -> myCurrentLevel.getEntities().get(e);))
															  .collect(Collectors.toCollection()));
				myActions.forEach(a -> {
					myEntities.stream()
							  .filter(e -> a.getEntityName().equals(e.getName()))
							  .forEach(e -> e.applyAction(a, myComponentTagResources));
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

	public void handleAddEntityEvent(IEvent myEvent) {
		AddEntityEvent event = (AddEntityEvent) myEvent;
		myCurrentLevel.addEntityToMap(event.getNewEntities());
	}

	public void handleEnemyMissed() {
		// gets events, send event to level manager

	}
	
	public GameWorld getGameWorld(){
		return myGameWorld;

	}
}

