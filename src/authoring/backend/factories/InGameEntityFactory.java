package authoring.backend.factories;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import engine.backend.game_object.GameStatisticsObject;
import engine.backend.components.IComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class InGameEntityFactory {
	
	private GameStatisticsObject myStats;
	private Map<String, Map<String,Entity>> myEntityMap;
	
	public InGameEntityFactory(GameStatisticsObject stats, Map<String, Map<String, Entity>> map) {
		this.myStats = stats;
		this.myEntityMap = map;
	}
	
	public IEntity createEntity(String entityName){
		IEntity templateEntity = findInMap(entityName);
		IEntity newEntity = new Entity(myStats.nextEntityID(), templateEntity.getName(), templateEntity.getType(), templateEntity.getValue());
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
	
	private void copyComponents(IEntity newEntity, IEntity templateEntity){
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
