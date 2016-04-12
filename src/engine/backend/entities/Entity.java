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
	
	// private List<Component> myComponents;
	private Map<String, IComponent> myComponents = new HashMap<String, IComponent>();;

	private boolean hasBeenModified = false;

	public Entity(int ID, String name, String type, double value) {
		myName = name;
		myType = type;
		this.myID = ID;
		this.setValue(value);
	}
	
	public Entity(int ID){
		this.myID = ID;
	}

	public List<Rule> getRules() {
		return myRules;
	}

	public void addRule(Rule myRule) {
		myRules.add(myRule);
	}

	public void addComponent(IComponent component) {
		System.out.println(myComponents);
		myComponents.put(component.getTag(), component);
	}

	public IComponent getComponent(String tag) {
		return myComponents.get(tag);
	}

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}

	public Set<String> getComponentTags() {
		// sort alphabetically
		return myComponents.keySet();
	}

	public Collection<IComponent> getComponents() {
		return myComponents.values();
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

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
