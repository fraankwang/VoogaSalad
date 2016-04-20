//Kushal Byatnal
package authoring.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.backend.components.Component;
import engine.backend.entities.Entity;

public class EntityFactory {
	private ComponentFactory myComponentFactory;
	
	public EntityFactory() {
		this.myComponentFactory = new ComponentFactory();
	}

	public Entity createEntity(Map<String, String> info){
		Entity newEntity = new Entity(info.get("Name"), info.get("Genre"));
		List<Component> entityComponents = createComponents(info);
		for(Component comp : entityComponents){
			newEntity.addComponent(comp);
		}
		return newEntity;
	}
	
	private List<Component> createComponents(Map<String, String> info){
		List<Component> components = new ArrayList<Component>();
		for (String key : info.keySet()){
			if(key.contains("Component")){
				components.add(myComponentFactory.createComponent(key, info.get(key)));
			}
		}
		return components;
	}
	
	

}
