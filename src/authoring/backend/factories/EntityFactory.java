//Kushal Byatnal
package authoring.backend.factories;

import java.util.List;

import engine.backend.game_object.GameStatistics;
import engine.backend.components.Component;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class EntityFactory {
	private GameStatistics myStats;
	
	public EntityFactory() {
		
	}
	
	public Entity createEntity(int levelID, String name, String type, double price, List<Component> components){	
		Entity newEntity = new Entity(myStats.nextEntityID(), name, type, price);
		for (Component component : components) {
			newEntity.addComponent(component);
		}
		newEntity.setLevelID(levelID);
		return newEntity;
	}
}
