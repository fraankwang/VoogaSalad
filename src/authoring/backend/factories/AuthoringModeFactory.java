package authoring.backend.factories;

import java.util.Map;
import java.util.TreeMap;

import authoring.backend.game_objects.AuthoringMode;

public class AuthoringModeFactory {

	public AuthoringModeFactory() {
		
	}
	
	public AuthoringMode createMode(Map<String, String> data){
		String name = "";
		Map<Integer, String> levelMap = new TreeMap<Integer, String>();
		int initialLives = 0;
		int initialResources = 0;
		for (String key : data.keySet()) {
			switch (key) {
			
			case "Name":
				name = data.get(key);
				break;
			case "Levels":
				levelMap = getLevels(data.get(key));
				break;
			case "InitialLives":
				initialLives = Integer.parseInt(data.get(key));
				break;
			case "InitialResources":
				initialResources = Integer.parseInt(data.get(key));
				break;
				
			}
		}
		return new AuthoringMode(name, initialLives, initialResources, levelMap);
	}
	
	private Map<Integer, String> getLevels(String str) {
		String[] names = str.split(" ");
		Map<Integer, String> levelMap = new TreeMap<Integer, String>();
		for (String name : names) {
			String[] levelInfo = name.split(":");
			int levelIndex = Integer.parseInt(levelInfo[0]);
			String levelName = levelInfo[1];
			levelMap.put(levelIndex, levelName);
		}
		return levelMap;
	}

}
