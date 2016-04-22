//Kushal Byatnal
package authoring.backend.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.components.IComponent;
import engine.backend.entities.Entity;

public class EntityFactory {
	private ComponentFactory myComponentFactory;
	
	public EntityFactory() {
		this.myComponentFactory = new ComponentFactory();
	}

	public Entity createEntity(Map<String, String> info){
		Entity newEntity = new Entity(info.get("Name"), info.get("Genre"));
		List<IComponent> entityComponents = createComponents(info);
		for(IComponent comp : entityComponents){
			newEntity.addComponent(comp);
		}
		return newEntity;
	}
	
	private List<IComponent> createComponents(Map<String, String> info){
		Map<String, IComponent> componentMap = new HashMap<String, IComponent>();
		for (String key : info.keySet()) {
			if (key.contains("_")) {
				String[] componentInfo = key.split("_");
				String componentType = componentInfo[0];
				String componentData = componentInfo[1];
				String data = info.get(key);
				if (componentMap.containsKey(componentType)) {
					IComponent comp = componentMap.get(componentType);
					IComponent newComp = myComponentFactory.update(comp, componentData, data);
					componentMap.put(componentType, newComp);
				}
				IComponent component = myComponentFactory.create(componentType, componentData, data);
				componentMap.put(componentType, component);
			}
		}
		Collection<IComponent> temp = componentMap.values();
		List<IComponent> components = new ArrayList<IComponent>(temp);
		return components;
	}
	
}
