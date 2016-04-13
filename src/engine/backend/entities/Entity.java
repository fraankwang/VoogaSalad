package engine.backend.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.components.IComponent;
import engine.backend.rules.Rule;

public class Entity implements IEntity {

	private String myName;
	private String myType;
	private double myValue;
	private List<Rule> myRules = new ArrayList<Rule>();;
	private int myID;
	private int myParentLevelID;
	private Map<String, IComponent> myComponents = new HashMap<String, IComponent>();;

	private boolean hasBeenModified = false;

	public Entity(int myID, String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		this.myID = myID;
		this.myValue = myValue;
	}
	
	public Entity(String myName, String myType, double myValue) {
		this.myName = myName;
		this.myType = myType;
		this.myValue = myValue;
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

	public double getValue() {
		return myValue;
	}

	public void setValue(double myValue) {
		this.myValue = myValue;
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

}
