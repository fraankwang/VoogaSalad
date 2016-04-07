/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.systems;

import java.util.ArrayList;
import java.util.List;

import backend.FrontEndAccessController;
import backend.GameObject;
import backend.IFrontEndAccess;
import backend.Level;
import backend.Mode;

public class SystemsController {

	private RenderingSystem render;
	private MobilizeSystem mobilize;
	private List<Systems> bagOfSystems = new ArrayList<Systems>();
	private FrontEndAccessController backendController;

	public SystemsController(FrontEndAccessController backendController) {
		this.backendController = backendController;
		render = new RenderingSystem(backendController);
		mobilize = new MobilizeSystem();
		addToBagOfSystems(render);
//		addToBagOfSystems(mobilize);
	}

	public void addToBagOfSystems(Systems system) {
		bagOfSystems.add(system);
	}

	public void iterateThroughSystems(GameObject game) {
		for (Systems myCurrSystem : bagOfSystems) {
			Mode currMode = game.getModes().get(game.getGameStats().getCurrentLevel());
			List<Level> currLevels = game.getLevelsForMode(currMode);
			myCurrSystem.update(currLevels.get(game.getGameStats().getCurrentLevel()).getEntities());
		}
	}

}
