package authoring.backend.factories;

import java.util.Map;

import authoring.backend.game_objects.AuthoringEntity;
import engine.backend.components.IComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class EntityFactory {
	
	public EntityFactory() {
		
	}
	
	public IEntity createEntity(AuthoringEntity authoringEntity) {
		String name = authoringEntity.getName();
		String genre = authoringEntity.getGenre();
		Map<String, IComponent> components = authoringEntity.getComponents();
		
		return new Entity(name, genre, components);
	}

}
