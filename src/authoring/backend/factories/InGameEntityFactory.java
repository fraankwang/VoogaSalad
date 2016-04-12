package authoring.backend.factories;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import engine.backend.GameStatisticsObject;
import engine.backend.components.IComponent;
import engine.backend.entities.Entity;

public class InGameEntityFactory {
	
	private GameStatisticsObject myStats;
	private Map<String, Map<String,Entity>> myEntityMap;
	
	public InGameEntityFactory(GameStatisticsObject stats, Map<String, Map<String, Entity>> map) {
		this.myStats = stats;
		this.myEntityMap = map;
	}
	
	public Entity createEntity(String entityName){
		Entity templateEntity = findInMap(entityName);
		Entity newEntity = new Entity(myStats.nextEntityID(), templateEntity.getName(), templateEntity.getMyType(), templateEntity.getValue());
		copyComponents(newEntity, templateEntity);
		return newEntity;
	}
	
	private Entity findInMap(String entityName){
		for (String type : myEntityMap.keySet()){
			Map<String, Entity> entities = myEntityMap.get(type);
			if (entities.containsKey(entityName)){
				return entities.get(entityName);
			}
		}
		return null;
	}
	
	private void copyComponents(Entity newEntity, Entity templateEntity){
		Collection<IComponent> templateComponents =  templateEntity.getComponents();
		for (IComponent component : templateComponents){
			IComponent copyComponent = cloneComponent(component);
			newEntity.addComponent(copyComponent);
		}
	}
	
	private IComponent cloneComponent(IComponent component){
	       try{
	            IComponent clone = component.getClass().newInstance();
	            for (Field field  : component.getClass().getDeclaredFields()) {
	                field.setAccessible(true);
	                field.set(clone, field.get(component));
	            }
	            return clone;
	        }catch(Exception e){
	            System.out.println(e.getStackTrace());
	            return null;
	        }
	}

}
