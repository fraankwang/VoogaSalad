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

public class DuffyGame implements ITestingGame {

	public GameWorld initGame() {
		GameWorld duffy = new GameWorld();
		Mode mode = new Mode("Duffy");
		Level level = getFirstLevel();
		mode.addLevel(level);
		GameStatistics gameStats = new GameStatistics(50, 50);
		mode.setGameStatistics(gameStats);
		duffy.addMode(mode);

		return duffy;
	}
	
	private Level getFirstLevel(){
		Level firstLevel  = new Level("level1");
		firstLevel.setIndex(0);
		EntityAction deleteBloon = new EntityAction("tempEntity", "Display", "Delete", "true");
		EntityAction hideBloon = new EntityAction("tempEntity", "Display", "CanBeShown", "false");
		EntityAction depleteBloon = new EntityAction("tempEntity", "Health", "Health", "-5");
		
		EntityAction showBullet = new EntityAction("SimpleBullet", "Display", "CanBeShown", "false");
		EntityAction hideBullet = new EntityAction("SimpleBullet", "Display", "Delete", "true");
		EntityAction keyActionLeft = new EntityAction("tempEntity2", "Position", "XCoordinate", "-5");
		EntityAction keyActionRight = new EntityAction("tempEntity2", "Position", "XCoordinate", "5");
		EntityAction keyActionDown = new EntityAction("tempEntity2", "Position", "YCoordinate", "5");
		EntityAction keyActionUp = new EntityAction("tempEntity2", "Position", "YCoordinate", "-5");
		/**
		 * This is the rules to move entities around
		 */
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
		/**
		 * Level actions to decrement the lives and add current resources
		 */
		LevelAction levelDeathAction = new LevelAction("CurrentNumLives", "-1");
		LevelAction levelWinAction = new LevelAction("CurrentResources", "4");
		
		List<EntityAction> myActions = new ArrayList<EntityAction>();
		myActions.add(deleteBloon);
		List<String> myEvents = new ArrayList<String>();
		myEvents.add("SimpleBullet-tempEntity-CollisionEvent");
		myEvents.add("tempEntity-DeathEvent");
		
		Rule handleCollision = new Rule();
		handleCollision.addActions(Arrays.asList(depleteBloon, showBullet, hideBullet));
		handleCollision.addEvents(Arrays.asList("SimpleBullet-tempEntity-CollisionEvent"));

		Rule bloonDeath = new Rule();
		bloonDeath.addActions(Arrays.asList(deleteBloon, hideBloon));
		bloonDeath.addEvents(Arrays.asList("tempEntity-DeathEvent"));

		Rule shootRule = new Rule();
		EntityAction shootAction = new EntityAction("tempEntity2", "Firing", "FireNow", "true");
		shootRule.addActions(Arrays.asList(shootAction));
		shootRule.addEvents(Arrays.asList("tempEntity2-KeyPressedEntityEvent-S"));

		Rule bulletMapCleanUp = new Rule();
		bulletMapCleanUp.addActions(Arrays.asList(showBullet, hideBullet));
		bulletMapCleanUp.addEvents(Arrays.asList("SimpleBullet-OutOfMapEvent"));

		Rule bloonAtEndOfPath = new Rule();
		bloonAtEndOfPath.addActions(Arrays.asList(levelDeathAction, deleteBloon, hideBloon, levelWinAction));
		bloonAtEndOfPath.addEvents(Arrays.asList("tempEntity-EndOfPathEvent"));
		
		firstLevel.setRuleAgenda(
				Arrays.asList(handleCollision, bloonDeath, shootRule, bulletMapCleanUp, bloonAtEndOfPath));

		GameMap level1MapAndPath = makeGameMap();
		IEntity tempSpawn = new Entity(40, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn2 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);
		setSpawnComponents(tempSpawn, tempSpawner);
		IComponent tempSpawner1 = new SpawnerComponent(Arrays.asList(spawn, new Spawn("tempEntity", 1, 1, 2)), 0);
		IEntity tempSpawn2 = new Entity(-40, "tempSpawn2", "spawner");
		setSpawnComponents(tempSpawn2, tempSpawner1);
		
		IEntity bloonEntity = new Entity(0, "tempEntity", "Spawns");
		setBloonComponents(bloonEntity);
		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");
		setBulletComponents(mySimpleBullet);
		double tower1X = (150.0 + 327.0)/2;
		double tower1Y = (320.0 + 158.0)/2;
		IEntity tempEntity2 = new Entity(-5, "tempEntity2", "object2");
		IComponent tempPosition2 = new PositionComponent(tower1X, tower1Y);
		IComponent tempDisplay2 = new DisplayComponent("resources/images/DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		IComponent tempCollision2 = new CollisionComponent();
		Vector myBulletVector = new Vector(0, 1222);
		IComponent tempDisplay3 = new DisplayComponent("resources/images/bullet_sprite.png");
		IComponent tempSize3 = new SizeComponent();
		
		Map<String, Map<String, IEntity>> myCreatableEntityMap = new HashMap<String, Map<String, IEntity>>();
		Map<String, IEntity> createdSpawns = new HashMap<String, IEntity>();
		createdSpawns.put("tempEntity", bloonEntity);

		Map<String, IEntity> createdAmmunition = new HashMap<String, IEntity>();
		createdAmmunition.put("SimpleBullet", mySimpleBullet);
		ShopItem item = new ShopItem("tempEntity2", "resources/images/DrumpfVader.png", 30);
		ShopItem item2 = new ShopItem("SpeedPowerUp", "resources/images/bullet_sprite.png", 30);

		firstLevel.setShopItems(Arrays.asList(item, item2));
		myCreatableEntityMap.put("Ammunition", createdAmmunition);
		myCreatableEntityMap.put("Spawns", createdSpawns);
		
		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(bloonEntity, mySimpleBullet, tempEntity2));

		ArrayList<String> myTargets = new ArrayList<String>();
		myTargets.add("tempEntity");
		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);
		simpleFire.setTargets(myTargets);
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(simpleFire);
		
		IEntity tempEntity3 = new Entity(-5, "tempEntity3", "object3");
		double tower2X = (327.0 + 566.0)/2;
		double tower2Y = 274.0;
		IComponent tempPosition3 = new PositionComponent(tower2X, tower2Y);tempEntity2.addComponent(tempDisplay2);
		tempEntity3.addComponent(tempDisplay2);
		tempEntity3.addComponent(tempSize2);
		tempEntity3.addComponent(tempPosition3);
		tempEntity3.addComponent(simpleFire);
		Map<String, IEntity> createdTowers = new HashMap<String, IEntity>();
		createdTowers.put("tempEntity2", tempEntity2);
		createdTowers.put("tempEntity3", tempEntity3);
		myCreatableEntityMap.put("Towers", createdTowers);

		firstLevel.addEntityToMap(tempSpawn);
		// level.addEntityToMap(tempSpawn2);
		firstLevel.addEntityToMap(tempEntity2);
		firstLevel.setCurrentWaveIndex(0);
		firstLevel.setMap(level1MapAndPath);
		firstLevel.setNumWaves(2);
		firstLevel.setWaveDelayTimer(5);
		firstLevel.setAuthoredEntities(authoredEntities);

		
		return firstLevel;
	}
	
	private void setSpawnComponents(IEntity tempSpawn, IComponent tempSpawner){
		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);
	}
	
	private void setBloonComponents(IEntity tempEntity){
		double tower1X = (150.0 + 327.0)/2;
		double tower1Y = (320.0 + 158.0)/2;
		IComponent tempPosition = new PositionComponent(tower1X, tower1Y);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent("resources/images/DrumpfVader.png");
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
	}
	
	private void setBulletComponents(IEntity mySimpleBullet){
		double tower1X = (150.0 + 327.0)/2;
		double tower1Y = (320.0 + 158.0)/2;
		IComponent tempPosition = new PositionComponent(tower1X, tower1Y);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempSize3 = new SizeComponent();
		IComponent tempDisplay3 = new DisplayComponent("resources/images/bullet_sprite.png");
		mySimpleBullet.addComponent(tempCollision);
		mySimpleBullet.addComponent(tempPosition);
		mySimpleBullet.addComponent(new MovementComponent(10, 0));
		mySimpleBullet.addComponent(tempDisplay3);
		mySimpleBullet.addComponent(tempSize3);
	}
	
	private GameMap makeGameMap(){
		Path tempPath = new Path();
		BezierCurve curve1 = new BezierCurve(28.0,323.0, 28.0,323.0,147.0,329.0,147.0,329.0); 
		BezierCurve curve2 =  new BezierCurve(147.0,329.0,150.0,158.0,147.0,329.0,150.0,158.0 );
		BezierCurve curve3 = new BezierCurve(150.0,158.0,150.0,158.0,327.0,157.0,327.0,157.0 );
		BezierCurve curve4 = new BezierCurve( 327.0,157.0,327.0,157.0,327.0,383.0,327.0,383.0 );
		BezierCurve curve5 = new BezierCurve(327.0,383.0,327.0,383.0,566.0,384.0,566.0,384.0 );
		BezierCurve curve6 = new BezierCurve(566.0,384.0,566.0,384.0,572.0,274.0,572.0,274.0 );
		BezierCurve curve7 = new BezierCurve(572.0,274.0,572.0,274.0,881.0,270.0,881.0,270.0);
		//BezierCurve curve8 new BezierCurve(100.0-100.0,150.0-50.0,250.0-150.0,300.0-100.0
		double tower1X = (150.0 + 327.0)/2;
		double tower1Y = (320.0 + 158.0)/2;
		double tower2X = (327.0 + 566.0)/2;
		double tower2Y = 274.0;
		/*BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		Path tempPath1 = new Path();
		BezierCurve tempCurve4 = new BezierCurve(600, 4, 0, 0, 0, 0, 2, 100);
		BezierCurve tempCurve5 = new BezierCurve(250, 200, 50, 50, 250, 450, 0, 200);
		BezierCurve tempCurve6 = new BezierCurve(0, 300, 950, 50, 250, 250, 200, 400);
		tempPath1.addCurve(tempCurve4);
		tempPath1.addCurve(tempCurve6);
		tempPath1.addCurve(tempCurve5);*/
		tempPath.addCurve(curve1);
		tempPath.addCurve(curve2);
		tempPath.addCurve(curve3);
		tempPath.addCurve(curve4);
		tempPath.addCurve(curve5);
		tempPath.addCurve(curve6);
		tempPath.addCurve(curve7);

		Path[] pathArray = new Path[2];
		pathArray[0] = tempPath;
		GameMap tempMap = new GameMap("resources/images/tower_defense_map_1.png", pathArray, 900, 600);
		return tempMap;
	}

}