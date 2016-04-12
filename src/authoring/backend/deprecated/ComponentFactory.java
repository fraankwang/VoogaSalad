package authoring.backend.deprecated;

import java.util.List;

public class ComponentFactory {

	public ComponentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Component makeComponent(String componentType, List parameters){
		Component component = null;
		try {
			component = (Component) Class.forName("backend."+componentType + "Component").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		component.initWithParams(parameters);
		return component;
	}

}
