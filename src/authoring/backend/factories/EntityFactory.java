//Kushal Byatnal
package authoring.backend.factories;

import java.util.List;

import engine.backend.components.Component;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

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

	public static IEntity getEntity(String firedEntityName) {
		// TODO Auto-generated method stub
		return null;
	}
}
