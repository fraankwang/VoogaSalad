package authoring_environment.backend.deprecated;

import java.util.ArrayList;
import java.util.List;

public class GameProperties {
	private List myModes;
	private List myLevels;
	private List myEntities;
	private List myComponents;
	
	public GameProperties() {
		setMyModes(new ArrayList<>());
		setMyLevels(new ArrayList<>());
		myEntities = new ArrayList<>();
		myComponents = new ArrayList<>();
	}

	public List getMyModes() {
		return myModes;
	}

	public void setMyModes(List myModes) {
		this.myModes = myModes;
	}

	public List getMyLevels() {
		return myLevels;
	}

	public void setMyLevels(List myLevels) {
		this.myLevels = myLevels;
	}

}
