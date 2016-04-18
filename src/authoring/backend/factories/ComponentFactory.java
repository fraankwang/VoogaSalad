package authoring.backend.factories;

import engine.backend.components.Component;

public class ComponentFactory {

	public ComponentFactory() {
		
	}
	
	public Component createComponent(String componentType, String data){
		Component component = null;
		try {
			component = (Component) Class.forName("engine.backend.components."+componentType).newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		component.initWithParams(data.split("//s+"));
		return component;
	}
	
	

}
