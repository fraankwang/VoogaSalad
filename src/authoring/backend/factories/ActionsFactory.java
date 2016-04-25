package authoring.backend.factories;

public class ActionsFactory {

	public ActionsFactory() {
		
	}
	
	public Action createAction(Object info) {
		String actionName = "";
		Action action = null;
		try {
			action = (Action) Class.forName("backend.rules"+ actionName + "Actions").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return action;
	}

}
