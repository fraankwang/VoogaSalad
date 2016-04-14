package authoring.backend;

import java.util.List;
import java.util.Observable;

public class ObservableList<E> extends Observable {
	
	private List<E> objects;
	
	public ObservableList(List<E> objects) {
		this.objects = objects;
	}
	
	public ObservableList() {
		
	}
	
	public void add(E object) {
		objects.add(object);
		setChanged();
		notifyObservers();
	}
	
	public List<E> getList() {
		return objects;
	}

}
