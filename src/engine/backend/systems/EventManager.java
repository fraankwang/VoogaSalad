package engine.backend.systems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import backend.xml_converting.GameWorldToXMLWriter;
import backend.xml_converting.ObjectToXMLWriter;
import engine.backend.components.FiringComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_features.GameShop;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.IModifiable;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import engine.backend.rules.Rule;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.backend.systems.Events.GameEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.NextWaveEvent;
import engine.backend.systems.Events.PowerUpDroppedEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.backend.systems.Events.WaveOverEvent;
import engine.backend.utilities.ComponentTagResources;
import engine.controller.IEngineController;

public class EventManager implements Observer {

	private IEngineController myEngineController;
	private GameWorld myGameWorld;
	private GameStatistics currentGameStatistics;
	private List<Rule> myRuleAgenda;
	private RuleHandler ruleHandler;
	private ActionHandler actionHandler;
	private InGameEntityFactory myEntityFactory;
	private GameShop myGameShop;

	public EventManager(IEngineController engineController, GameWorld game) {
		myEngineController = engineController;
		myGameWorld = game;

		currentGameStatistics = new GameStatistics();
		// pass in right values
		myGameShop = new GameShop();
	}

	/**
	 * Sets the in game entity factory creator to the given factory.
	 * 
	 * @param factory
	 */
	public void setEntityFactory(InGameEntityFactory factory) {
		myEntityFactory = factory;

	}

	/**
	 * Sets the level rules to those for the current level. Also sets up handlers to handle rules.
	 * 
	 * @param level
	 */
	public void setLevelRules(Level level) {
		ruleHandler = new RuleHandler();
		actionHandler = new ActionHandler(level.getEntities(), currentGameStatistics);
		setCustomRules(level.getRuleAgenda());
	}

	/**
	 * If the current level is not reverted when it should be, revert occurs
	 * before returning.
	 * 
	 * @return The current level.
	 */
	public Level getCurrentLevel() {

		String modeName = currentGameStatistics.getCurrentMode();
		int levelIndex = currentGameStatistics.getCurrentLevelIndex();
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
		return myGameWorld.getModes().get(currentGameStatistics.getCurrentMode());
	}

	/**
	 * Updates the game shop by setting the shop items to those for the current
	 * level. Also updates the shop so only those allowed with the given
	 * resources is displayed. Calls engine controller to update the shop.
	 */
	public void updateGameShop() {
		myGameShop.setShopItems(getCurrentLevel().getShopItems());
		myGameShop.updateShop(currentGameStatistics.getCurrentResources());
		myEngineController.updateShop(myGameShop.getShopItems());
	}

	@Override
	public void update(Observable o, Object arg) {
		handleSystemEvent((IEvent) arg);
	}

	/**
	 * Receives an update entity event and sends the engine controller
	 * information to update the entity.
	 * 
	 * @param myEvent
	 */
	public void sendUpdatedEntity(UpdateEntityEvent myEvent) {
		myEngineController.updateEntity(myEvent.getX(), myEvent.getY(), myEvent.getImage(), myEvent.getID(),
				myEvent.getSizeX(), myEvent.getsizeY(), myEvent.getShow());
	}

	/**
	 * Sets the rule agenda for each level to the list of rules given.
	 * 
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
		currentGameStatistics = myGameWorld.getGameStatistics(event.getModeName());
		handleModeClickedEvent(event.getModeName());
		handleLevelClickedEvent(event.getLevel());
	}

	/**
	 * 
	 * @return The current game statistics.
	 */
	public GameStatistics getCurrentGameStatistics() {
		return currentGameStatistics;
	}

	/**
	 * Handles when a mode has been selected.
	 * 
	 * @param modeName
	 */
	private void handleModeClickedEvent(String modeName) {
		currentGameStatistics.setCurrentMode(modeName);
	}

	/**
	 * Handles when a level has been selected.
	 * 
	 * @param Level
	 * @throws IOException
	 */
	private void handleLevelClickedEvent(int level) throws IOException {
		currentGameStatistics.setCurrentLevelIndex(level);
		serializeLevel();
	}

	/**
	 * Handles when user goes to the next level.
	 * 
	 */
	public void handleGoToNextLevelEvent() {
		currentGameStatistics.setCurrentLevelIndex(currentGameStatistics.getCurrentLevelIndex() + 1);
		serializeLevel();
		updateEntityFactory();
	}

	private void serializeLevel() {
		String modeName = currentGameStatistics.getCurrentMode();
		int levelIndex = currentGameStatistics.getCurrentLevelIndex();
		myGameWorld.getLevelWithId(modeName, levelIndex)
				.setLastSerializedVersion(serializeLevel(myGameWorld.getLevelWithId(modeName, levelIndex)));
	}

	private String serializeLevel(Object o) {
		ObjectToXMLWriter serializer = new GameWorldToXMLWriter();
		return serializer.getXMLfromObject(o);
	}

	private void handleWaveOverEvent(WaveOverEvent event) {
		myEngineController.waveIsOver(event.getTimerLength());
	}

	/**
	 * Handles whether a level is over by checking if there are no more lives.
	 * If no more lives, engine controller is told that the level is lost and
	 * the level played is reset. If level is won, then the lives left and level
	 * resources is saved in the statistics and the level is reset.
	 */
	public void handleLevelOver() {

		boolean noLives = currentGameStatistics.noMoreLives();
		if (noLives) {
			myEngineController.levelIsLost();
			currentGameStatistics.updateHighestLevelUnlocked(currentGameStatistics.getCurrentLevelIndex());
			resetLevel();
		} else {
			if (getCurrentLevel().lastWaveOver() && !isEnemyOnScreen()) {
				currentGameStatistics.addEndOfLevelLives(currentGameStatistics.getCurrentNumLives());
				currentGameStatistics.addEndOfLevelResources(currentGameStatistics.getCurrentResources());
				int numLevelsTotal = myGameWorld.getModes().get(currentGameStatistics.getCurrentMode()).getLevels()
						.size();
				int nextLevelIndex = currentGameStatistics.getCurrentLevelIndex() + 1;
				if (numLevelsTotal > nextLevelIndex) {
					currentGameStatistics.updateHighestLevelUnlocked(currentGameStatistics.getCurrentLevelIndex() + 1);
				} else {
					myEngineController.levelIsLost();
				}
				myEngineController.levelIsWon();
				resetLevel();
			} else {
				return;
			}
		}
	}

	private boolean isEnemyOnScreen() {
		Set<String> enemyNames = getUniqueEnemyNames();
		boolean ret = false;
		for (IEntity entity : getCurrentLevel().getEntities().values()) {
			if (enemyNames.contains(entity.getName())) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	private Set<String> getUniqueEnemyNames() {
		Set<String> enemyNames = new HashSet<String>();

		for (ShopItem item : getCurrentLevel().getShopItems()) {
			for (IEntity entity : getCurrentLevel().getAuthoredEntities()) {
				if (item.getItemName().equals(entity.getName())) {
					if (entity.hasComponent(ComponentTagResources.firingComponentTag)) {
						FiringComponent firingComponent = (FiringComponent) entity
								.getComponent(ComponentTagResources.firingComponentTag);
						enemyNames.addAll(firingComponent.getTargets());
					}
				}
			}
		}
		return enemyNames;
	}

	private void resetLevel() {
		String modeName = currentGameStatistics.getCurrentMode();
		int levelIndex = currentGameStatistics.getCurrentLevelIndex();
		currentGameStatistics.resetResourcesAndLives();
		myGameWorld.getLevelWithId(modeName, levelIndex).setShouldRevert(true);
	}

	public void handleNextWaveEvent(NextWaveEvent event) {
		getCurrentLevel().setSendNextWave(true);
	}

	/**
	 * Creates a new entity from the entity factory, sets the position
	 * component, and adds entity to screen map.
	 * 
	 * @param event
	 */
	private void handleEntityDropEvent(EntityDroppedEvent event) {
		if (currentGameStatistics.getCurrentResources() >= event.getEntityValue()) {
			subtractFromResources(event.getEntityValue());
			IEntity newEntity = myEntityFactory.createEntity(event.getEntityName());
			PositionComponent posComp = (PositionComponent) newEntity
					.getComponent(ComponentTagResources.positionComponentTag);
			posComp.setPositionVector(new Vector(event.getXCoordinate(), event.getYCoordinate()));
			getCurrentLevel().addEntityToMap(newEntity);
		}
	}

	private void handlePowerUpDroppedEvent(PowerUpDroppedEvent event) {
		if (currentGameStatistics.getCurrentResources() >= event.getPowerUp().getPrice()) {
			if (event.getPowerUp() != null && isPowerUpApplicable(event.getAffectedEntityID(),
					((EntityAction) event.getPowerUp().getActions().get(0)).getEntityName())) {
				Collection<Integer> affectedEntities = Arrays.asList(event.getAffectedEntityID());
				Collection<IAction> actions = event.getPowerUp().getActions();
				actionHandler.applyActions(affectedEntities, actions);
				subtractFromResources(event.getPowerUp().getPrice());
			}
		}
	}

	private boolean isPowerUpApplicable(int entityID, String applicableName) {
		IEntity entity = getCurrentLevel().getEntityWithID(entityID);
		return entity.getName().equals(applicableName);
	}

	/**
	 * Updates the factory by checking if the entity factory is correct for the
	 * current level. If not, the entity factory is set to the authored entities
	 * for the level and given the current level index and initial entities.
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
	 * Takes in collection of non map user events generated, and handles them
	 * accordingly
	 * 
	 * @param events
	 */
	public void handleNonMapEvents(Collection<IEvent> events) {
		for (IEvent event : events) {
			if (event instanceof EntityDroppedEvent) {
				handleEntityDropEvent((EntityDroppedEvent) event);
			} else if (event instanceof NextWaveEvent) {
				handleNextWaveEvent((NextWaveEvent) event);
			} else if (event instanceof PowerUpDroppedEvent) {
				handlePowerUpDroppedEvent((PowerUpDroppedEvent) event);
			}
		}
	}

	/**
	 * Calls the rule handler to handle generated events;
	 * 
	 * @param generatedEventMap
	 */
	public void handleGeneratedEvents(Map<String, Set<Integer>> generatedEventMap) {
		ruleHandler.applyRules(generatedEventMap, myRuleAgenda, actionHandler);
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
		return currentGameStatistics;
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

	private void subtractFromResources(double value) {
		currentGameStatistics.setCurrentResources(currentGameStatistics.getCurrentResources() - value);
	}

}
