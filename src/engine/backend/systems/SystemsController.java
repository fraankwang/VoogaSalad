/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.controller.Engine2PlayerController;
import exception.ResourceLoader;

public class SystemsController {

	private RenderingSystem render;
	private MobilizeSystem mobilize;
	private RulesSystem rulesSystem;
	private List<Systemm> bagOfSystems = new ArrayList<Systemm>();
	private Engine2PlayerController frontendController;
//	private ResourceLoader myResourceLoader;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myActionRequirementsResources;
	private ResourceBundle myComponentTagResources;


	public SystemsController(Engine2PlayerController frontendController) {
		this.frontendController = frontendController;
//		myResourceLoader = new ResourceLoader();
		render = new RenderingSystem(frontendController);

		rulesSystem = new RulesSystem();


		addToBagOfSystems(new CollisionSystem());

//		mobilize = new MobilizeSystem();
		addToBagOfSystems(render);
		addToBagOfSystems(rulesSystem);
//		addToBagOfSystems(mobilize);
	}

	public void addToBagOfSystems(Systemm system) {
		bagOfSystems.add(system);
	}

	public void iterateThroughSystems(GameWorld game) {
		for (Systemm myCurrSystem : bagOfSystems) {
			Mode currMode = game.getModes().get(game.getGameStats().getCurrentLevel());
			List<Level> currLevels = game.getLevelsForMode(currMode);
			//switch this to iterate through the quadrants contained inside of the curr levels map
			//and that maps quadrants
			myCurrSystem.update(currLevels.get(game.getGameStats().getCurrentLevel()).getEntities());
		}
	}

}
