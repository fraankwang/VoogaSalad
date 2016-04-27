package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;

import engine.backend.components.IComponent;

public class AuthoringEntity {
	
	private static final String TYPE = "Type";
	private static final String GENRE = "Genre";
	private static final String NAME = "Name";
	private static final String CLASS_TYPE = "Entity";
	private static final String COMMA_SPLIT = ",";
	private static final String SEMICOLON_SPLIT = ":";
	private static final String UNDERSCORE_SPLIT = "_";
	
	private String myName;
	private String myType;
	private Map<String, String> myInfo;
	private Map<String, IComponent> myComponents;
	
	public AuthoringEntity(String myName, String myType) {
		this.myName = myName;
		this.myType = myType;
		this.myInfo = new HashMap<String, String>();
		this.myComponents = new HashMap<String, IComponent>();
		initializeInfo();
	}
	
	private void initializeInfo() {
		myInfo.put(TYPE, CLASS_TYPE);
		myInfo.put(GENRE, myType);
		myInfo.put(NAME, myName);
	}
	
	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
		if (!myType.equals("Spawner")) {
			String[] componentInfo = component.getComponentInfo().split(COMMA_SPLIT);
			for (String s : componentInfo) {
				String[] info = s.split(SEMICOLON_SPLIT);
				String tag = component.getTag() + UNDERSCORE_SPLIT + info[0];
				String value = info[1];
				myInfo.put(tag, value);
			}
		} else {
			String componentInfo = component.getComponentInfo();
			String tag = component.getTag();
			myInfo.put(tag, componentInfo);
		}
	}
	
	public Map<String, String> getInfo() {
		return myInfo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof AuthoringEntity) {
			AuthoringEntity entity = (AuthoringEntity) o;
			if (this.myName.equals(entity.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}