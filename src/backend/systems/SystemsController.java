/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backend.FrontEndAccessController;
import backend.GameObject;
import backend.IFrontEndAccess;
import backend.Level;
import backend.Mode;
import exception.ResourceLoader;

public class SystemsController {

	private RenderingSystem render;
	private MobilizeSystem mobilize;
	private List<Systemm> bagOfSystems = new ArrayList<Systemm>();
	private FrontEndAccessController backendController;
	private ResourceLoader myResourceLoader;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "/backend/resources/";
	private ResourceBundle myActionRequirementsResources;
	private ResourceBundle myComponentTagResources;

	public SystemsController(FrontEndAccessController backendController) {
		this.backendController = backendController;
		myResourceLoader = new ResourceLoader();
		
//		myActionRequirementsResources = new ResourceLoader("action_component_requirements.properties");
//		myComponentTagResources = new ResourceLoader("component_tag.properties");
//		myActionRequirementsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "action_component_requirements.properties");
//		myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tag.properties");
		
		render = new RenderingSystem(backendController);
//		mobilize = new MobilizeSystem();
		addToBagOfSystems(render);
//		addToBagOfSystems(mobilize);
	}

	public void addToBagOfSystems(Systemm system) {
		bagOfSystems.add(system);
	}

	public void iterateThroughSystems(GameObject game) {
		for (Systemm myCurrSystem : bagOfSystems) {
			Mode currMode = game.getModes().get(game.getGameStats().getCurrentLevel());
			List<Level> currLevels = game.getLevelsForMode(currMode);
			myCurrSystem.update(currLevels.get(game.getGameStats().getCurrentLevel()).getEntities());
		}
	}

}
