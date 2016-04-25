package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.ModeStatistics;
import engine.backend.rules.EntityAction;
import engine.backend.rules.Rule;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.EntityEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.controller.IEngineController;

public class EventManager implements Observer{

	private Level myCurrentLevel;
	IEngineController myEngineController;
	GameWorld myGameWorld;
	ModeStatistics currentModeStatistics;

	private SystemsController mySystemsController;
	private List<Rule> myRuleAgenda;

	public EventManager(IEngineController engineController, GameWorld game) {
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
		// TODO Auto-generated method stub
		handleCustomEvent((IEvent) arg);
	}

	public void sendUpdatedEntity(UpdateEntityEvent myEvent){
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(), myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	public void setCustomEvents(Map<String, List<EntityAction>> myCustomEntityEvents) {
		this.myCustomEntityEvents = myCustomEntityEvents;
	}

	private void handleCustomEvent(IEvent myEvent) {

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

	private void applyActions(Set<Integer> entityIDs, Collection<EntityAction> actions){

		Collection<IEntity> myEntities = new ArrayList<IEntity>();
		for (Integer i : entityIDs) {
			myEntities.add(myCurrentLevel.getEntityWithID(i));
		}
		for (EntityAction a : actions) {
			for (IEntity e: myEntities) {
				if (a.getEntityName().equals(e.getName())) {
					e.applyAction(a);
				}
			}
		}


	}

	//supposed to handle list of events generated in each loop iteration
	public void handleGeneratedEvents(Map<String, Set<Integer>> generatedEventMap){

		for(Rule rule : myRuleAgenda){

			List<Set<Integer>> myPossibleEntities = new ArrayList<Set<Integer>>();
			Collection<IEvent> ruleEvents = rule.getEvents();
			Set<Integer> myFinalEntities;
			for(IEvent event : ruleEvents){
				if(!generatedEventMap.containsKey(event.getEventID())){
					myPossibleEntities.clear();
					break;
				}
				myPossibleEntities.add(generatedEventMap.get(event.getEventID()));
			}
			if (myPossibleEntities.size() > 0) {
				myFinalEntities = new HashSet<Integer>(myPossibleEntities.get(0));
				myPossibleEntities.forEach(e -> myFinalEntities.retainAll(e));
				
				//apply actions
				if(myFinalEntities.size() > 0){
					applyActions(myFinalEntities, rule.getActions());
					
					//remove IDs
					generatedEventMap.values().forEach(s -> s.removeAll(myFinalEntities));
					
				}
				
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

