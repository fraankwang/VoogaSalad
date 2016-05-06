package authoring.backend.game_objects;

import java.util.Map;
import java.util.TreeMap;

import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class AuthoringMode extends AuthoringObject {
	
	private static final String LIVES = "InitialLives";
	private static final String RESOURCES = "InitialResources";
	private static final String LEVELS = "Levels";

	private int initialNumLives;
	private double initialResources;

	private Map<Integer, String> levels;

	public AuthoringMode(String myName, int initialNumLives, double initialResources, Map<Integer, String> levels) {
		super(myName);
		this.levels = levels;
		this.initialNumLives = initialNumLives;
		this.initialResources = initialResources;
	}

	public AuthoringMode(Mode mode) {
		super(mode.getName());
		this.levels = getLevelMap(mode.getLevels());
		this.initialNumLives = mode.getGameStatistics().getInitialNumLives();
		this.initialResources = mode.getGameStatistics().getInitialResources();
	}
	
	@Override
	protected void initializeSpecificInfo() {
		getInfo().put(LIVES, initialNumLives + EMPTY);
		getInfo().put(RESOURCES, initialResources + EMPTY);
		getInfo().put(LEVELS, getStringLevelIndexes());
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
			return EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int key : levels.keySet()) {
				sb.append(key);
				sb.append(SEMICOLON_SPLIT);
				sb.append(levels.get(key));
				sb.append(SPACE_SPLIT);
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
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

}
