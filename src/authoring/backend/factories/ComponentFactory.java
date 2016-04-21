package authoring.backend.factories;

import engine.backend.components.IComponent;

public class ComponentFactory {

	public ComponentFactory() {
		
	}
	
	public IComponent create(String componentType, String dataName, String data){
		IComponent component = null;
		try {
			component = (IComponent) Class.forName("engine.backend.components."+componentType).newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		component.initWithParams(data.split("//s+"));
		return component;
	}
	
	public IComponent update(IComponent component, String dataName, String data) {
		component.update(dataName, data);
		return component;
	}

}
