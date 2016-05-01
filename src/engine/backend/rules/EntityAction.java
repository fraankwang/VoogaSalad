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

public class EntityAction implements IAction {

	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
	
	/**
	 * 
	 * @param entityName
	 * @param componentToModify
	 * @param valueInComponent
	 * @param newValue
	 */
	public EntityAction(String entityName, String componentToModify, String valueInComponent, String newValue) {
		this.setEntityName(entityName);
		this.setComponentToModifiy(componentToModify);
		this.setValueInComponent(valueInComponent);
		this.setNewValue(newValue);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Entity");
		sb.append("_");
		sb.append(entityName);
		sb.append("_");
		sb.append(componentToModifiy);
		sb.append("_");
		sb.append(valueInComponent);
		sb.append("_");
		sb.append(newValue);
		return sb.toString();
	}

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
