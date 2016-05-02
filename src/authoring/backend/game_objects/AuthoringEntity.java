package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.components.IComponent;
import engine.backend.entities.IEntity;

public class AuthoringEntity {

	private static final String TYPE = "Type";
	private static final String GENRE = "Genre";
	private static final String NAME = "Name";
	private static final String CLASS_TYPE = "Entity";
	private static final String COMMA_SPLIT = ",";
	private static final String SEMICOLON_SPLIT = ":";
	private static final String UNDERSCORE_SPLIT = "_";

	private String myName;
	private String myGenre;
	private Map<String, String> myInfo;
	private Map<String, IComponent> myComponents;

	public AuthoringEntity(String myName, String myGenre) {
		this.myName = myName;
		this.myGenre = myGenre;
		this.myInfo = new HashMap<String, String>();
		this.myComponents = new HashMap<String, IComponent>();
		initializeInfo();
	}

	public AuthoringEntity(IEntity entity) {
		this.myName = entity.getName();
		this.myGenre = entity.getGenre();
		setUpComponents((List<IComponent>) entity.getComponents());
		initializeInfo();
	}

	private void initializeInfo() {
		myInfo.put(TYPE, CLASS_TYPE);
		myInfo.put(GENRE, myGenre);
		myInfo.put(NAME, myName);
	}

	private void setUpComponents(List<IComponent> components) {
		for (IComponent component : components) {
			addComponent(component);
		}
	}

	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
		if (!myGenre.equals("Spawner")) {
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

	public String getName() {
		return myName;
	}

	public String getGenre() {
		return myGenre;
	}

	public Map<String, IComponent> getComponents() {
		return myComponents;
	}

	public IComponent getComponent(String name) {
		return myComponents.get(name);
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
