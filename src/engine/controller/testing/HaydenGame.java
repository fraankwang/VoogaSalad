package engine.controller.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.components.CollisionComponent;
import engine.backend.components.DisplayComponent;
import engine.backend.components.FiringComponent;
import engine.backend.components.HealthComponent;
import engine.backend.components.IComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.components.TrackingMovementComponent;
import engine.backend.components.Vector;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.rules.EntityAction;
import engine.backend.rules.LevelAction;
import engine.backend.rules.Rule;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class HaydenGame implements ITestingGame{

	public GameWorld initGame() {
		GameWorld austin = new GameWorld();
		Mode mode = new Mode("Duffy");
		Level level = getLevel();
		//Level level2 = getLevel2();
		mode.addLevel(level);
		//mode.addLevel(level2);
		GameStatistics gameStats = new GameStatistics(50, 1000);
		mode.setGameStatistics(gameStats);
		austin.addMode(mode);

		return austin;
	}

	private Level getLevel() {
		Level level = new Level("level1");
		level.setIndex(0);
		EntityAction action = new EntityAction("tempEntity", "Display", "Delete", "true");
		EntityAction action4 = new EntityAction("tempEntity", "Display", "CanBeShown", "false");
		EntityAction action2 = new EntityAction("tempEntity", "Health", "Health", "-5");
		EntityAction action3 = new EntityAction("SimpleBullet", "Display", "CanBeShown", "false");
		EntityAction action5 = new EntityAction("SimpleBullet", "Display", "Delete", "true");

		EntityAction keyActionLeft = new EntityAction("tempEntity2", "Position", "XCoordinate", "-5");
		EntityAction keyActionRight = new EntityAction("tempEntity2", "Position", "XCoordinate", "5");
		EntityAction keyActionDown = new EntityAction("tempEntity2", "Position", "YCoordinate", "5");
		EntityAction keyActionUp = new EntityAction("tempEntity2", "Position", "YCoordinate", "-5");

		Rule ruleKeyLeft = new Rule();
		ruleKeyLeft.addActions(keyActionLeft);
		ruleKeyLeft.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEvent-LEFT"));
		Rule ruleKeyRight = new Rule();
		ruleKeyRight.addActions(keyActionRight);
		ruleKeyRight.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEvent-RIGHT"));
		Rule ruleKeyUp = new Rule();
		ruleKeyUp.addActions(keyActionUp);
		ruleKeyUp.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-UP"));
		Rule ruleKeyDown = new Rule();
		ruleKeyDown.addActions(keyActionDown);
		ruleKeyDown.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-DOWN"));

		LevelAction levelAction = new LevelAction("CurrentNumLives", "-1");
		LevelAction levelAction2 = new LevelAction("CurrentResources", "4");

		List<EntityAction> myActions = new ArrayList<EntityAction>();
		myActions.add(action);
		List<String> myEvents = new ArrayList<String>();
		myEvents.add("SimpleBullet-tempEntity-CollisionEvent");
		myEvents.add("tempEntity-DeathEvent");

		Rule rule1 = new Rule();
		rule1.addActions(Arrays.asList(action2, action3, action5));
		rule1.addEvents(Arrays.asList("SimpleBullet-tempEntity-CollisionEvent"));

		Rule rule2 = new Rule();
		rule2.addActions(Arrays.asList(action, action4));
		rule2.addEvents(Arrays.asList("tempEntity-DeathEvent"));

		Rule rule3 = new Rule();
		EntityAction shootAction = new EntityAction("tempEntity2", "Firing", "FireNow", "true");
		rule3.addActions(Arrays.asList(shootAction));
		rule3.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-S"));

		Rule rule4 = new Rule();
		rule4.addActions(Arrays.asList(action3, action5));
		rule4.addEvents(Arrays.asList("SimpleBullet-OutOfMapEvent"));

		Rule rule5 = new Rule();
		rule5.addActions(Arrays.asList(levelAction, action, action4, levelAction2));
		rule5.addEvents(Arrays.asList("tempEntity-EndOfPathEvent"));

		level.setRuleAgenda(
				Arrays.asList(rule1, rule2, rule3, rule4, rule5, ruleKeyUp, ruleKeyDown, ruleKeyLeft, ruleKeyRight));
		// level.addActionToEventMap(Arrays.asList("SimpleBullettempEntityCollisionEvent"),
		// myActions);
		
		//world is going to be 900 x 600
		
		
		
		BezierCurve tempCurve1 = new BezierCurve(900, 60, 900, 60, 0, 60, 0, 60);
		BezierCurve delay1 = new BezierCurve(0, 60, 0, 60, 0, 60, 0, 60);
		
		BezierCurve tempCurve2 = new BezierCurve(900, 180, 900, 180, 0, 180, 0, 180);
		
		BezierCurve tempCurve3 = new BezierCurve(900, 300, 900, 300, 0, 300, 0, 300);

		BezierCurve tempCurve4 = new BezierCurve(900, 420, 900, 420, 0, 420, 0, 420);
		
		BezierCurve tempCurve5 = new BezierCurve(900, 540, 900, 540, 0, 540, 0, 540);
		

		Path hPath1 = new Path();
		hPath1.addCurve(tempCurve1);
		
		Path hPath2 = new Path();
		hPath2.addCurve(tempCurve2);

		Path hPath3 = new Path();
		hPath3.addCurve(tempCurve3);
		
		Path hPath4 = new Path();
		hPath4.addCurve(tempCurve4);
	
		Path hPath5 = new Path();
		hPath5.addCurve(tempCurve5);


		Path[] pathArray = new Path[5];
		pathArray[0] = hPath1;
		pathArray[1] = hPath2;
		pathArray[2] = hPath3; 
		pathArray[3] = hPath4;
		pathArray[4] = hPath5;
		GameMap tempMap = new GameMap("resources/images/pvzBackground.jpg", pathArray, 900, 600);

		IEntity tempSpawn = new Entity(-1000, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn2 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner1 = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);

		IEntity tempSpawn2 = new Entity(-1001, "tempSpawn2", "spawner");
		Spawn spawn3 = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn4 = new Spawn("tempEntity", 1, 1, 2);
		IComponent tempSpawner2 = new SpawnerComponent(Arrays.asList(spawn3, spawn4), 1);
		
		IEntity tempSpawn3 = new Entity(-1002, "tempSpawn3", "spawner");
		Spawn spawn5 = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn6 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner3 = new SpawnerComponent(Arrays.asList(spawn5, spawn6), 2);

		IEntity tempSpawn4 = new Entity(-1003, "tempSpawn4", "spawner");
		Spawn spawn7 = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn8 = new Spawn("tempEntity", 1, 1, 2);
		IComponent tempSpawner4 = new SpawnerComponent(Arrays.asList(spawn7, spawn8), 3);
		
		IEntity tempSpawn5 = new Entity(-1004, "tempSpawn5", "spawner");
		Spawn spawn9 = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn10 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner5 = new SpawnerComponent(Arrays.asList(spawn9, spawn10), 4);



		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner1);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);

		IComponent tempPosition5 = new PositionComponent(0, 100);
		IComponent tempDisplay5 = new DisplayComponent(false);
		IComponent tempSize5 = new SizeComponent();
		tempSpawn2.addComponent(tempSize5);
		tempSpawn2.addComponent(tempSpawner2);
		tempSpawn2.addComponent(tempPosition5);
		tempSpawn2.addComponent(tempDisplay5);
		
		IComponent tempPosition7 = new PositionComponent(0, 100);
		IComponent tempDisplay7 = new DisplayComponent(false);
		IComponent tempSize7 = new SizeComponent();
		tempSpawn3.addComponent(tempSize7);
		tempSpawn3.addComponent(tempSpawner3);
		tempSpawn3.addComponent(tempPosition7);
		tempSpawn3.addComponent(tempDisplay7);
		
		IComponent tempPosition8 = new PositionComponent(0, 100);
		IComponent tempDisplay8 = new DisplayComponent(false);
		IComponent tempSize8 = new SizeComponent();
		tempSpawn4.addComponent(tempSize8);
		tempSpawn4.addComponent(tempSpawner4);
		tempSpawn4.addComponent(tempPosition8);
		tempSpawn4.addComponent(tempDisplay8);
		
		IComponent tempPosition9 = new PositionComponent(0, 100);
		IComponent tempDisplay9 = new DisplayComponent(false);
		IComponent tempSize9 = new SizeComponent();
		tempSpawn5.addComponent(tempSize9);
		tempSpawn5.addComponent(tempSpawner5);
		tempSpawn5.addComponent(tempPosition9);
		tempSpawn5.addComponent(tempDisplay9);

		IEntity tempEntity = new Entity(0, "tempEntity", "Spawns");
		IComponent tempPosition = new PositionComponent(0, 100);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent("resources/images/normalZombie.png");
		IComponent tempSize = new SizeComponent();
		IComponent tempHealth = new HealthComponent(5);
		IComponent pathComp = new PathComponent(0, 0);

		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(tempCollision);
		tempEntity.addComponent(tempHealth);
		tempEntity.addComponent(pathComp);

		ShopItem item = new ShopItem("tempEntity2", "resources/images/Peashooter1.png", 30);
		ShopItem item2 = new ShopItem("SpeedPowerUp", "resources/images/bullet_sprite.png", 30);

		level.setShopItems(Arrays.asList(item, item2));

		IEntity tempEntity2 = new Entity(-5, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempDisplay2 = new DisplayComponent("resources/images/Peashooter1.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		Vector myBulletVector = new Vector(0, 1222);
		IComponent tempDisplay3 = new DisplayComponent("resources/images/cannonball_2.png");
		IComponent tempSize3 = new SizeComponent();

		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);

		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");

		mySimpleBullet.addComponent(tempCollision2);
		mySimpleBullet.addComponent(tempPosition);
		mySimpleBullet.addComponent(new TrackingMovementComponent(20, 0));
		mySimpleBullet.addComponent(tempDisplay3);
		mySimpleBullet.addComponent(tempSize3);
		Map<String, Map<String, IEntity>> myCreatableEntityMap = new HashMap<String, Map<String, IEntity>>();

		Map<String, IEntity> createdSpawns = new HashMap<String, IEntity>();
		createdSpawns.put("tempEntity", tempEntity);

		Map<String, IEntity> createdAmmunition = new HashMap<String, IEntity>();
		createdAmmunition.put("SimpleBullet", mySimpleBullet);

		myCreatableEntityMap.put("Ammunition", createdAmmunition);
		myCreatableEntityMap.put("Spawns", createdSpawns);

		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(tempEntity, mySimpleBullet, tempEntity2));

		ArrayList<String> myTargets = new ArrayList<String>();
		myTargets.add("tempEntity");
		simpleFire.setTargets(myTargets);
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(simpleFire);

		Map<String, IEntity> createdTowers = new HashMap<String, IEntity>();
		createdTowers.put("tempEntity2", tempEntity2);
		myCreatableEntityMap.put("Towers", createdTowers);

		level.addEntityToMap(tempSpawn);
		level.addEntityToMap(tempSpawn2);
		level.addEntityToMap(tempSpawn3);
		level.addEntityToMap(tempSpawn4);
		level.addEntityToMap(tempSpawn5);
		// level.addEntityToMap(tempSpawn2);
		level.addEntityToMap(tempEntity2);
		level.setCurrentWaveIndex(0);
		level.setMap(tempMap);
		level.setNumWaves(2);
		level.setWaveDelayTimer(5);
		level.setAuthoredEntities(authoredEntities);

		return level;
	}
	
	private Level getLevel2() {
		Level level = new Level("level1");
		level.setIndex(1);
		EntityAction action = new EntityAction("tempEntity", "Display", "Delete", "true");
		EntityAction action4 = new EntityAction("tempEntity", "Display", "CanBeShown", "false");
		EntityAction action2 = new EntityAction("tempEntity", "Health", "Health", "-5");
		EntityAction action3 = new EntityAction("SimpleBullet", "Display", "CanBeShown", "false");
		EntityAction action5 = new EntityAction("SimpleBullet", "Display", "Delete", "true");

		EntityAction keyActionLeft = new EntityAction("tempEntity2", "Position", "XCoordinate", "-5");
		EntityAction keyActionRight = new EntityAction("tempEntity2", "Position", "XCoordinate", "5");
		EntityAction keyActionDown = new EntityAction("tempEntity2", "Position", "YCoordinate", "5");
		EntityAction keyActionUp = new EntityAction("tempEntity2", "Position", "YCoordinate", "-5");

		Rule ruleKeyLeft = new Rule();
		ruleKeyLeft.addActions(keyActionLeft);
		ruleKeyLeft.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEvent-LEFT"));
		Rule ruleKeyRight = new Rule();
		ruleKeyRight.addActions(keyActionRight);
		ruleKeyRight.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEvent-RIGHT"));
		Rule ruleKeyUp = new Rule();
		ruleKeyUp.addActions(keyActionUp);
		ruleKeyUp.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-UP"));
		Rule ruleKeyDown = new Rule();
		ruleKeyDown.addActions(keyActionDown);
		ruleKeyDown.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-DOWN"));

		LevelAction levelAction = new LevelAction("CurrentNumLives", "-1");
		LevelAction levelAction2 = new LevelAction("CurrentResources", "4");

		List<EntityAction> myActions = new ArrayList<EntityAction>();
		myActions.add(action);
		List<String> myEvents = new ArrayList<String>();
		myEvents.add("SimpleBullet-tempEntity-CollisionEvent");
		myEvents.add("tempEntity-DeathEvent");

		Rule rule1 = new Rule();
		rule1.addActions(Arrays.asList(action2, action3, action5));
		rule1.addEvents(Arrays.asList("SimpleBullet-tempEntity-CollisionEvent"));

		Rule rule2 = new Rule();
		rule2.addActions(Arrays.asList(action, action4));
		rule2.addEvents(Arrays.asList("tempEntity-DeathEvent"));

		Rule rule3 = new Rule();
		EntityAction shootAction = new EntityAction("tempEntity2", "Firing", "FireNow", "true");
		rule3.addActions(Arrays.asList(shootAction));
		rule3.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-S"));

		Rule rule4 = new Rule();
		rule4.addActions(Arrays.asList(action3, action5));
		rule4.addEvents(Arrays.asList("SimpleBullet-OutOfMapEvent"));

		Rule rule5 = new Rule();
		rule5.addActions(Arrays.asList(levelAction, action, action4, levelAction2));
		rule5.addEvents(Arrays.asList("tempEntity-EndOfPathEvent"));

		level.setRuleAgenda(
				Arrays.asList(rule1, rule2, rule3, rule4, rule5, ruleKeyUp, ruleKeyDown, ruleKeyLeft, ruleKeyRight));
		// level.addActionToEventMap(Arrays.asList("SimpleBullettempEntityCollisionEvent"),
		// myActions);
		
		//world is going to be 900 x 600
		
		
		
		BezierCurve tempCurve1 = new BezierCurve(900, 60, 900, 60, 0, 60, 0, 60);
		BezierCurve delay1 = new BezierCurve(0, 60, 0, 60, 0, 60, 0, 60);
		
		BezierCurve tempCurve2 = new BezierCurve(900, 180, 900, 180, 0, 180, 0, 180);
		
		BezierCurve tempCurve3 = new BezierCurve(900, 300, 900, 300, 0, 300, 0, 300);

		BezierCurve tempCurve4 = new BezierCurve(900, 420, 900, 420, 0, 420, 0, 420);
		
		BezierCurve tempCurve5 = new BezierCurve(900, 540, 900, 540, 0, 540, 0, 540);
		

		Path hPath1 = new Path();
		hPath1.addCurve(tempCurve1);
		
		Path hPath2 = new Path();
		hPath2.addCurve(tempCurve2);

		Path hPath3 = new Path();
		hPath3.addCurve(tempCurve3);
		
		Path hPath4 = new Path();
		hPath4.addCurve(tempCurve4);
	
		Path hPath5 = new Path();
		hPath5.addCurve(tempCurve5);


		Path[] pathArray = new Path[5];
		pathArray[0] = hPath1;
		pathArray[1] = hPath2;
		pathArray[2] = hPath3; 
		pathArray[3] = hPath4;
		pathArray[4] = hPath5;
		GameMap tempMap = new GameMap("resources/images/pvzBackground.jpg", pathArray, 900, 600);

		IEntity tempSpawn = new Entity(-1000, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn2 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner1 = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);

		IEntity tempSpawn2 = new Entity(-1001, "tempSpawn2", "spawner");
		Spawn spawn3 = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn4 = new Spawn("tempEntity", 1, 1, 2);
		IComponent tempSpawner2 = new SpawnerComponent(Arrays.asList(spawn3, spawn4), 1);
		
		IEntity tempSpawn3 = new Entity(-1002, "tempSpawn3", "spawner");
		Spawn spawn5 = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn6 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner3 = new SpawnerComponent(Arrays.asList(spawn5, spawn6), 2);

		IEntity tempSpawn4 = new Entity(-1003, "tempSpawn4", "spawner");
		Spawn spawn7 = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn8 = new Spawn("tempEntity", 1, 1, 2);
		IComponent tempSpawner4 = new SpawnerComponent(Arrays.asList(spawn7, spawn8), 3);
		
		IEntity tempSpawn5 = new Entity(-1004, "tempSpawn5", "spawner");
		Spawn spawn9 = new Spawn("tempEntity", 1, 0, 6);
		Spawn spawn10 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner5 = new SpawnerComponent(Arrays.asList(spawn9, spawn10), 4);



		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner1);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);

		IComponent tempPosition5 = new PositionComponent(0, 100);
		IComponent tempDisplay5 = new DisplayComponent(false);
		IComponent tempSize5 = new SizeComponent();
		tempSpawn2.addComponent(tempSize5);
		tempSpawn2.addComponent(tempSpawner2);
		tempSpawn2.addComponent(tempPosition5);
		tempSpawn2.addComponent(tempDisplay5);
		
		IComponent tempPosition7 = new PositionComponent(0, 100);
		IComponent tempDisplay7 = new DisplayComponent(false);
		IComponent tempSize7 = new SizeComponent();
		tempSpawn3.addComponent(tempSize7);
		tempSpawn3.addComponent(tempSpawner3);
		tempSpawn3.addComponent(tempPosition7);
		tempSpawn3.addComponent(tempDisplay7);
		
		IComponent tempPosition8 = new PositionComponent(0, 100);
		IComponent tempDisplay8 = new DisplayComponent(false);
		IComponent tempSize8 = new SizeComponent();
		tempSpawn4.addComponent(tempSize8);
		tempSpawn4.addComponent(tempSpawner4);
		tempSpawn4.addComponent(tempPosition8);
		tempSpawn4.addComponent(tempDisplay8);
		
		IComponent tempPosition9 = new PositionComponent(0, 100);
		IComponent tempDisplay9 = new DisplayComponent(false);
		IComponent tempSize9 = new SizeComponent();
		tempSpawn5.addComponent(tempSize9);
		tempSpawn5.addComponent(tempSpawner5);
		tempSpawn5.addComponent(tempPosition9);
		tempSpawn5.addComponent(tempDisplay9);

		IEntity tempEntity = new Entity(0, "tempEntity", "Spawns");
		IComponent tempPosition = new PositionComponent(0, 100);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent("resources/images/bucketZombie.png");
		IComponent tempSize = new SizeComponent();
		IComponent tempHealth = new HealthComponent(10);
		IComponent pathComp = new PathComponent(0, 0);

		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(tempCollision);
		tempEntity.addComponent(tempHealth);
		tempEntity.addComponent(pathComp);

		ShopItem item = new ShopItem("tempEntity2", "resources/images/Peashooter1.png", 30);
		ShopItem item2 = new ShopItem("SpeedPowerUp", "resources/images/bullet_sprite.png", 30);

		level.setShopItems(Arrays.asList(item, item2));

		IEntity tempEntity2 = new Entity(-5, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempDisplay2 = new DisplayComponent("resources/images/Peashooter1.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		Vector myBulletVector = new Vector(0, 1222);
		IComponent tempDisplay3 = new DisplayComponent("resources/images/cannonball_2.png");
		IComponent tempSize3 = new SizeComponent();

		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);

		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");

		mySimpleBullet.addComponent(tempCollision2);
		mySimpleBullet.addComponent(tempPosition);
		mySimpleBullet.addComponent(new TrackingMovementComponent(20, 0));
		mySimpleBullet.addComponent(tempDisplay3);
		mySimpleBullet.addComponent(tempSize3);
		Map<String, Map<String, IEntity>> myCreatableEntityMap = new HashMap<String, Map<String, IEntity>>();

		Map<String, IEntity> createdSpawns = new HashMap<String, IEntity>();
		createdSpawns.put("tempEntity", tempEntity);

		Map<String, IEntity> createdAmmunition = new HashMap<String, IEntity>();
		createdAmmunition.put("SimpleBullet", mySimpleBullet);

		myCreatableEntityMap.put("Ammunition", createdAmmunition);
		myCreatableEntityMap.put("Spawns", createdSpawns);

		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(tempEntity, mySimpleBullet, tempEntity2));

		ArrayList<String> myTargets = new ArrayList<String>();
		myTargets.add("tempEntity");
		simpleFire.setTargets(myTargets);
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(simpleFire);

		Map<String, IEntity> createdTowers = new HashMap<String, IEntity>();
		createdTowers.put("tempEntity2", tempEntity2);
		myCreatableEntityMap.put("Towers", createdTowers);

		level.addEntityToMap(tempSpawn);
		level.addEntityToMap(tempSpawn2);
		level.addEntityToMap(tempSpawn3);
		level.addEntityToMap(tempSpawn4);
		level.addEntityToMap(tempSpawn5);
		// level.addEntityToMap(tempSpawn2);
		level.addEntityToMap(tempEntity2);
		level.setCurrentWaveIndex(0);
		level.setMap(tempMap);
		level.setNumWaves(2);
		level.setWaveDelayTimer(5);
		level.setAuthoredEntities(authoredEntities);

		return level;
	}



}

