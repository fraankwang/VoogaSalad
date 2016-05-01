package authoring.backend.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringGame;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;

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
				notifyObservers(getInfo());
				return;
			}
		}
		objects.add(object);
		setChanged();
		notifyObservers(getInfo());
	}

	public void remove(E object) {
		for (E e : objects) {
			if (e.equals(object)) {
				objects.remove(e);
				setChanged();
				notifyObservers(getInfo());
				return;
			}
		}
	}

	public List<E> getList() {
		return objects;
	}

	public List<Map<String, String>> getInfo() {
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		for (E object : objects) {
			if (object instanceof AuthoringEntity) {
				AuthoringEntity entity = (AuthoringEntity) object;
				info.add(entity.getInfo());
			} else if (object instanceof AuthoringLevel) {
				AuthoringLevel level = (AuthoringLevel) object;
				info.add(level.getInfo());
			} else if (object instanceof AuthoringMode) {
				AuthoringMode mode = (AuthoringMode) object;
				info.add(mode.getInfo());
			} else if (object instanceof AuthoringGame) {
				AuthoringGame game = (AuthoringGame) object;
				info.add(game.getInfo());
			}
		}
		return info;
	}

}
