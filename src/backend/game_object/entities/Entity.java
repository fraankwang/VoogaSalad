package backend.game_object.entities;

import java.util.ArrayList;
import java.util.List;

import backend.game_object.components.Component;

public class Entity {

	private int myID;
	private List<Component> myComponents;

	public Entity(int ID) {
		myComponents = new ArrayList<Component>();
		this.myID = ID;
	}

	public void addComponent(Component component) {
		myComponents.add(component);
	}
	
	public Component getComponent(String tag){
		for(Component component : myComponents){
			if(component.getTag().equals(tag)){
				return component;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + myComponents + "]";
	}
	
	public List<String> getComponentTags(){
		List<String> tags = new ArrayList<String>();
		
		for(Component component : myComponents){
			tags.add(component.getTag());
		}
		
		//sort alphabetically
		java.util.Collections.sort(tags);
		
		return tags;
	}
	
	public List<Component> getComponents(){
		return myComponents;
	}

}
