package authoring.backend.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class ObservableList<E> extends Observable {
	
	private List<E> objects;
	
	public ObservableList(List<E> objects) {
		this.objects = objects;
	}
	
	public ObservableList() {
		this.objects = new ArrayList<E>();
	}
	
	public void add(E object) {
		for (E e : objects) {
			if (e.equals(object)) {
				e = object;
				setChanged();
				notifyObservers();
				return;
			}
		}
		objects.add(object);
		setChanged();
		notifyObservers();
	}
	
	public List<E> getList() {
		return objects;
	}
	
	public List<Map<String, String>> getInfo() {
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		for (E object : objects) {
			if (object instanceof Entity) {
				Entity entity = (Entity) object;
				info.add(entity.getInfo());
			} else if (object instanceof Level) {
				Level level = (Level) object;
				info.add(level.getInfo());
			} else if (object instanceof Mode) {
				Mode mode = (Mode) object;
				info.add(mode.getInfo());
			}
		}
		return info;
	}

}
