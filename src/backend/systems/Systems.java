package backend.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backend.game_object.entities.Entity;

public abstract class Systems {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "backend/resources/";
	private ResourceBundle myActionRequirementsResources;
	myActionRequirementsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "action_component_requirements");
	
	public void update(List<Entity> entities){
		
	}
	
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
	
}
