package authoring.backend.factories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.backend.game_object.Mode;

public class ModeFactory {

	public ModeFactory() {
		
	}
	
	public Mode createMode(Map<String, String> data){
		//setUpMode(newMode, info);
		String name = null;
		Set<String> levelNames = new HashSet<String>();
		for (String key : data.keySet()) {
			if (key.equals("Name")) {
				name = data.get(key);
			}
			if (key.equals("LevelNames")) {
				levelNames = getLevelNames(data.get(key));
			}
		}
		return new Mode(name, levelNames);
	}
	
	private Set<String> getLevelNames(String str) {
		String[] names = str.split(" ");
		Set<String> levelNames = new HashSet<String>();
		for (int i = 0; i < names.length; i++) {
			levelNames.add(names[i]);
		}
		return levelNames;
	}
	
	private void setUpMode(Mode mode, Object info){
		//do other shit with string
	}
}
