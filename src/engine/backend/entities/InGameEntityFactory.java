package engine.backend.entities;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import engine.backend.game_object.GameStatistics;
import engine.backend.components.IComponent;

public class InGameEntityFactory {

	private GameStatistics myStats;
	private Map<String, Map<String, IEntity>> myEntityMap;

	public InGameEntityFactory(GameStatistics stats, Map<String, Map<String, IEntity>> map) {
		this.myStats = stats;
		this.myEntityMap = map;
	}

	/**
	 * 
	 * @param entityName
	 * @return A entity with the entity name given.
	 */
	public IEntity createEntity(String entityName) {
		IEntity templateEntity = findInMap(entityName);
		IEntity newEntity = new Entity(myStats.nextEntityID(), templateEntity.getName(), templateEntity.getType(),
				templateEntity.getValue());
		copyComponents(newEntity, templateEntity);
		return newEntity;
	}

	private IEntity findInMap(String entityName) {
		for (String type : myEntityMap.keySet()) {
			Map<String, IEntity> entities = myEntityMap.get(type);
			if (entities.containsKey(entityName)) {
				return entities.get(entityName);
			}
		}
		return null;
	}

	private void copyComponents(IEntity newEntity, IEntity templateEntity) {
		Collection<IComponent> templateComponents = templateEntity.getComponents();
		if(templateComponents.size() == 0){
			System.out.println("This list is empty");
		} else {
			System.out.println(templateComponents.size());
		}
		for (IComponent component : templateComponents) {
			IComponent copyComponent = cloneComponent(component);
			System.out.println(component.getClass().getName());
			System.out.println(copyComponent);
			newEntity.addComponent(copyComponent);
		}
	}

	private IComponent cloneComponent(IComponent component) {
		try {
			IComponent clone = component.getClass().newInstance();
			for (Field field : component.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				field.set(clone, field.get(component));
			}
			return clone;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}
	}

}
