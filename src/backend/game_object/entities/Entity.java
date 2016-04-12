package backend.game_object.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.game_object.components.Component;
import backend.game_object.components.IComponent;
import backend.rules.Rule;

public class Entity implements IEntity{
	
	private String myLabel;
	private double myValue;
	private List<Rule> myRules;
	private int myID;
	//private List<Component> myComponents;
	private Map<String, IComponent> myComponents;

	public Entity(int ID, double value) {
		this.myRules = new ArrayList<Rule>();
		this.myComponents = new HashMap<String, IComponent>();
		this.myID = ID;
		this.setValue(value);
	}
	
	public List<Rule> getRules(){
		return myRules;
	}
	
	public void addRule(Rule myRule){
		myRules.add(myRule);
	}

	public void addComponent(IComponent component) {
		myComponents.put(component.getTag(), component);
	}
	
	public IComponent getComponent(String tag){
		return myComponents.get(tag);
	}
	

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}
	
	public Set<String> getComponentTags(){
		
		//sort alphabetically
		return myComponents.keySet();
		
	}
	
	public Collection<IComponent> getComponents(){
		return myComponents.values();
	}

	public String getLabel() {
		return myLabel;
	}

	public void setLabel(String label) {
		this.myLabel = label;
	}
	
	public boolean hasComponent(String tag){
		return myComponents.get(tag) != null;
	}

	public double getValue() {
		return myValue;
	}

	public void setValue(double myValue) {
		this.myValue = myValue;
	}

}
