/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */

package engine.backend.rules;

public class Action {
	
	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
	
	public Action(String entityName, String componentToModify, String valueInComponent, String newValue) {
		this.setEntityName(entityName);
		this.setComponentToModifiy(componentToModify);
		this.setValueInComponent(valueInComponent);
		this.setNewValue(newValue);
	}

	public String getNewValue() {
		return newValue;
	}

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
	

}
