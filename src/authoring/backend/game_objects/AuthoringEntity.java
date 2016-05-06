//This entire file is part of my masterpiece
//Jonathan Ma

/*
 * This is a subclass of the AuthoringObject abstract class. It acts primarily as a mirror to the Entity
 * class in the engine. The reason for splitting up the objects in the authoring environment from the 
 * objects in the engine is that a lot of the data in the Entity class was getting colluded. This means that
 * engine was starting to have access to data that should only exist in the authoring environment, which 
 * is really bad design. This class is included in the masterpiece to show how a subclass of the AuthoringObject
 * might look and to show how the AuthoringEntity class has changed after being subclassed. The rest of the 
 * design considerations and analysis for this masterpiece can be found in the comments in AuthoringObject.
 */

package authoring.backend.game_objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import engine.backend.components.IComponent;
import engine.backend.entities.IEntity;

public class AuthoringEntity extends AuthoringObject {
	
	private static final String GENRE = "Genre";
	private static final String SPAWNER = "Spawner";
		
	private String myGenre;
	private Map<String, IComponent> myComponents;

	public AuthoringEntity(String myName, String myGenre) {
		super(myName);
		this.myGenre = myGenre;
		this.myComponents = new HashMap<String, IComponent>();
	}

	public AuthoringEntity(IEntity entity) {
		super(entity.getName());
		this.myGenre = entity.getGenre();
		this.myComponents = new HashMap<String, IComponent>();
		setUpComponents(entity.getComponents());
	}

	private void setUpComponents(Collection<IComponent> components) {
		for (IComponent component : components) {
			addComponent(component);
		}
	}

	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
		if (!myGenre.equals(SPAWNER)) {
			String[] componentInfo = component.getComponentInfo().split(COMMA_SPLIT);
			for (String s : componentInfo) {
				String[] info = s.split(SEMICOLON_SPLIT);
				String tag = component.getTag() + UNDERSCORE_SPLIT + info[0];
				String value = info[1];
				getInfo().put(tag, value);
			}
		} else {
			String componentInfo = component.getComponentInfo();
			String tag = component.getTag();
			getInfo().put(tag, componentInfo);
		}
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
	protected void initializeSpecificInfo() {
		getInfo().put(GENRE, myGenre);
	}

}
