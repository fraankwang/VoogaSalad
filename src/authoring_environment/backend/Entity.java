package authoring_environment.backend;

import java.util.Observable;

public class Entity extends Observable {
	
	private int ID;
	
	public Entity(int ID) {
		this.ID = ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
		setChanged();
		notifyObservers();
	}
	
	public int getID() {
		return ID;
	}

}
