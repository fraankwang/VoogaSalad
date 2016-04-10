package backend.game_object.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.game_object.components.Component;
import backend.game_object.components.IComponent;

public class Entity implements IEntity{
	
	private String myLabel;
	private int myID;
	private int myLevelId;
	//private List<Component> myComponents;
	private Map<String, IComponent> myComponents;

	public Entity(int ID) {
		myComponents = new HashMap<String, IComponent>();
		this.myID = ID;
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
	
	public int getLevelId(){
		return this.myLevelId;
	}
	
	public int getId(){
		return this.myID;
	}
	
	public boolean hasComponent(String tag){
		return myComponents.get(tag) != null;
	}

}
