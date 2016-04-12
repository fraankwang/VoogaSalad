//Kushal Byatnal
package authoring_environment.backend.deprecated;

import java.util.List;

import backend.GameStatisticsObject;
import backend.game_object.components.Component;
import backend.game_object.entities.Entity;

public class EntityFactory {
	
	public EntityFactory() {
		
	}

	public Entity createEntity(Object info){
		//parse string
		int parsedId = 0;
		Entity newEntity = new Entity(parsedId);
		setUpEntity(newEntity, info);
		return newEntity;
	}
	
	private void setUpEntity(Entity entity, Object info){
		//set up parent id
	}
	
	public void addComponents(Entity entity, List<Component> components){
		for (Component component : components) {
			entity.addComponent(component);
		}
	}
}
