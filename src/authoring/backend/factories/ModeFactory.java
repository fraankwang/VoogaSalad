package authoring.backend.factories;

import java.util.HashMap;
import java.util.Map;

import authoring.backend.game_objects.AuthoringMode;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.game_object.ModeStatistics;

public class ModeFactory {
	
	public ModeFactory() {
	
	}
	
	public Mode createMode(AuthoringMode authoringMode, Map<String, Level> levelMap) {
		String name = authoringMode.getName();
		int numLives = authoringMode.getInitialLives();
		double resources = authoringMode.getInitialResources();
		ModeStatistics modeStatistics = new ModeStatistics(numLives, resources);
		
		Map<Integer, String> levelIndex = authoringMode.getLevelIndex();
		Map<Integer, Level> levels = new HashMap<Integer, Level>();
		
		for (int key : levelIndex.keySet()) {
			String levelName = levelIndex.get(key);
			Level level = levelMap.get(levelName);
			levels.put(key, level);
		}
		
		return new Mode(name, modeStatistics, levels);
	}
}
