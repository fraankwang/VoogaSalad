package backend;

import java.util.ArrayList;
import java.util.List;

public class SystemsController {

	private RenderingSystem render;
	private MobilizeSystem mobilize;
	private List<Systems> bagOfSystems = new ArrayList<Systems>();

	public SystemsController() {
		render = new RenderingSystem();
		mobilize = new MobilizeSystem();
		addToBagOfSystems(render);
		addToBagOfSystems(mobilize);
	}

	public void addToBagOfSystems(Systems system) {
		bagOfSystems.add(system);
	}

	public void iterateThroughSystems(GameObject game) {
		for (Systems myCurrSystem : bagOfSystems) {

		}
	}

}
