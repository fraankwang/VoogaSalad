/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */

package engine.backend.rules;

/**
 * the rule is going to sreot the component that nends to be hcanges, by how much and which method it needs to execute
 */
public class Action {
<<<<<<< HEAD

	private String methodToCall;

	private String delta;
=======
	
	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
>>>>>>> origin/authoring_backend
	
	public Action(String entityName, String componentToModify, String valueInComponent, String newValue) {
		this.setEntityName(entityName);
		this.setComponentToModifiy(componentToModify);
		this.setValueInComponent(valueInComponent);
		this.setNewValue(newValue);
	}

	public String getNewValue() {
		return newValue;
	}

<<<<<<< HEAD
	public String getMethodToCall(){
		return methodToCall;
	}

	public void setMyMethodToCall(String method){
		this.methodToCall = method;
	}

=======
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
	
>>>>>>> origin/authoring_backend

}
