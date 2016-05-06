package authoring.backend.factories;

import java.util.Map;

import authoring.backend.game_objects.AuthoringEntity;
import engine.backend.components.IComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class EntityFactory {

	public EntityFactory() {

	}

	public IEntity createEntity(AuthoringEntity spawnEntity) {
		String name = spawnEntity.getName();
		String genre = spawnEntity.getGenre();
		Map<String, IComponent> components = spawnEntity.getComponents();

		return new Entity(name, genre, components);
	}

}
