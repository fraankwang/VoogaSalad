package authoring.backend.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.backend.game_objects.AuthoringEntity;
import engine.backend.components.CollisionComponent;
import engine.backend.components.IComponent;

/*
 * @author: Kushal, Jonathan
 */

public class AuthoringEntityFactory {
	private ComponentFactory myComponentFactory;
	
	public AuthoringEntityFactory() {
		this.myComponentFactory = new ComponentFactory();
	}

	public AuthoringEntity createEntity(Map<String, String> info){
		String name = info.get("Name");
		String genre = info.get("Genre");
		
		AuthoringEntity newAuthoringEntity = new AuthoringEntity(name, genre);
		
		List<IComponent> entityComponents = createComponents(info);
		
		for(IComponent comp : entityComponents){
			newAuthoringEntity.addComponent(comp);
		}
		return newAuthoringEntity;
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
				} else {
					IComponent component = myComponentFactory.create(componentType, componentData, data);
					componentMap.put(componentType, component);
				}
			}
		}
		
		componentMap.put("CollisionComponent", new CollisionComponent());

		Collection<IComponent> temp = componentMap.values();

		List<IComponent> components = new ArrayList<IComponent>(temp);

		return components;
	}
	
}
