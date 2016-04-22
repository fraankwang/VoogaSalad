/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */

package engine.backend.rules;

/**
 * the rule is going to sreot the component that nends to be hcanges, by how
 * much and which method it needs to execute
 */
<<<<<<< HEAD:src/engine/backend/rules/Action.java
public class Action {
<<<<<<< HEAD
=======
>>>>>>> origin/engine_backend_systems_rk145:src/engine/backend/rules/EntityAction.java

public class EntityAction {

	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
<<<<<<< HEAD:src/engine/backend/rules/Action.java
>>>>>>> origin/authoring_backend
	
	public Action(String entityName, String componentToModify, String valueInComponent, String newValue) {
=======

	public EntityAction(String entityName, String componentToModify, String valueInComponent, String newValue) {
>>>>>>> origin/engine_backend_systems_rk145:src/engine/backend/rules/EntityAction.java
		this.setEntityName(entityName);
		this.setComponentToModifiy(componentToModify);
		this.setValueInComponent(valueInComponent);
		this.setNewValue(newValue);
	}

	public String getNewValue() {
		return newValue;
	}

<<<<<<< HEAD:src/engine/backend/rules/Action.java
<<<<<<< HEAD
	public String getMethodToCall(){
		return methodToCall;
	}

	public void setMyMethodToCall(String method){
		this.methodToCall = method;
	}

=======
=======
>>>>>>> origin/engine_backend_systems_rk145:src/engine/backend/rules/EntityAction.java
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getValueInComponent() {
		return valueInComponent;
	}

	public void setValueInComponent(String valueInComponent) {
		this.valueInComponent = valueInComponent;
	}

	public String getComponentToModifiy() {
		return componentToModifiy;
	}

	public void setComponentToModifiy(String componentToModifiy) {
		this.componentToModifiy = componentToModifiy;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
<<<<<<< HEAD:src/engine/backend/rules/Action.java
	
>>>>>>> origin/authoring_backend
=======
>>>>>>> origin/engine_backend_systems_rk145:src/engine/backend/rules/EntityAction.java

}
