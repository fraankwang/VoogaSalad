// This entire file is part of my masterpiece.
// Raghav Kedia

/**
 * This is our Entity class, and it is the heart of our entity-component-system design. In our design, all game objects are entities
 * that contain different components, and each entity has a unique ID. Through this design, you can customize and entity just by adding
 * different components to it. There is absolutely no inheritance involved. I think this code clearly exhibits great composition design. 
 * I also like this code because it makes use of two interfaces. One IEntity interface while is the interface that the rest of the program 
 * interacts with, and one IModifiable interface which means that an Entity object can be modified through an actions, because it contains 
 * an applyAction() method. This applyAction() method is used to update the components inside and entity when the event manager is enforcing rules. 
 * As you can see, because any component, and any variable inside a component can be modified, to generalize the whole process I make use of 
 * reflection to produce the right method inside a specific component class. This code is also good because it is very readable and modular. 
 *  
 */

package engine.backend.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import engine.backend.components.IComponent;
import engine.backend.game_object.IModifiable;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;
import engine.backend.utilities.ComponentTagResources;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;

/**
 * 
 * @author raghavkedia
 *
 */

public class Entity extends Observable implements IEntity, IModifiable {

	private static final String PREFIX = "set";
	private static final String LACK_ACCESS = "LackAccessToClass";
	private static final String METHOD_DNE = "MethodDoesNotExist";
	private static final String SECURITY_EXCEPTION = "SecurityException";
	private static final String ILLEGAL_ARGS = "IllegalArguments";
	private static final String INSTANTIATION = "ReflectionInstantiation";

	private String myName;
	private String myGenre;
	private int myID;
	private Map<String, IComponent> myComponents;
	private boolean hasBeenModified;

	private EntityStatistics myStats;

	/**
	 * Initializes an Entity without a unique ID. Authoring Environment
	 * Constructor.
	 */
	public Entity(String myName, String myGenre, Map<String, IComponent> myComponents) {
		this.myName = myName;
		this.myGenre = myGenre;
		this.myComponents = myComponents;
		myStats = new EntityStatistics();
	}

	/**
	 * Engine Testing Constructor.
	 */
	public Entity(int myID, String myName, String myGenre) {
		this.myName = myName;

		this.myGenre = myGenre;
		this.myID = myID;
		this.myComponents = new HashMap<String, IComponent>();
		this.myStats = new EntityStatistics();
	}

	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
	}

	public IComponent getComponent(String tag) {
		if (myComponents.containsKey(tag)) {
			return myComponents.get(tag);
		}
		// find substring tag
		Set<String> keys = myComponents.keySet();
		for (String key : keys) {
			if (key.contains(tag)) {
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

	public void setLevelID(int levelID) {
		myID = levelID;
	}

	/**
	 * @return A string that represents the name of the entity.
	 */
	public String getName() {
		return myName;
	}

	/**
	 * @return A string that represents the type of the entity.
	 */
	public String getGenre() {
		return myGenre;
	}

	public boolean hasBeenModified() {
		return hasBeenModified;
	}

	public void setHasBeenModified(boolean bool) {
		hasBeenModified = bool;
	}

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}

	public EntityStatistics getStats() {
		for (IComponent component : myComponents.values()) {
			myStats.addStat(component.getComponentInfo());
		}

		return myStats;
	}

	@Override
	public void applyAction(IAction action) {
		String component = ((EntityAction) action).getComponentToModifiy();
		String instanceVar = ((EntityAction) action).getValueInComponent();
		String newVal = ((EntityAction) action).getNewValue();
		Method setMethod;

		String fullName = ComponentTagResources.getComponentTag(component);
		Class<? extends IComponent> componentClass = getComponent(fullName).getClass();
		ExceptionLoader myExceptionLoader = new ExceptionLoader();
		try {
			Object componentClassInstance = componentClass.newInstance();

			componentClassInstance = componentClass.cast(getComponent(fullName));

			String methodName = PREFIX + instanceVar;
			setMethod = componentClassInstance.getClass().getMethod(methodName, String.class);

			setMethod.invoke(componentClassInstance, newVal);
		} catch (InstantiationException e) {
			new DrumpfTowerException(myExceptionLoader.getString(INSTANTIATION));
		} catch (IllegalAccessException e) {
			new DrumpfTowerException(myExceptionLoader.getString(LACK_ACCESS));
		} catch (NoSuchMethodException e) {
			new DrumpfTowerException(myExceptionLoader.getString(METHOD_DNE));
		} catch (SecurityException e) {
			new DrumpfTowerException(myExceptionLoader.getString(SECURITY_EXCEPTION));
		} catch (IllegalArgumentException | InvocationTargetException e) {
			new DrumpfTowerException(myExceptionLoader.getString(ILLEGAL_ARGS));
		}
	}

	public void broadcastEntity() {
		setChanged();
		notifyObservers();
	}
}