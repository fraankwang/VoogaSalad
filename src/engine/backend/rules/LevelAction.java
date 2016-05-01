package engine.backend.rules;

public class LevelAction implements IAction{
	
	private String variableToModify;
	private String deltaValue;
	
	/**
	 * Creates a level action to modify properties of the level
	 * @param varToModify - variable within the level to modify
	 * @param delta - value to change the variable by
	 */
	public LevelAction(String varToModify, String delta) {
		this.setVariableToModify(varToModify);
		this.setDeltaValue(delta);
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
	 * @param variableToModify - Sets the variable within the level to modify
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
	 * @param deltaValue - the amount to set the variable to change
	 */
	public void setDeltaValue(String deltaValue) {
		this.deltaValue = deltaValue;
	}

}
