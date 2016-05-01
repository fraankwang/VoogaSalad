package engine.backend.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.components.EmptyComponent;
import engine.backend.components.IComponent;
import engine.backend.game_object.GameStatistics;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class InGameEntityFactory {

	private Map<String, Map<String, IEntity>> myEntityMap;
	private int currentLevelId;
	private int initNumEntities;
	private int nextAvailableID;
	private ExceptionLoader myExceptionLoader;

	private static final String LACK_ACCESS = "LackAccessToClass";
	private static final String METHOD_DNE = "MethodDoesNotExist";
	private static final String SECURITY_EXCEPTION = "SecurityException";
	private static final String ILLEGAL_ARGS = "IllegalArguments";
	private static final String INSTANTIATION = "ReflectionInstantiation";

	public InGameEntityFactory(List<IEntity> entities) {
		myExceptionLoader = new ExceptionLoader();
		this.myEntityMap = createMap(entities);
		nextAvailableID = 0;
		//initNumEntities = 0;
	}

	private Map<String, Map<String, IEntity>> createMap(List<IEntity> entities) {
		Map<String, Map<String, IEntity>> mainEntityMap = new HashMap<String, Map<String, IEntity>>();
		for (IEntity entity : entities) {
			Map<String, IEntity> typeMap = null;
			if (mainEntityMap.containsKey(entity.getGenre())) {
				typeMap = mainEntityMap.get(entity.getGenre());
			} else {
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

		IEntity newEntity = new Entity(initNumEntities + getNextAvailableID(), templateEntity.getName(), templateEntity.getGenre());
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
			Constructor<? extends IComponent> constructor = component.getClass().getConstructor(component.getClass());
			return (IComponent) constructor.newInstance(component);
		} catch (InstantiationException e) {
			new DrumpfTowerException(myExceptionLoader.getString(INSTANTIATION));
		} catch (IllegalAccessException e) {
			new DrumpfTowerException(myExceptionLoader.getString(LACK_ACCESS));
		} catch (IllegalArgumentException e) {
			new DrumpfTowerException(myExceptionLoader.getString(ILLEGAL_ARGS));
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e1) {
			new DrumpfTowerException(myExceptionLoader.getString(METHOD_DNE));
		} catch (SecurityException e1) {
			new DrumpfTowerException(myExceptionLoader.getString(SECURITY_EXCEPTION));
		}
		return (IComponent) new EmptyComponent();
	}

	public boolean isCurrent(int id) {
		return this.currentLevelId == id;
	}

	public void setEntities(List<IEntity> entities) {
		this.myEntityMap = createMap(entities);
	}

	public void setID(int id) {
		this.currentLevelId = id;
	}
	
	public void setInitNumEntities(int num){
		this.initNumEntities = num;
	}
	
	private int getNextAvailableID(){
		nextAvailableID++;
		return this.nextAvailableID;
	}

}
