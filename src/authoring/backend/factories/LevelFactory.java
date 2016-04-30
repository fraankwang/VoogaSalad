package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import engine.backend.components.DisplayComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PurchaseComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.IEntity;
import engine.backend.game_features.ShopItem;
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
		for (AuthoringEntity spawnEntity : spawnEntities) {
			SpawnerComponent component = (SpawnerComponent) spawnEntity.getComponent("SpawnerComponent");
			for (Spawn spawn : component.getSpawns()) {
				for (IEntity authoredEntity : authoredEntities) {
					if (authoredEntity.getName().equals(spawn.getSpawningEntityName())) {
						authoredEntity.addComponent(new PathComponent());
					}
				}
			}
			
			IEntity entity = entityFactory.createEntity(spawnEntity);
			authoredEntities.add(entity);
			entitiesMap.put(entityID, entity);
			entityID++;
		}
		List<ShopItem> shopItems = new ArrayList<ShopItem>();
		for (IEntity entity : authoredEntities) {
			if (entity.hasComponent("PurchaseComponent")) {
				DisplayComponent displayComponent = (DisplayComponent) entity.getComponent("DisplayComponent");
				PurchaseComponent purchaseComponent = (PurchaseComponent) entity.getComponent("PurchaseComponent");
				ShopItem item = new ShopItem(entity.getName(), displayComponent.getImage(), purchaseComponent.getValue());
				shopItems.add(item);		
			}
		}
		
		return new Level(name, map, waveDelayTimer, shopItems, authoredEntities, entitiesMap);
	}

}
