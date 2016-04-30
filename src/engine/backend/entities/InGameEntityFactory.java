package engine.backend.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.components.IComponent;
import engine.backend.game_object.GameStatistics;

public class InGameEntityFactory {

	private GameStatistics myStats;
	private Map<String, Map<String, IEntity>> myEntityMap;
	private int currentLevelId;

	public InGameEntityFactory(GameStatistics stats, List<IEntity> entities) {
		this.myStats = stats;
		this.myEntityMap = createMap(entities);
	}
	
	private Map<String, Map<String, IEntity>> createMap(List<IEntity> entities)
	{
		Map<String, Map<String, IEntity>> mainEntityMap = new HashMap<String, Map<String, IEntity>>(); 
		for(IEntity entity : entities){
			Map<String, IEntity> typeMap = null;
			if(mainEntityMap.containsKey(entity.getGenre())){
				typeMap = mainEntityMap.get(entity.getGenre());
			}
			else{
				typeMap = new HashMap<String, IEntity>();
				mainEntityMap.put(entity.getGenre(), typeMap);
			}
			typeMap.put(entity.getName(), entity);
		}
		return mainEntityMap;
	}
	/**
	 * 
	 * @param entityName
	 * @return A entity with the entity name given.
	 */
	
	public IEntity createEntity(String entityName) {
		IEntity templateEntity = findInMap(entityName);

		IEntity newEntity = new Entity(myStats.getNextAvailableID(), templateEntity.getName(), templateEntity.getGenre());
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
			System.exit(1);
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
	
	public boolean isCurrent(int id){
		return this.currentLevelId == id;
	}
	
	public void setEntities(List<IEntity> entities){
		this.myEntityMap = createMap(entities);
	}
	
	public void setID(int id){
		this.currentLevelId = id;
	}

}
