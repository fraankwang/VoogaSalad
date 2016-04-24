package engine.backend.systems;

import java.util.Observable;
import java.util.ResourceBundle;

import engine.backend.entities.IEntity;
import engine.backend.systems.Events.AddEntityEvent;

public abstract class GameSystem extends Observable implements ISystem{
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "backend.resources/";
	protected ResourceBundle myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");

	
}
