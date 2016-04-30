package engine.controller;

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
import engine.backend.components.Vector;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.game_features.GameShop;
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

public class testingClass {

	public GameWorld initTestGame(GameWorld myGameWorld) {
		GameWorld collisionTest = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		GameMap tempMap = new GameMap("Park_Path.png", tempPath, 600, 400);

		IEntity tempEntity = new Entity(0, "tempEntity", "object");
		IComponent tempPosition = new PositionComponent(0, 60);
		IComponent tempMovement = new MovementComponent(2, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent pathComp = new PathComponent(0, 0);
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize = new SizeComponent();
		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(tempCollision);
		// tempEntity.addComponent(pathComp);

		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempMovement2 = new MovementComponent(-4, 0);
		// IComponent pathComp2 = new PathComponent(0, 0);
		IComponent tempDisplay2 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(tempMovement2);
		tempEntity.addComponent(tempCollision2);
		// tempEntity2.addComponent(pathComp2);

		IEntity tempEntity3 = new Entity(2, "tempEntity3", "object3");
		IComponent tempPosition3 = new PositionComponent(450, 450);
		IComponent tempDisplay3 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize3 = new SizeComponent();
		tempEntity3.addComponent(tempDisplay3);
		tempEntity3.addComponent(tempSize3);
		tempEntity3.addComponent(tempPosition3);
		//
		// tempLevel.addToEntities(tempEntity);
		// tempLevel.addToEntities(tempEntity2);
		// tempLevel.addToEntities(tempEntity3);
		// tempLevel.setMap(tempMap);
		// tempMode.addLevel(tempLevel);
		collisionTest.addMode(tempMode);
		return collisionTest;
	}

	public void testExceptions() {
		DrumpfTowerException exc = new DrumpfTowerException("Throwing an example exception");
	}

	public GameWorld testFiring() {
		GameWorld firingTest = new GameWorld();
		Mode mode = new Mode("test firing");
		Level level = new Level("blah");
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
		ruleKeyLeft.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEventLEFT"));
		Rule ruleKeyRight = new Rule();
		ruleKeyRight.addActions(keyActionRight);
		ruleKeyRight.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEventRIGHT"));
		Rule ruleKeyUp = new Rule();
		ruleKeyUp.addActions(keyActionUp);
		ruleKeyUp.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEventUP"));
		Rule ruleKeyDown = new Rule();
		ruleKeyDown.addActions(keyActionDown);
		ruleKeyDown.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEventDOWN"));

		LevelAction levelAction = new LevelAction("CurrentNumLives", "-1");
		LevelAction levelAction2 = new LevelAction("CurrentResources", "4");

		List<EntityAction> myActions = new ArrayList<EntityAction>();
		myActions.add(action);
		List<String> myEvents = new ArrayList<String>();
		myEvents.add("SimpleBullettempEntityCollisionEvent");
		myEvents.add("tempEntityDeathEvent");

		Rule rule1 = new Rule();
		rule1.addActions(Arrays.asList(action2, action3, action5));
		rule1.addEvents(Arrays.asList("SimpleBullettempEntityCollisionEvent"));

		Rule rule2 = new Rule();
		rule2.addActions(Arrays.asList(action, action4));
		rule2.addEvents(Arrays.asList("tempEntityDeathEvent"));

		Rule rule3 = new Rule();
		EntityAction shootAction = new EntityAction("tempEntity2", "Firing", "FireNow", "true");
		rule3.addActions(Arrays.asList(shootAction));
		rule3.addEvents(Arrays.asList("tempEntity2KeyPressedEntityEventS"));

		Rule rule4 = new Rule();
		rule4.addActions(Arrays.asList(action3, action5));
		rule4.addEvents(Arrays.asList("SimpleBulletOutOfMapEvent"));

		Rule rule5 = new Rule();
		rule5.addActions(Arrays.asList(levelAction, action, action4, levelAction2));
		rule5.addEvents(Arrays.asList("tempEntityEndOfPathEvent"));

		level.setRuleAgenda(
				Arrays.asList(rule1, rule2, rule3, rule4, rule5, ruleKeyUp, ruleKeyDown, ruleKeyLeft, ruleKeyRight));
		// level.addActionToEventMap(Arrays.asList("SimpleBullettempEntityCollisionEvent"),
		// myActions);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		Path[] pathArray = new Path[1];
		pathArray[0] = tempPath;

		GameMap tempMap = new GameMap("Park_Path.png", pathArray, 900, 600);

		IEntity tempSpawn = new Entity(40, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("tempEntity", 1, 0, 10);
		Spawn spawn2 = new Spawn("tempEntity", 1, 1, 10);
		IComponent tempSpawner = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);

		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);

		IEntity tempEntity = new Entity(0, "tempEntity", "Spawns");
		IComponent tempPosition = new PositionComponent(0, 100);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
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

		ShopItem item = new ShopItem("tempEntity2", "DrumpfVader.png", 30);
		GameShop shop = new GameShop();
		shop.addItem("tempEntity2", "DrumpfVader.png", 30);

		level.setShopItems(Arrays.asList(item));

		IEntity tempEntity2 = new Entity(-5, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempDisplay2 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		Vector myBulletVector = new Vector(0, 1222);
		IComponent tempDisplay3 = new DisplayComponent("bullet_sprite.png");
		IComponent tempSize3 = new SizeComponent();

		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);

		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");

		mySimpleBullet.addComponent(tempCollision2);
		mySimpleBullet.addComponent(tempPosition);
		mySimpleBullet.addComponent(new MovementComponent(10, 0));
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
		// firingTest.setAuthoredEntities(authoredEntities);
		// firingTest.setEntityMap(myCreatableEntityMap);
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
		System.out.println(level.getEntities().values().size());
		level.addEntityToMap(tempEntity2);

		level.setCurrentWaveIndex(0);
		level.setMap(tempMap);
		level.setCurrentWaveIndex(0);
		level.setNumWaves(2);
		level.setWaveDelayTimer(5);
		level.setAuthoredEntities(authoredEntities);
		mode.addLevel(level);
		firingTest.addMode(mode);

		GameStatistics gameStats = new GameStatistics(50, 50);
		firingTest.setGameStatistics(gameStats);

		return firingTest;
	}

	public GameWorld testCollision() {
		GameWorld collisionTest = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		// Level tempLevel = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		Path[] pathArray = new Path[1];
		pathArray[0] = tempPath;

		GameMap tempMap = new GameMap("", pathArray, 200, 200);

		IEntity tempEntity = new Entity(0, "tempEntity", "object");
		IComponent tempPosition = new PositionComponent(0, 60);
		IComponent tempMovement = new MovementComponent(2, 0);
		IComponent tempCollision = new CollisionComponent();
		// IComponent pathComp = new PathComponent(0, 0);
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize = new SizeComponent();
		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(tempCollision);
		// tempEntity.addComponent(pathComp);

		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempMovement2 = new MovementComponent(-4, 0);
		// IComponent pathComp2 = new PathComponent(0, 0);
		IComponent tempDisplay2 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(tempMovement2);
		tempEntity.addComponent(tempCollision2);
		// tempEntity2.addComponent(pathComp2);

		IEntity tempEntity3 = new Entity(2, "tempEntity3", "object3");
		IComponent tempPosition3 = new PositionComponent(450, 450);
		IComponent tempDisplay3 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize3 = new SizeComponent();
		tempEntity3.addComponent(tempDisplay3);
		tempEntity3.addComponent(tempSize3);
		tempEntity3.addComponent(tempPosition3);

		// tempLevel.addToEntities(tempEntity);
		// tempLevel.addToEntities(tempEntity2);
		// tempLevel.addToEntities(tempEntity3);
		// tempLevel.addEntityToMap(tempEntity);
		// tempLevel.addEntityToMap(tempEntity2);
		// tempLevel.setMap(tempMap);
		// tempMode.addLevel(tempLevel);
		collisionTest.addMode(tempMode);
		return collisionTest;
	}

}
