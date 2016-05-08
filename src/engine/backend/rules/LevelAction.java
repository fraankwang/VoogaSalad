package engine.backend.rules;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameStatistics;

public class LevelAction implements IAction {

	private String variableToModify;
	private String deltaValue;
	private IAction successor;
	
	public LevelAction() {
		
	}

	/**
	 * Creates a level action to modify properties of the level
	 * 
	 * @param varToModify
	 *            - variable within the level to modify
	 * @param delta
	 *            - value to change the variable by
	 */
	public LevelAction(String varToModify, String delta) {
		this.setVariableToModify(varToModify);
		this.setDeltaValue(delta);
		this.setSuccessor(new EntityAction());
	}

	/**
	 * 
	 * @return The variable within the level to modify
	 */
	public String getVariableToModify() {
		return variableToModify;
	}

	/**
	 * 
	 * @param variableToModify
	 *            - Sets the variable within the level to modify
	 */
	public void setVariableToModify(String variableToModify) {
		this.variableToModify = variableToModify;
	}

	/**
	 * 
	 * @return The amount to change the variable by
	 */
	public String getDeltaValue() {
		return deltaValue;
	}

	/**
	 * 
	 * @param deltaValue
	 *            - the amount to set the variable to change
	 */
	public void setDeltaValue(String deltaValue) {
		this.deltaValue = deltaValue;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Level");
		sb.append("_");
		sb.append(variableToModify);
		sb.append("_");
		sb.append(deltaValue);
		return sb.toString();
	}

	@Override
	public void setSuccessor(IAction sucessor) {
		this.successor = sucessor;
	}

	@Override
	public void applyAction(GameStatistics currentGameStatistics, IEntity entity, IAction action) {
		if (action.getClass() == this.getClass()) {
			currentGameStatistics.applyAction(action);			
		} else {
			successor.applyAction(currentGameStatistics, entity, action);
		}
	}


}
