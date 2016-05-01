package authoring.backend.deprecated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import engine.backend.game_object.Mode;

public class ModeList extends Observable {

	private List<Mode> modes;

	public ModeList() {

	}

	public List<Mode> getList() {
		return modes;
	}

	public void add(Mode mode) {
		for (Mode m : modes) {
			if (m.equals(mode)) {
				m = mode;
				setChanged();
				notifyObservers(getInfo());
			}
		}
		modes.add(mode);
		setChanged();
		notifyObservers(getInfo());
	}

	public List<Map<String, String>> getInfo() {
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		for (Mode mode : modes) {
			info.add(mode.getInfo());
		}
		return info;
	}

}
