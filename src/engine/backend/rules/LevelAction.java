package engine.backend.rules;

public class LevelAction {
	private String resourceToModify;
	private String changeInValue;

	public LevelAction(String resourceToModify, String changeInValue) {
		this.resourceToModify = resourceToModify;
		this.changeInValue = changeInValue;
	}

	public String getResourceToModify() {
		return resourceToModify;
	}

	public String getChangeInValue() {
		return changeInValue;
	}

}
