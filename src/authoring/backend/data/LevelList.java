package authoring.backend.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import engine.backend.game_object.Level;

public class LevelList extends Observable{
	
	private List<Level> levels;
	
	public LevelList() {
		
	}
	
	public List<Level> getList() {
		return levels;
	}
	
	public void add(Level level) {
		levels.add(level);
		setChanged();
		notifyObservers(getInfo());
	}
	
	public List<Map<String, String>> getInfo() {
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		for (Level level : levels) {
			info.add(level.getInfo());
		}
		return info;
	}
	
}
