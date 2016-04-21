package engine.backend.entities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.components.IComponent;
import engine.backend.rules.EntityAction;
import engine.backend.rules.Rule;

public class Entity implements IEntity {

	private String myName;
	private String myType;
	private List<Rule> myRules = new ArrayList<Rule>();;
	private int myID;
	private int myParentLevelID;
	private Map<String, IComponent> myComponents = new HashMap<String, IComponent>();

	private boolean hasBeenModified = true;

	public Entity(int myID, String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		this.myID = myID;
		//this.myValue = myValue;
	}
	
	public Entity(String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		//this.myValue = myValue;
	}

	public List<Rule> getRules() {
		return myRules;
	}

	public void addRule(Rule myRule) {
		myRules.add(myRule);
	}

	public void addComponent(IComponent component) {
		component.setEntityName(myName);
		myComponents.put(component.getTag(), component);
	}

	public IComponent getComponent(String tag) {
		return myComponents.get(tag);
	}

	public Set<String> getComponentTags() {
		return myComponents.keySet();
	}

	public Collection<IComponent> getComponents() {
		return myComponents.values();
	}
	
	public void setID(int myID) {
		this.myID = myID;
	}
	
	public int getID(){
		return myID;
	}

	public String getName() {
		return myName;
	}

	public void setMane(String name) {
		this.myName = name;
	}

	public boolean hasComponent(String tag) {
		return myComponents.get(tag) != null;
	}

	public boolean hasBeenModified() {
		return hasBeenModified;
	}

	public void setHasBeenModified(boolean bool) {
		hasBeenModified = bool;
	}

	public int getLevelID() {
		return myParentLevelID;
	}

	public void setLevelID(int levelID) {
		this.myParentLevelID = levelID;
	}

	public String getType() {
		return myType;
	}

	public void setMyType(String myType) {
		this.myType = myType;
	}
	
	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Entity) {
			Entity temp = (Entity) o;
			if (this.myName.equals(temp.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyAction(EntityAction action, ResourceBundle myComponentTagResources) {
		String component = action.getComponentToModifiy();
		String instanceVar = action.getValueInComponent();
		String newVal = action.getNewValue();
		Method setMethod;
		
		String fullName = myComponentTagResources.getString(component);
		Class<? extends IComponent> componentClass = myComponents.get(fullName).getClass();
		
		try {
			Object componentClassInstance = componentClass.newInstance();
			componentClassInstance = componentClass.cast(myComponents.get(fullName));
			//put in resource file!!!
			String methodName = "set" + instanceVar;
			
			setMethod = componentClassInstance.getClass().getMethod(methodName, String.class);
			
			setMethod.invoke(componentClassInstance, newVal);
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
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