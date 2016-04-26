package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthoringMode {
	
	private String myName;
	private int initialNumLives;
	private double initialResources;
	
	private Set<String> levels;
	private Map<String, String> myInfo;
	
	public AuthoringMode(String myName, int initialNumLives, double initialResources, Set<String> levels) {
		this.myName = myName;
		this.levels = levels;
		this.initialNumLives = initialNumLives;
		this.initialResources = initialResources;
		this.myInfo = new HashMap<String, String>();
		initializeInfo();
	}
	
	private void initializeInfo() {
		myInfo.put("Type", "Mode");
		myInfo.put("Name", myName);
		myInfo.put("InitialLives", initialNumLives + "");
		myInfo.put("InitialResources", initialResources + "");
		myInfo.put("LevelNames", getLevelNames());
	}
	
	private String getLevelNames() {
		StringBuilder sb = new StringBuilder();
		for (String level : levels) {
			sb.append(level);
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
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
