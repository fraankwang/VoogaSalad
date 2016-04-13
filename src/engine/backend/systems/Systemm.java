/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.entities.Entity;

public abstract class Systemm implements ISystem{
	
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "backend/resources/";

	private ResourceBundle myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");

	public ResourceBundle getComponentTagResources(){
		return myComponentTagResources;
	}
	
}
