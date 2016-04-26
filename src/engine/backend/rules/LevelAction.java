package engine.backend.rules;

public class LevelAction implements IAction{
	
	private String variableToModify;
	private String deltaValue;

	public LevelAction(String varToModify, String delta) {
		this.setVariableToModify(varToModify);
		this.setDeltaValue(delta);
	}

	public String getVariableToModify() {
		return variableToModify;
	}

	public void setVariableToModify(String variableToModify) {
		this.variableToModify = variableToModify;
	}

	public String getDeltaValue() {
		return deltaValue;
	}

	public void setDeltaValue(String deltaValue) {
		this.deltaValue = deltaValue;
	}

}
