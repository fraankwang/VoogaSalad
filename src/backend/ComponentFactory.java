package backend;

import java.util.List;

public class ComponentFactory {

	public ComponentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Component makeComponent(String componentType){
		Component myComponent = null;
		try {
			myComponent = (Component) Class.forName(componentType + "Component").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return myComponent;
	}
	
	public void initComponentWithParams(Component component, List parameters){
		component.initWithParams(parameters);
	}

}
