package engine.backend.systems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import backend.xml_converting.GameWorldToXMLWriter;
import backend.xml_converting.ObjectToXMLWriter;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_features.GameShop;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.IModifiable;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.game_object.GameStatistics;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import engine.backend.rules.Rule;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.backend.systems.Events.GameEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.NextWaveEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.backend.systems.Events.WaveOverEvent;
import engine.backend.utilities.ComponentTagResources;
import engine.controller.IEngineController;

public class EventManager implements Observer {
	
	private static final int ZERO = 0;
	
	IEngineController myEngineController;
	GameWorld myGameWorld;
	GameStatistics currentModeStatistics;
	private List<Rule> myRuleAgenda;
	InGameEntityFactory myEntityFactory;
	private GameShop myGameShop;


	public EventManager(IEngineController engineController, GameWorld game) {
		myEngineController = engineController;
		myGameWorld = game;
		// pass in right values
		currentModeStatistics = game.getGameStatistics();
		myGameShop = new GameShop();
	}

	public void setEntityFactory(InGameEntityFactory factory) {
		myEntityFactory = factory;
	}

	public void setLevelRules(Level level) {
		setCustomRules(level.getRuleAgenda());
	}

	public Level getCurrentLevel() {
		return myGameWorld.getLevelWithId(currentModeStatistics.getCurrentMode(),
				currentModeStatistics. getCurrentLevelIndex());
	}
	
	public Mode getCurrentMode() {
		return myGameWorld.getModes().get(currentModeStatistics.getCurrentMode());
	}

	public void updateGameShop() {
		myGameShop.setShopItems(getCurrentLevel().getShopItems());
		myGameShop.updateShop(currentModeStatistics.getCurrentResources());
		myEngineController.updateShop(myGameShop.getShopItems());
	}

	@Override
	public void update(Observable o, Object arg) {
		// System.out.println(arg.getClass().getName());
		handleCustomEvent((IEvent) arg);
	}

	public void sendUpdatedEntity(UpdateEntityEvent myEvent) {
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(),
				myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	public void setCustomRules(List<Rule> rules) {
		this.myRuleAgenda = rules;
	}

	private void handleCustomEvent(IEvent myEvent) {

		if (myEvent instanceof UpdateEntityEvent) {
			sendUpdatedEntity((UpdateEntityEvent) myEvent);
			return;
		}

		if (myEvent instanceof AddEntityEvent) {
			handleAddEntityEvent(myEvent);
		}

		if (myEvent instanceof WaveOverEvent) {
			handleWaveOverEvent((WaveOverEvent) myEvent);
		}
		
		if (myEvent instanceof NextWaveEvent) {
			handleNextWaveEvent((NextWaveEvent) myEvent);
		}

	}
	
	public void handleUserInputEvent(IEvent myEvent){
		
		if(myEvent instanceof GameEvent){
			handleGameStartEvent((GameEvent) myEvent);
		}
		else if(myEvent instanceof EntityClickedEvent){
			handleClickEvent((EntityClickedEvent) myEvent);
		}
		
		else if(myEvent instanceof KeyPressedEntityEvent){
			handleKeyPressedEvent((KeyPressedEntityEvent) myEvent);
		}
		
		else if(myEvent instanceof EntityDroppedEvent){
			handleEntityDropEvent((EntityDroppedEvent) myEvent);
		}
		
		else if(myEvent instanceof NextWaveEvent){
			handleNextWaveEvent((NextWaveEvent) myEvent);
		}
		
	}

	/**
	 * Handles setting the mode and level when clicked.
	 * @param modeName
	 * @param level
	 * @throws IOException
	 */
	public void handleGameStartEvent(GameEvent event) throws IOException {
		handleModeClickedEvent(event.getModeName());
		handleLevelClickedEvent(event.getLevel());
	}
	
	/**
	 * Handles when a mode has been selected.
	 * @param modeName
	 */
	private void handleModeClickedEvent(String modeName) {
		currentModeStatistics.setCurrentMode(modeName);
	}
	
	/**
	 * Handles when a level has been selected.
	 * 
	 * @param Level
	 * @throws IOException
	 */
	private void handleLevelClickedEvent(int level) throws IOException {
		currentModeStatistics.setCurrentLevelIndex(level);
		serializeLevel();
	}

	/**
	 * Handles when user goes to the next level.
	 * 
	 */
	private void handleGoToNextLevelEvent() {
		currentModeStatistics.setCurrentLevelIndex(currentModeStatistics.getCurrentLevelIndex() + 1);
		serializeLevel();
	}

	private void serializeLevel() {
		myGameWorld.getLevelWithId(currentModeStatistics.getCurrentMode(), currentModeStatistics.getCurrentLevelIndex())
				.setLastSerializedVersion(serializeLevel(
						myGameWorld.getLevelWithId(currentModeStatistics.getCurrentMode(),
								currentModeStatistics.getCurrentLevelIndex()),
						myGameWorld.getGameType() + currentModeStatistics.getCurrentLevelIndex()));
	}
	
	private String serializeLevel(Object o, String fileName) {
		ObjectToXMLWriter serializer = new GameWorldToXMLWriter();
		return serializer.getXMLfromObject(o);
	}
	
	private void handleWaveOverEvent(WaveOverEvent event) {
		int index = getCurrentLevel().getCurrentWaveIndex();
		// last wave, level is over, send whether level is won or not
		if (index == getCurrentLevel().getNumWaves() - 1) {
			boolean won = currentModeStatistics.getCurrentNumLives() > ZERO;
			myEngineController.levelIsOver(won);
		} else {
			myEngineController.waveIsOver();
			getCurrentLevel().setCurrentWaveIndex(index + 1);
		}

	}

	private void handleNextWaveEvent(NextWaveEvent event) {
		getCurrentLevel().setSendNextWave(true);
	}

	/**
	 * Creates a new entity from the entity factory, sets the position component, and adds 
	 * entity to screen map.
	 * @param event
	 */
	private void handleEntityDropEvent(EntityDroppedEvent event) {
		
		double value = event.getEntityValue();
		currentModeStatistics.setCurrentResources(Double.toString(value));
		
		IEntity newEntity = myEntityFactory.createEntity(event.getEntityName());
		PositionComponent posComp = (PositionComponent) newEntity.getComponent(ComponentTagResources.positionComponentTag);
		posComp.setPositionVector(new Vector(event.getXCoordinate(), event.getYCoordinate()));
		getCurrentLevel().addEntityToMap(newEntity);
	}

	private void handleClickEvent(EntityClickedEvent event) {

		String identifier = getCurrentLevel().getEntityWithID(event.getClickedEntityID()).getName();
		event.setEventID(identifier);
		IEntity entity = getCurrentLevel().getEntityWithID(event.getClickedEntityID());
		((Observable) entity).addObserver(event.getCurrentView());

		for (Rule rule : myRuleAgenda) {
			ArrayList<String> ruleEvents = (ArrayList<String>) rule.getEvents();
			if (ruleEvents.size() == 1 && ruleEvents.get(0).equals(event.getEventID())) {
				applyActions(entity, rule.getActions());
			}
		}
		entity.broadcastEntity();
	}

	public void updateEntityFactory() {
		if (myEntityFactory.isCurrent(getCurrentLevel().getIndex())) {
			return;
		}
		myEntityFactory.setEntities(getCurrentLevel().getAuthoredEntities());
		myEntityFactory.setID(getCurrentLevel().getIndex());
		return;
	}

	private void applyActions(IEntity entity, Collection<IAction> actions) {

		for (IAction a : actions) {
			if (a instanceof EntityAction) {

				if (((EntityAction) a).getEntityName().equals(entity.getName())) {
					((IModifiable) entity).applyAction((EntityAction) a);
				}

			} else if (a instanceof LevelAction) {
				currentModeStatistics.applyAction((LevelAction) a);
			}
		}
	}

	private void applyActions(Collection<Integer> entityIDs, Collection<IAction> actions) {

		Collection<IEntity> myEntities = new ArrayList<IEntity>();
		entityIDs.forEach(i -> myEntities.add(getCurrentLevel().getEntityWithID(i)));
		for (IAction action : actions) {
			if (action instanceof EntityAction) {

				myEntities.stream()
						  .filter(e -> ((EntityAction) action).getEntityName().equals(e.getName()))
						  .forEach(e -> ((IModifiable) e).applyAction(action));
								  
			} else if (action instanceof LevelAction) {
				currentModeStatistics.applyAction(action);
			}
		}

	}

	// supposed to handle list of events generated in each loop iteration
	public void handleGeneratedEvents(Map<String, Set<Integer>> generatedEventMap) {

		for (Rule rule : myRuleAgenda) {

			List<Set<Integer>> myPossibleEntities = new ArrayList<Set<Integer>>();
			Collection<String> ruleEvents = rule.getEvents();
			Set<Integer> myFinalEntities;
			for (String event : ruleEvents) {
				if (!generatedEventMap.containsKey(event)) {
					myPossibleEntities.clear();
					break;
				}
				myPossibleEntities.add(generatedEventMap.get(event));
			}
			if (myPossibleEntities.size() > 0) {
				myFinalEntities = new HashSet<Integer>(myPossibleEntities.get(0));
				myPossibleEntities.forEach(e -> myFinalEntities.retainAll(e));

				// apply actions
				if (myFinalEntities.size() > 0) {
					applyActions(myFinalEntities, rule.getActions());

					// remove IDs
					generatedEventMap.values().forEach(s -> s.removeAll(myFinalEntities));

				}

			}

		}
	}

	private void handleAddEntityEvent(IEvent myEvent) {
		AddEntityEvent event = (AddEntityEvent) myEvent;
		getCurrentLevel().addEntityToMap(event.getNewEntities());
	}

	private void handleEnemyMissed() {

	}

	public GameWorld getGameWorld() {
		return myGameWorld;

	}

	public GameStatistics getModeStatistics() {
		return currentModeStatistics;
	}

	public InGameEntityFactory getEntityFactory() {
		return myEntityFactory;
	}

	public void initializeRules() {
		myRuleAgenda = getCurrentLevel().getRuleAgenda();
	}

}
