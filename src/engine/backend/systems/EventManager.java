package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.ModeStatistics;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import engine.backend.rules.Rule;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.controller.IEngineController;

public class EventManager implements Observer{

	IEngineController myEngineController;
	GameWorld myGameWorld;
	ModeStatistics currentModeStatistics;
	private List<Rule> myRuleAgenda;

	public EventManager(IEngineController engineController, GameWorld game, ModeStatistics stats) {
		setLevel(game.getLevelWithId(0));
		myEngineController = engineController;
		myGameWorld = game;
		//pass in right values
		currentModeStatistics = stats;
	}

	public void setLevel(Level level) {
		setCustomRules(level.getRuleAgenda());
	}

	public Level getCurrentLevel() {
		return myGameWorld.getLevelWithId(currentModeStatistics.getCurrentLevelIndex());
	}

	@Override
	public void update(Observable o, Object arg) {
		//System.out.println(arg.getClass().getName()); 
		handleCustomEvent((IEvent) arg);
	}

	public void sendUpdatedEntity(UpdateEntityEvent myEvent){
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(), myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	public void setCustomRules(List<Rule> rules) {
		this.myRuleAgenda = rules;
	}

	private void handleCustomEvent(IEvent myEvent) {

		if(myEvent instanceof UpdateEntityEvent){
			sendUpdatedEntity((UpdateEntityEvent) myEvent);
			return;
		}

		if(myEvent instanceof AddEntityEvent){
			handleAddEntityEvent(myEvent);
		}

	}
	
	public void handleClickEvent(EntityClickedEvent event) {
		String identifier = getCurrentLevel().getEntityWithID(event.getClickedEntityID()).getName();
		event.setEventID(identifier);
		
		for(Rule rule : myRuleAgenda){
			ArrayList<String> ruleEvents = (ArrayList<String>) rule.getEvents();
			if(ruleEvents.size() == 1 && ruleEvents.get(0).equals(event.getEventID())){
				applyActions(new HashSet<Integer>(event.getClickedEntityID()), rule.getActions());
			}
		}
		
	}
	
	private void applyActions(Set<Integer> entityIDs, Collection<IAction> actions){

		Collection<IEntity> myEntities = new ArrayList<IEntity>();
		for (Integer i : entityIDs) {
			myEntities.add(getCurrentLevel().getEntityWithID(i));
		}
		for (IAction a : actions) {
			if(a instanceof EntityAction){
				for (IEntity e: myEntities) {
					if (((EntityAction) a).getEntityName().equals(e.getName())) {
						e.applyAction((EntityAction) a);
					}
				}
			}
			else if(a instanceof LevelAction){
				currentModeStatistics.applyAction((LevelAction) a);
			}
		}


	}

	//supposed to handle list of events generated in each loop iteration
	public void handleGeneratedEvents(Map<String, Set<Integer>> generatedEventMap){

		for(Rule rule : myRuleAgenda){

			List<Set<Integer>> myPossibleEntities = new ArrayList<Set<Integer>>();
			Collection<String> ruleEvents = rule.getEvents();
			Set<Integer> myFinalEntities;
			for(String event : ruleEvents){
				if(!generatedEventMap.containsKey(event)){
					myPossibleEntities.clear();
					break;
				}
				myPossibleEntities.add(generatedEventMap.get(event));
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

	public void handleAddEntityEvent(IEvent myEvent) {
		AddEntityEvent event = (AddEntityEvent) myEvent;
		getCurrentLevel().addEntityToMap(event.getNewEntities());
	}

	public void handleEnemyMissed() {
		// gets events, send event to level manager

	}

	public GameWorld getGameWorld(){
		return myGameWorld;

	}

	public ModeStatistics getModeStatistics() {
		// TODO Auto-generated method stub
		return currentModeStatistics;
	}

}

