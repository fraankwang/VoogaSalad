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
		setMyEntities(new ArrayList<>());
		setMyComponents(new ArrayList<>());
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

	public List getMyEntities() {
		return myEntities;
	}

	public void setMyEntities(List myEntities) {
		this.myEntities = myEntities;
	}

	public List getMyComponents() {
		return myComponents;
	}

	public void setMyComponents(List myComponents) {
		this.myComponents = myComponents;
	}

}
