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

public class EntityAction implements IAction{

	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
	
	/**
	 * Creates an action that acts upon an entity and modifies its components
	 * @param entityName - the entity name to modify
	 * @param componentToModify - the component within the entity to modify
	 * @param valueInComponent - the instance variable within the component to modify
	 * @param newValue - the new value for the variable
	 */
	public EntityAction(String entityName, String componentToModify, String valueInComponent, String newValue) {
		this.setEntityName(entityName);
		this.setComponentToModifiy(componentToModify);
		this.setValueInComponent(valueInComponent);
		this.setNewValue(newValue);
	}
	
	/**
	 * 
	 * @return the new value this action will set the variable
	 */
	public String getNewValue() {
		return newValue;
	}
	
	
	/**
	 * 
	 * @param newValue - create the new value to set the variable to
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	/**
	 * 
	 * @return The instance variable in the component to change
	 */
	public String getValueInComponent() {
		return valueInComponent;
	}
	
	/**
	 * 
	 * @param valueInComponent The instance variable in the component to change
	 */
	public void setValueInComponent(String valueInComponent) {
		this.valueInComponent = valueInComponent;
	}
	
	/**
	 * 
	 * @return The component within the entity to modify
	 */
	public String getComponentToModifiy() {
		return componentToModifiy;
	}
	
	/**
	 * 
	 * @param componentToModifiy - Set the component within the entity to modify
	 */
	public void setComponentToModifiy(String componentToModifiy) {
		this.componentToModifiy = componentToModifiy;
	}
	
	/**
	 * 
	 * @return The entity name to modify
	 */
	public String getEntityName() {
		return entityName;
	}
	
	/**
	 * 
	 * @param entityName - Sets the entity name to modify
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
