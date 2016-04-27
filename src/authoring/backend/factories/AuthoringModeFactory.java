package authoring.backend.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringMode;

public class AuthoringModeFactory {

	public AuthoringModeFactory() {
		
	}
	
	public AuthoringMode createMode(Map<String, String> data){
		String name = "";
		Set<String> levelNames = new HashSet<String>();
		int initialLives = 0;
		int initialResources = 0;
		for (String key : data.keySet()) {
			switch (key) {
			
			case "Name":
				name = data.get(key);
				break;
			case "LevelNames":
				levelNames = getLevelNames(data.get(key));
				break;
			case "InitialLives":
				initialLives = Integer.parseInt(data.get(key));
				break;
			case "InitialResources":
				initialResources = Integer.parseInt(data.get(key));
				break;
				
			}
		}
		return new AuthoringMode(name, initialLives, initialResources, levelNames);
	}
	
	private Set<String> getLevelNames(String str) {
		String[] names = str.split(" ");
		Set<String> levelNames = new HashSet<String>();
		for (int i = 0; i < names.length; i++) {
			levelNames.add(names[i]);
		}
		return levelNames;
	}

}
