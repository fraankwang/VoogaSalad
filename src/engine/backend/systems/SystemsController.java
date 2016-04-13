/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.backend.factories.InGameEntityFactory;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.controller.EngineController;

public class SystemsController {

	private ISystem renderingSystem;
	private ISystem mobilizationSystem;
	private ISystem healthSystem;
	private ISystem firingSystem;
	private ISystem rulesSystem;
	private ISystem collisionSystem;
	
	private List<ISystem> mySystems;
	private EngineController engineController;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myActionRequirementsResources;
	private ResourceBundle myComponentTagResources;
	
	private InGameEntityFactory myEntityFactory;


	public SystemsController(EngineController eController) {
		engineController = eController;
		myEntityFactory = new InGameEntityFactory(eController.getMyGameWorld().getGameStatistics(), eController.getMyGameWorld().getEntityMap());
		
		renderingSystem = new RenderingSystem(engineController);
		mobilizationSystem = new MobilizeSystem();
		healthSystem = new HealthSystem();
		firingSystem = new FiringSystem();
		collisionSystem = new CollisionSystem();
		rulesSystem = new RulesSystem();
		
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
			system.update(currentLevel.getEntities(), myEntityFactory);

		}
		
	}

}
