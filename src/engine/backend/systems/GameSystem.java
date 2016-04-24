package engine.backend.systems;

import java.util.Observable;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
import engine.backend.systems.Events.AddEntityEvent;

public abstract class GameSystem extends Observable implements ISystem{
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	private ResourceBundle myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");
	
	protected String movementComponentTag = myComponentTagResources.getString("Movement");
	protected String positionComponentTag = myComponentTagResources.getString("Position");
	protected String collisionComponentTag = myComponentTagResources.getString("Collision");
	protected String firingComponentTag = myComponentTagResources.getString("Firing");
	protected String pathComponentTag = myComponentTagResources.getString("Path");
	protected String sizeComponentTag = myComponentTagResources.getString("Size");
	protected String spawnerComponentTag = myComponentTagResources.getString("Spawner");
	protected String healthComponentTag = myComponentTagResources.getString("Health");
	protected String displayComponentTag = myComponentTagResources.getString("Display");

}
