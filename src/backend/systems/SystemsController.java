package backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backend.GameObject;

public class SystemsController {

	private RenderingSystem render;
	private MobilizeSystem mobilize;
	private List<Systems> bagOfSystems = new ArrayList<Systems>();
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend/resources/";
	private ResourceBundle myActionRequirementsResources;
	private ResourceBundle myComponentTagResources;

	public SystemsController() {
		myActionRequirementsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "action_component_requirements");
		myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tag");
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
