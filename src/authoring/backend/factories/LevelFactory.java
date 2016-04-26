package authoring.backend.factories;

import authoring.backend.game_objects.AuthoringLevel;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;

public class LevelFactory {
	
	public LevelFactory() {
		
	}
	
	public Level createLevel(AuthoringLevel authoringLevel) {
		String name = authoringLevel.getName();
		GameMap map = authoringLevel.getMap();
		double waveDelayTimer = authoringLevel.getWaveDelayTimer();
		
		return new Level(name, map, waveDelayTimer);
	}

}
