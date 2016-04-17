/**
 * 
 * @author mario_oliver93
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
	private GameSystem rulesSystem;
	private GameSystem collisionSystem;
	
	private List<ISystem> mySystems;
	private EngineController engineController;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myActionRequirementsResources;
	private ResourceBundle myComponentTagResources;
	
	private InGameEntityFactory myEntityFactory;


	public SystemsController(EventManager eventManager, EngineController eController) {
		engineController = eController;
		myEntityFactory = new InGameEntityFactory(eController.getMyGameWorld().getGameStatistics(), 
				eController.getMyGameWorld().getEntityMap());
		
		myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");
		
		renderingSystem = new RenderingSystem(engineController);
		mobilizationSystem = new MobilizeSystem();
		healthSystem = new HealthSystem();
		firingSystem = new FiringSystem();
		collisionSystem = new CollisionSystem();
		
		healthSystem.addObserver(eventManager);
		firingSystem.addObserver(eventManager);
		collisionSystem.addObserver(eventManager);
		
		mySystems = new ArrayList<ISystem>();
		mySystems.add(firingSystem);
		mySystems.add(mobilizationSystem);
		mySystems.add(collisionSystem);
		mySystems.add(healthSystem);
		mySystems.add(rulesSystem);
		mySystems.add(renderingSystem);

	}

	public void iterateThroughSystems(GameWorld game) {
		Mode currMode = game.getModes().get(game.getGameStatistics().getCurrentLevel());
		List<Level> currLevels = currMode.getLevels();
		Level currentLevel = currLevels.get(game.getGameStatistics().getCurrentLevel());
		for (ISystem system : mySystems) {
			
			long startTime = System.currentTimeMillis();
			system.update(currentLevel, myEntityFactory, myComponentTagResources);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			//System.out.println(system.getClass().getSimpleName() + ":  " + totalTime);
			
		}
	}

}
