/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */

package engine.backend.rules;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.IModifiable;

/**
 * the rule is going to find the component that needs to be changes, by how
 * much and which method it needs to execute
 */

public class EntityAction implements IAction {

	private String entityName;
	private String componentToModifiy;
	private String valueInComponent;
	private String newValue;
	private IAction successor;

	public EntityAction() {
		
	}
	
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
	 * @param newValue
	 *            - create the new value to set the variable to
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

	@Override
	public void setSuccessor(IAction sucessor) {
		this.successor = sucessor;
	}

	@Override
	public void applyAction(GameStatistics currentGameStatistics, IEntity entity, IAction action) {
		if (action.getClass() == this.getClass()) {
			if (((EntityAction) action).getEntityName().equals(entity.getName())) {
				((IModifiable) entity).applyAction((EntityAction) action);
			}
		} else {
			successor.applyAction(currentGameStatistics, entity, action);
		}
	}

}
