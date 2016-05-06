package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;

public abstract class AuthoringObject {
	
	protected static final String TYPE = "Type";
	protected static final String NAME = "Name";
	protected static final String CLASS_TYPE = "Entity";
	protected static final String COMMA_SPLIT = ",";
	protected static final String SEMICOLON_SPLIT = ":";
	protected static final String UNDERSCORE_SPLIT = "_";
	protected static final String SPACE_SPLIT = " ";
	protected static final String PLUS_SPLIT = "+";
	protected static final String EMPTY = "";
	
	private String myName;
	private Map<String, String> myInfo;
	
	public AuthoringObject(String myName) {
		this.myName = myName;
		this.myInfo = new HashMap<String, String>();
		initializeInfo();
		initializeSpecificInfo();
	}
	
	private void initializeInfo() {
		myInfo.put(TYPE, this.getClass().getSimpleName());
		myInfo.put(NAME, myName);
	}
	
	protected abstract void initializeSpecificInfo();
	
	public Map<String, String> getInfo() {
		return myInfo;
	}
	
	public String getName() {
		return myName;
	}
	
	public void setName(String name) {
		this.myName = name;
		myInfo.put(NAME, myName);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof AuthoringObject) {
			AuthoringObject author = (AuthoringObject) object;
			if (this.myName.equals(author.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
