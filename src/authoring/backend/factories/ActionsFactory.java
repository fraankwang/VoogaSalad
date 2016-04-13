package authoring.backend.factories;

import backend.game_object.components.Component;
import backend.rules.Actions;

public class ActionsFactory {

	public ActionsFactory() {
		
	}
	
	public Actions createAction(Object info) {
		String actionName = "";
		Actions action = null;
		try {
			action = (Actions) Class.forName("backend.rules"+ actionName + "Actions").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return action;
	}

}
