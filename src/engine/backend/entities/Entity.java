package engine.backend.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.backend.components.IComponent;
import engine.backend.rules.EntityAction;
import engine.backend.utilities.ComponentTagResources;

public class Entity implements IEntity {

	private String myName;
	private String myType;
	private int myID;
	private Map<String, IComponent> myComponents = new HashMap<String, IComponent>();

	private boolean hasBeenModified = true;

	public Entity(int myID, String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		this.myID = myID;
		// this.myValue = myValue;
	}

	public Entity(String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		// this.myValue = myValue;
	}

	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
	}

	public IComponent getComponent(String tag) {
		if(myComponents.containsKey(tag)){
			return myComponents.get(tag);
		}
		//find substring tag
		Set<String> keys = myComponents.keySet();
		for(String key : keys){
			if(key.contains(tag)){
				return myComponents.get(key);
			}
		}
		return null;
	}

	public Set<String> getComponentTags() {
		return myComponents.keySet();
	}

	/**
	 * Returns a set of components that this entity has.
	 */
	public Collection<IComponent> getComponents() {
		return myComponents.values();
	}

	/**
	 * Sets the unique identifier for this entity.
	 * 
	 * @param myID
	 */
	public void setID(int myID) {
		this.myID = myID;
	}

	/**
	 * @return The unique identifier for this entity.
	 */
	public int getID() {
		return myID;
	}

	/**
	 * @return A string that represents the name or type of the entity; this is
	 *         not a unique id.
	 */
	public String getName() {
		return myName;
	}

	/**
	 * @return A boolean representing whether this entity has the component with
	 *         the tag.
	 */
	public boolean hasComponent(String tag) {
		if(myComponents.containsKey(tag)){
			return true;
		}
		//find substring tag
		Set<String> keys = myComponents.keySet();
		for(String key : keys){
			if(key.contains(tag)){
				return true;
			}
		}
		return false;
	}

	public boolean hasBeenModified() {
		return hasBeenModified;
	}

	public void setHasBeenModified(boolean bool) {
		hasBeenModified = bool;
	}

	public String getType() {
		return myType;
	}

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}
 
	@Override
	public void applyAction(EntityAction action) {
		String component = action.getComponentToModifiy();
		String instanceVar = action.getValueInComponent();
		String newVal = action.getNewValue();
		Method setMethod;

		String fullName = ComponentTagResources.getComponentTag(component);
		//System.out.println(getName() + "   " + fullName);
		Class<? extends IComponent> componentClass = myComponents.get(fullName).getClass();
		//System.out.println(componentClass.getName());
		try {
			Object componentClassInstance = componentClass.newInstance();
			
			componentClassInstance = componentClass.cast(myComponents.get(fullName));
			// put in resource file!!!
			String methodName = "set" + instanceVar;

			setMethod = componentClassInstance.getClass().getMethod(methodName, String.class);

			setMethod.invoke(componentClassInstance, newVal);

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}