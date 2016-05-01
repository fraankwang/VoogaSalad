package engine.backend.utilities;

import java.util.ResourceBundle;

public class ComponentTagResources {

	private static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private static ResourceBundle myComponentTagResources = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");

	public static final String movementComponentTag = myComponentTagResources.getString("Movement");
	public static final String positionComponentTag = myComponentTagResources.getString("Position");
	public static final String collisionComponentTag = myComponentTagResources.getString("Collision");
	public static final String firingComponentTag = myComponentTagResources.getString("Firing");
	public static final String pathComponentTag = myComponentTagResources.getString("Path");
	public static final String sizeComponentTag = myComponentTagResources.getString("Size");
	public static final String spawnerComponentTag = myComponentTagResources.getString("Spawner");
	public static final String healthComponentTag = myComponentTagResources.getString("Health");
	public static final String displayComponentTag = myComponentTagResources.getString("Display");
	public static final String purchaseComponentTag = myComponentTagResources.getString("Purchase");

	public static String getComponentTag(String component) {
		return myComponentTagResources.getString(component);
	}

}
