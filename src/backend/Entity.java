package backend;

import java.util.ArrayList;
import java.util.List;

public class Entity {

	private int myID;
	private List<Component> components;

	public Entity(int ID) {
		components = new ArrayList<Component>();
		this.myID = ID;
	}

	public void addComponent(Component component) {
		components.add(component);
	}
	
	public List<Component> getComponents() {
		return components;
	}

	@Override
	public String toString() {
		return "Entity [myID=" + myID + ", components=" + components + "]";
	}
	
	public int getMyID() {
		return myID;
	}

}
