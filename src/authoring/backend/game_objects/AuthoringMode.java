package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class AuthoringMode {

	private String myName;
	private int initialNumLives;
	private double initialResources;

	private Map<String, String> myInfo;
	private Map<Integer, String> levels;

	public AuthoringMode(String myName, int initialNumLives, double initialResources, Map<Integer, String> levels) {
		this.myName = myName;
		this.levels = levels;
		this.initialNumLives = initialNumLives;
		this.initialResources = initialResources;
		this.myInfo = new HashMap<String, String>();
		initializeInfo();
	}

	public AuthoringMode(Mode mode) {
		this.myName = mode.getName();
		this.levels = getLevelMap(mode.getLevels());
		this.initialNumLives = mode.getGameStatistics().getInitialNumLives();
		this.initialResources = mode.getGameStatistics().getInitialResources();
		initializeInfo();
	}

	private void initializeInfo() {
		myInfo.put("Type", "Mode");
		myInfo.put("Name", myName);
		myInfo.put("InitialLives", initialNumLives + "");
		myInfo.put("InitialResources", initialResources + "");
		myInfo.put("Levels", getStringLevelIndexes());
	}

	private Map<Integer, String> getLevelMap(Map<Integer, Level> levels) {
		Map<Integer, String> levelMap = new TreeMap<Integer, String>();
		for (int key : levels.keySet()) {
			String levelname = levels.get(key).getName();
			levelMap.put(key, levelname);
		}
		return levelMap;
	}

	private String getStringLevelIndexes() {
		if (levels.isEmpty()) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for (int key : levels.keySet()) {
				sb.append(key);
				sb.append(":");
				sb.append(levels.get(key));
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}

	public Map<String, String> getInfo() {
		return myInfo;
	}

	public String getName() {
		return myName;
	}

	public int getInitialLives() {
		return initialNumLives;
	}

	public double getInitialResources() {
		return initialResources;
	}

	public Map<Integer, String> getLevels() {
		return levels;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AuthoringMode) {
			AuthoringMode mode = (AuthoringMode) o;
			if (this.myName.equals(mode.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
