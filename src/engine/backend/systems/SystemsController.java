/**
 * 
 * @author mario_oliver93, raghav kedia
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.text.html.parser.Entity;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.controller.EngineController;

public class SystemsController {

	private GameSystem renderingSystem;
	private GameSystem mobilizationSystem;
	private GameSystem healthSystem;
	private GameSystem firingSystem;
	private GameSystem collisionSystem;
	private GameSystem spawningSystem;
	private RangeSystem rangeSystem;
	private List<ISystem> mySystems;
	private EngineController engineController;
	private EventManager myEventManager;

	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myComponentTagResources;

	private InGameEntityFactory myEntityFactory;

	private int myLevelIndex;
	private int myModeIndex;

	private GameClock myGameClock;

	/*
	 * the this reference to rendering will get removed, so only the event
	 * handler will get passed fixing rendering system before I remove this
	 * dependency
	 * 
	 * @author == mario
	 */
	public SystemsController(int framesPerSecond, EventManager myEventManager) {
		myEntityFactory = new InGameEntityFactory(myEventManager.getGameWorld().getGameStatistics(),
				myEventManager.getGameWorld().getEntityMap());

		myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");

		rangeSystem = new RangeSystem();
		renderingSystem = new RenderingSystem();
		mobilizationSystem = new MobilizeSystem();
		healthSystem = new HealthSystem();
		firingSystem = new FiringSystem();
		collisionSystem = new CollisionSystem();
		spawningSystem = new SpawningSystem();

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
		mySystems.add(renderingSystem);
		mySystems.add(rangeSystem);
		mySystems.add(spawningSystem);

		myGameClock = new GameClock(framesPerSecond);
	}

	public void iterateThroughSystems(Level level) {
		Map<String, Set<Integer>> myEventMap = new HashMap<String, Set<Integer>>();
		// go through systems, update stuff and gather events
		for (ISystem system : mySystems) {
			system.update(myEventManager.getCurrentLevel(), myEventMap, myEntityFactory,
					myGameClock.getCurrentSecond());
		}
		// handle all the generate events
		myEventManager.handleGeneratedEvents(myEventMap);
		// final system, do all the rendering
		renderingSystem.update(myEventManager.getCurrentLevel(), myEventMap, myEntityFactory,
				myGameClock.getCurrentSecond());
		// System.out.println(myEventManager.getModeStatistics().getCurrentNumLives());

		myGameClock.updateLoopIteration();
	}

}
