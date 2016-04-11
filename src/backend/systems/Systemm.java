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
import backend.Level;
import backend.game_object.entities.Entity;

public abstract class Systemm implements ISystem{
	
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "backend/resources/";
	private ResourceBundle myActionRequirementsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "action_component_requirements");
	private ResourceBundle myComponentTagResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "component_tags");
	
	//public abstract void execute(List<Level> list);
	
	//for filter list for desired entities
	public List<Entity> filter(List<Entity> entities, List<String> requiredComponents){
		
		List<Entity> filteredEntities = new ArrayList<Entity>(); 
		
		for(Entity entity : entities){
			
			if(entity.getComponentTags().containsAll(requiredComponents)){
				filteredEntities.add(entity);
			}
			
		}
		
		return filteredEntities;
		
	}
	
	public ResourceBundle getComponentTagResources(){
		return myComponentTagResources;
	}
	
}
