/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.controller.EngineController;

public class SystemsController {

	private GameSystem renderingSystem;
	private GameSystem mobilizationSystem;
	private GameSystem healthSystem;
	private GameSystem firingSystem;
	private GameSystem collisionSystem;

	private List<ISystem> mySystems;
	private EngineController engineController;
	private EventManager myEventManager;

	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myComponentTagResources;

	private InGameEntityFactory myEntityFactory;

	private int myLevelIndex;
	private int myModeIndex; 
	
	private GameClock myGameClock;
	
	private GameWorld myGame;

	/*
	 * the this reference to rendering will get removed, so only the event
	 * handler will get passed fixing rendering system before I remove this
	 * dependency
	 * 
	 * @author == mario
	 */
	public SystemsController(EngineController eController, int framesPerSecond, EventManager myEventManager) {
		engineController = eController;

		myEntityFactory = new InGameEntityFactory(eController.getMyGameWorld().getGameStatistics(),
				eController.getMyGameWorld().getEntityMap());

		myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");

		renderingSystem = new RenderingSystem(engineController);
		mobilizationSystem = new MobilizeSystem();
		healthSystem = new HealthSystem();
		firingSystem = new FiringSystem();
		collisionSystem = new CollisionSystem();


		this.myEventManager = myEventManager;

		healthSystem.addObserver(myEventManager);
		firingSystem.addObserver(myEventManager);
		collisionSystem.addObserver(myEventManager);

		mySystems = new ArrayList<ISystem>();
		mySystems.add(firingSystem);
		mySystems.add(mobilizationSystem);
		mySystems.add(collisionSystem);
		mySystems.add(healthSystem);
		mySystems.add(renderingSystem);
		
		myGameClock = new GameClock(framesPerSecond);

	}

	public void initializeGame(GameWorld game) {
		myGame = game;
	}

	public void iterateThroughSystems(Level level) {
		for (ISystem system : mySystems) {
			long startTime = System.currentTimeMillis();
			system.update(myEventManager.getCurrentLevel(), myEntityFactory, myGameClock.getCurrentSecond(), myComponentTagResources);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println(system.getClass().getSimpleName() + ": " + totalTime);
		}
		myGameClock.updateLoopIteration();
	}

}
