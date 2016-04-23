package engine.backend.rules;

public class LevelAction {
	
	private String resourceToModify;
	private String changeInValue;
	private int levelIndex;

	public LevelAction(String resourceToModify, String changeInValue, int levelIndex) {
		this.resourceToModify = resourceToModify;
		this.changeInValue = changeInValue;
		this.levelIndex = levelIndex;
	}

	public String getResourceToModify() {
		return resourceToModify;
	}

	public String getChangeInValue() {
		return changeInValue;
	}
	
	public int getLevelIndex() {
		return levelIndex;
	}

}
