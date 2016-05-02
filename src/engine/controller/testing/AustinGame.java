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
import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class AustinGame implements ITestingGame{
	
	private static final String IMAGE_DIR = "resources/images/";

	public GameWorld initGame() {
		GameWorld austin = new GameWorld();
		Mode mode = new Mode("Duffy");
		Level level = getLevel();
		mode.addLevel(level);
		GameStatistics gameStats = new GameStatistics(50, 50);
		mode.setGameStatistics(gameStats);
		austin.addMode(mode);

		return austin;
	}

	private Level getLevel() {
		Level level = new Level("level1");
		level.setIndex(0);
		createRules(level);
		createSpawns(level);

		IEntity towerEntity = new Entity(0, "SuperTower", "superType");
		IComponent tempPosition = new PositionComponent(0, 0);
		IComponent tempMovement = new MovementComponent(6, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent(IMAGE_DIR + "Beekeeper_Icon.png");
		IComponent tempSize = new SizeComponent();
		IComponent tempHealth = new HealthComponent(5);
		IComponent pathComp = new PathComponent(0, 0);
		
		Vector myBulletVector = new Vector(0, 1222);
		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);
		
		towerEntity.addComponent(tempDisplay);
		towerEntity.addComponent(tempSize);
		towerEntity.addComponent(tempPosition);
		towerEntity.addComponent(tempMovement);
		towerEntity.addComponent(tempCollision);
		towerEntity.addComponent(tempHealth);
		towerEntity.addComponent(pathComp);
		towerEntity.addComponent(simpleFire);

		ShopItem item = new ShopItem("SuperTower", IMAGE_DIR + "Beekeeper_Icon.png", 30);
		ShopItem item2 = new ShopItem("SpeedPowerUp", IMAGE_DIR + "red-ball.png", 30);
		level.setShopItems(Arrays.asList(item, item2));
		
		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");
		IComponent bulletDisplay = new DisplayComponent(IMAGE_DIR + "red-ball.png");
		IComponent bulletSize = new SizeComponent();
		mySimpleBullet.addComponent(new CollisionComponent());
		mySimpleBullet.addComponent(tempPosition);
		mySimpleBullet.addComponent(new MovementComponent(10, 0));
		mySimpleBullet.addComponent(bulletDisplay);
		mySimpleBullet.addComponent(bulletSize);
		
		ArrayList<String> myTargets = new ArrayList<String>();
		myTargets.add("tempEntity");
		simpleFire.setTargets(myTargets);
		
		createMap(level);
		
		level.addEntityToMap(towerEntity);
		level.setCurrentWaveIndex(0);
		level.setNumWaves(2);
		level.setWaveDelayTimer(5);
		
		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(towerEntity, mySimpleBullet));
		level.setAuthoredEntities(authoredEntities);
		return level;
	}
	
	private void createRules(Level level){
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

		level.setRuleAgenda(Arrays.asList(rule1, rule2, rule3, rule4, rule5, ruleKeyUp, ruleKeyDown, ruleKeyLeft, ruleKeyRight));
	}
	
	private void createMap(Level level){
		Path tempPath = new Path();
		BezierCurve tempCurve = new BezierCurve(0, 0, 0, 0, 450, 450, 450, 450);
		tempPath.addCurve(tempCurve);

		Path[] pathArray = new Path[2];
		pathArray[0] = tempPath;
	
		GameMap map = new GameMap(IMAGE_DIR + "Spider_Map.png", pathArray, 700, 500);
		level.setMap(map);
	}
	
	private void createSpawns(Level level){
		IEntity tempSpawn = new Entity(40, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("tempEntity", 1, 0, 2);
		Spawn spawn2 = new Spawn("tempEntity", 1, 1, 4);
		IComponent tempSpawner = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);

		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);
		level.addEntityToMap(tempSpawn);
	}
}

