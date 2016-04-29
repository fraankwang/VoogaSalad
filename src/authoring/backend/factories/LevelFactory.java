package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;

public class LevelFactory {
	
	private EntityFactory entityFactory;
	
	public LevelFactory() {
		this.entityFactory = new EntityFactory();
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
		List<AuthoringEntity> spawnEntities = authoringLevel.getSpawnEntities();
		Map<Integer, IEntity> entitiesMap = new HashMap<Integer, IEntity>();
		int entityID = 0;
		for (AuthoringEntity authoringEntity : spawnEntities) {
			IEntity spawnEntity = entityFactory.createEntity(authoringEntity);
			authoredEntities.add(spawnEntity);
			entitiesMap.put(entityID, spawnEntity);
			entityID++;
		}
		
		return new Level(name, map, waveDelayTimer, authoredEntities, entitiesMap);
	}

}
