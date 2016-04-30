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
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.backend.systems.Events.GameEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.NextWaveEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.backend.systems.Events.WaveOverEvent;
import engine.backend.utilities.ComponentTagResources;
import engine.controller.IEngineController;

public class EventManager implements Observer {

	IEngineController myEngineController;
	GameWorld myGameWorld;
	GameStatistics currentModeStatistics;
	private List<Rule> myRuleAgenda;
	InGameEntityFactory myEntityFactory;
	private GameShop myGameShop;

	public EventManager(IEngineController engineController, GameWorld game) {
		myEngineController = engineController;
		myGameWorld = game;
		currentModeStatistics = game.getGameStatistics();
		myGameShop = new GameShop();
	}

	/**
	 * Sets the in game entity factory creator to the given factory.
	 * @param factory
	 */
	public void setEntityFactory(InGameEntityFactory factory) {
		myEntityFactory = factory;
	}

	/**
	 * Sets the level rules to those for the current level.
	 * @param level
	 */
	public void setLevelRules(Level level) {
		setCustomRules(level.getRuleAgenda());
	}

	/**
	 * If the current level is not reverted when it should be, revert occurs before returning.
	 * @return The current level.
	 */
	public Level getCurrentLevel() {
		String modeName = currentModeStatistics.getCurrentMode();
		int levelIndex = currentModeStatistics.getCurrentLevelIndex();		
		Level myLevel = myGameWorld.getLevelWithId(modeName, levelIndex);
		revertLevelIfNeeded(myLevel, modeName, levelIndex);
		return myLevel;
	}

	private void revertLevelIfNeeded(Level myLevel, String modeName, int levelIndex) {
		if (myLevel.shouldRevert()) {
			GameWorldToXMLWriter serializer = new GameWorldToXMLWriter();
			myLevel = (Level) serializer
					.xMLToObject(myGameWorld.getLevelWithId(modeName, levelIndex).getLastSerializedVersion());
			myLevel.setShouldRevert(false);
			myGameWorld.putLevelInMap(modeName, levelIndex, myLevel);
		}
	}

	/**
	 * 
	 * @return The current mode being played.
	 */
	public Mode getCurrentMode() {
		return myGameWorld.getModes().get(currentModeStatistics.getCurrentMode());
	}

	/**
	 * Updates the game shop by setting the shop items to those for the current level. 
	 * Also updates the shop so only those allowed with the given resources is displayed.
	 * Calls engine controller to update the shop.
	 */
	public void updateGameShop() {
		myGameShop.setShopItems(getCurrentLevel().getShopItems());
		myGameShop.updateShop(currentModeStatistics.getCurrentResources());
		myEngineController.updateShop(myGameShop.getShopItems());
	}

	@Override
	public void update(Observable o, Object arg) {
		handleSystemEvent((IEvent) arg);
	}
	
	/**
	 * Receives an update entity event and sends the engine controller information to update
	 * the entity.
	 * @param myEvent
	 */
	public void sendUpdatedEntity(UpdateEntityEvent myEvent) {
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(),
				myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	/**
	 * Sets the rule agenda for each level to the list of rules given. 
	 * @param rules
	 */
	public void setCustomRules(List<Rule> rules) {
		this.myRuleAgenda = rules;
	}

	private void handleSystemEvent(IEvent myEvent) {

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

	}

	/**
	 * Handles setting the mode and level when clicked.
	 * 
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
	 * 
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
	public void handleGoToNextLevelEvent() {
		currentModeStatistics.setCurrentLevelIndex(currentModeStatistics.getCurrentLevelIndex() + 1);
		serializeLevel();
		updateEntityFactory();
	}

	private void serializeLevel() {
		System.out.println("level serialized");
		String modeName = currentModeStatistics.getCurrentMode();
		int levelIndex = currentModeStatistics.getCurrentLevelIndex();
		myGameWorld.getLevelWithId(modeName, levelIndex)
				.setLastSerializedVersion(serializeLevel(myGameWorld.getLevelWithId(modeName, levelIndex)));
	}

	private String serializeLevel(Object o) {
		ObjectToXMLWriter serializer = new GameWorldToXMLWriter();
		return serializer.getXMLfromObject(o);
	}

	private void handleWaveOverEvent(WaveOverEvent event) {
		myEngineController.waveIsOver();
	}

	/**
	 * Handles whether a level is over by checking if there are no more lives. If no more lives,
	 * engine controller is told that the level is lost and the level played is reset. 
	 * If level is won, then the lives left and level resources is saved in the statistics and
	 * the level is reset.
	 */
	public void handleLevelOver() {

		boolean noLives = currentModeStatistics.noMoreLives();
		if (noLives) {
			myEngineController.levelIsLost();
			resetLevel();
		}
		else{
			if(getCurrentLevel().lastWaveOver()){
				currentModeStatistics.addEndOfLevelLives(currentModeStatistics.getCurrentNumLives());
				currentModeStatistics.addEndOfLevelResources(currentModeStatistics.getCurrentResources());
				myEngineController.levelIsWon();
				resetLevel();
			} else {
				return;
			}
		}

	}

	private void resetLevel() {
		String modeName = currentModeStatistics.getCurrentMode();
		int levelIndex = currentModeStatistics.getCurrentLevelIndex();
		myGameWorld.getLevelWithId(modeName, levelIndex).setShouldRevert(true);
	}

	private void handleNextWaveEvent(NextWaveEvent event) {
		getCurrentLevel().setSendNextWave(true);
	}

	/**
	 * Creates a new entity from the entity factory, sets the position
	 * component, and adds entity to screen map.
	 * 
	 * @param event
	 */
	private void handleEntityDropEvent(EntityDroppedEvent event) {

		double value = event.getEntityValue();
		currentModeStatistics.setCurrentResources(Double.toString(value));

		IEntity newEntity = myEntityFactory.createEntity(event.getEntityName());
		PositionComponent posComp = (PositionComponent) newEntity
				.getComponent(ComponentTagResources.positionComponentTag);
		posComp.setPositionVector(new Vector(event.getXCoordinate(), event.getYCoordinate()));
		getCurrentLevel().addEntityToMap(newEntity);
	}

	/**
	 * Updates the factory by checking if the entity factory is correct for the current level.
	 * If not, the entity factory is set to the authored entities for the level and given
	 * the current level index and initial entities.
	 */
	public void updateEntityFactory() {
		if (myEntityFactory.isCurrent(getCurrentLevel().getIndex())) {
			return;
		}
		myEntityFactory.setEntities(getCurrentLevel().getAuthoredEntities());
		myEntityFactory.setID(getCurrentLevel().getIndex()); 
		myEntityFactory.setInitNumEntities(getCurrentLevel().getNumEntities());
		return;
	}

	/**
	 * Applies actions to an entity if applicable, else sees if it is a level
	 * action then changes things accordingly.
	 * 
	 * @param entity
	 * @param actions
	 */
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
		myEntities.forEach(entity -> applyActions(entity, actions));
	}

	/**
	 * Takes in collection of non map user events generated, and handles them
	 * accordingly
	 * 
	 * @param events
	 */
	public void handleNonMapEvents(Collection<IEvent> events) {

		for (IEvent event : events) {
			if (event instanceof EntityDroppedEvent) {
				System.out.println(event.getEventID());
				handleEntityDropEvent((EntityDroppedEvent) event);
			} else if (event instanceof NextWaveEvent) {
				handleNextWaveEvent((NextWaveEvent) event);
			}
		}

	}

	/**
	 * Takes a generated map of eventIDs and entityIDs for each eventID and finds
	 * whether a rule in the agenda has the entities and event necessary to carryout
	 * the actions of each rule. If so, the actions are applied.
	 * @param generatedEventMap
	 */
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

	/**
	 * 
	 * @return The current instance of the GameWorld.
	 */
	public GameWorld getGameWorld() {
		return myGameWorld;

	}

	/**
	 * 
	 * @return The statistics for the mode being played.
	 */
	public GameStatistics getModeStatistics() {
		return currentModeStatistics;
	}

	/**
	 * 
	 * @return The in-game entity factory.
	 */
	public InGameEntityFactory getEntityFactory() {
		return myEntityFactory;
	}

	/**
	 * Sets the rule agenda to the rules within the current level.
	 */
	public void initializeRules() {
		myRuleAgenda = getCurrentLevel().getRuleAgenda();
	}

}
