package authoring.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringLevel;
import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;

public class LevelFactory {
	
	public LevelFactory() {
		
	}
	
	public Level createLevel(AuthoringLevel authoringLevel, Map<String, IEntity> entityMap) {
		String name = authoringLevel.getName();
		GameMap map = authoringLevel.getMap();
		double waveDelayTimer = authoringLevel.getWaveDelayTimer();
		Set<String> entityNames = authoringLevel.getEntities();
		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		for (String key : entityNames) {
			IEntity entity = entityMap.get(key);
			authoredEntities.add(entity);
		}
		
		return new Level(name, map, waveDelayTimer, authoredEntities);
	}

}
