package authoring_environment.backend.deprecated;

import java.util.List;

import backend.game_object.components.Component;

public class ComponentFactory {

	public ComponentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Component createComponent(Object info){
		String componentType = "Display"; //placeholder - get from parsed info
		Component component = null;
		try {
			component = (Component) Class.forName("backend."+componentType + "Component").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		//component.initWithParams(parameters); //set up parent id
		return component;
	}
	
	

}
