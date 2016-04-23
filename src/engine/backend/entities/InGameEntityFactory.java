package engine.backend.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import engine.backend.components.IComponent;
import engine.backend.game_object.GameStatistics;

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
		for (IComponent component : templateComponents) {
			IComponent copyComponent = cloneComponent(component);
			newEntity.addComponent(copyComponent);
		}
	}

	private IComponent cloneComponent(IComponent component) {
		try {
			Constructor constructor = component.getClass().getConstructor(component.getClass());
			return (IComponent) constructor.newInstance(component);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		// try {
		// IComponent clone = component.getClass().newInstance();
		// for (Field field : component.getClass().getDeclaredFields()) {
		// field.setAccessible(true);
		// field.set(clone, field.get(component));
		// }
		// return clone;
		// } catch (Exception e) {
		// System.out.println(e.getStackTrace());
		// return null;
		// }
	}

}
