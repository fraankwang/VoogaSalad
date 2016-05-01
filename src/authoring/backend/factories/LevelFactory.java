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
import engine.backend.components.PositionComponent;
import engine.backend.components.PurchaseComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.IEntity;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.Level;
import engine.backend.map.GameMap;
import engine.backend.utilities.ComponentTagResources;

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
			entity.addComponent(new PositionComponent());
			authoredEntities.add(entity);
		}
		List<AuthoringEntity> spawnEntities = authoringLevel.getSpawnEntities();
		Map<Integer, IEntity> entitiesMap = new HashMap<Integer, IEntity>();
		int entityID = 0;
		int numWaves = 1;
		for (AuthoringEntity spawnEntity : spawnEntities) {
			SpawnerComponent component = (SpawnerComponent) spawnEntity.getComponent("SpawnerComponent");
			for (Spawn spawn : component.getSpawns()) {
				for (IEntity authoredEntity : authoredEntities) {
					if (authoredEntity.getName().equals(spawn.getSpawningEntityName())) {
						authoredEntity.addComponent(new PathComponent());
						authoredEntity.addComponent(new PositionComponent());
					}
				}
				int spawnWaves = spawn.getWaveIndex() + 1;
				if (spawnWaves > numWaves) {
					numWaves = spawnWaves;
				}
			}

			IEntity entity = entityFactory.createEntity(spawnEntity);
			entity.setID(entityID);
			authoredEntities.add(entity);
			entitiesMap.put(entityID, entity);
			entityID++;
		}
		List<ShopItem> shopItems = new ArrayList<ShopItem>();
		for (IEntity entity : authoredEntities) {
			if (entity.hasComponent("PurchaseComponent")) {
				DisplayComponent displayComponent = (DisplayComponent) entity.getComponent("DisplayComponent");
				PurchaseComponent purchaseComponent = (PurchaseComponent) entity.getComponent("PurchaseComponent");
				ShopItem item = new ShopItem(entity.getName(), displayComponent.getImage(),
						purchaseComponent.getValue());
				shopItems.add(item);
			}
			if (entity.hasComponent("MovementComponent")) {
				System.out.println(entity.getComponent("MovementComponent").getComponentInfo());
			}
		}

		return new Level(name, map, waveDelayTimer, numWaves, shopItems, authoredEntities, entitiesMap);
	}

}
