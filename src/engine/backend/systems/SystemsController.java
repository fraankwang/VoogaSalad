/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.game_object.Level;
import engine.backend.systems.Events.IEvent;

public class SystemsController {

	private GameSystem renderingSystem;
	private GameSystem mobilizationSystem;
	private GameSystem healthSystem;
	private GameSystem firingSystem;
	private GameSystem collisionSystem;
	private GameSystem spawningSystem;
	private UserInputSystem userInputSystem;
	private List<ISystem> mySystems;
	private EventManager myEventManager;

	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";

	private GameClock myGameClock;

	/*
	 * the this reference to rendering will get removed, so only the event
	 * handler will get passed fixing rendering system before I remove this
	 * dependency
	 * 
	 * @author == mario
	 */
	public SystemsController(int framesPerSecond, EventManager myEventManager) {

		renderingSystem = new RenderingSystem();
		mobilizationSystem = new MobilizeSystem();
		healthSystem = new HealthSystem();
		firingSystem = new FiringSystem();
		collisionSystem = new CollisionSystem();
		spawningSystem = new SpawningSystem();
		userInputSystem = new UserInputSystem();

		this.myEventManager = myEventManager;

		healthSystem.addObserver(myEventManager);
		firingSystem.addObserver(myEventManager);
		collisionSystem.addObserver(myEventManager);
		renderingSystem.addObserver(myEventManager);
		spawningSystem.addObserver(myEventManager);

		mySystems = new ArrayList<ISystem>();
		mySystems.add(spawningSystem);
		mySystems.add(firingSystem);
		mySystems.add(mobilizationSystem);
		mySystems.add(collisionSystem);
		mySystems.add(healthSystem);
		mySystems.add(userInputSystem);
		// mySystems.add(myEventManager);
		// mySystems.add(renderingSystem);

		myGameClock = new GameClock(framesPerSecond);
	}

	/**
	 * Receives user input from frontend, sends it to UserInputSystem
	 * 
	 * @param event
	 */
	public void sendUserInputEvent(IEvent event) {
		userInputSystem.handleUserEvent(event);
	}

	public void iterateThroughSystems(Level level, boolean playing) {
		Map<String, Set<Integer>> myEventMap = new HashMap<String, Set<Integer>>();

		for (ISystem system : mySystems) {
			system.update(playing, myEventManager.getCurrentLevel(), myEventMap, myEventManager.getEntityFactory(),
					myGameClock.getCurrentSecond());
		}
		Collection<IEvent> nonMapEvents = ((UserInputSystem) userInputSystem).getNonMapEvents();
		myEventManager.handleNonMapEvents(nonMapEvents);
		nonMapEvents.clear();
		// handle all the generate events
		myEventManager.handleGeneratedEvents(myEventMap);
		myEventManager.updateGameShop();
		renderingSystem.update(playing, myEventManager.getCurrentLevel(), myEventMap, myEventManager.getEntityFactory(),
				myGameClock.getCurrentSecond());
		myEventManager.handleLevelOver();
		if (!playing) {
			myGameClock.updateLoopIteration();
		}
	}

}
