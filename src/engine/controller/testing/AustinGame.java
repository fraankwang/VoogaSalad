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

public class AustinGame implements ITestingGame {

	public GameWorld initGame() {
		GameWorld galaga = new GameWorld();
		Mode mode = new Mode("Stretch");
		Level level = getLevel1();
		mode.addLevel(level);
		GameStatistics gameStats = new GameStatistics(5, 100);
		mode.setGameStatistics(gameStats);

		galaga.addMode(mode);

		return galaga;
	}

	private Level getLevel1() {
		Level level = new Level("GalagaLevel1");
		level.setIndex(0);

		// build superTower
		IEntity superTower = new Entity(666, "superTower", "tower");
		superTower.addComponent(new PositionComponent());
		superTower.addComponent(new SizeComponent(50, 50));
		superTower.addComponent(new DisplayComponent("resources/images/Beekeeper_Icon.png"));
		FiringComponent towerFiring = new FiringComponent("towerBullet", 10, 4, 1000, new Vector(0, 0), 1);
		towerFiring.setTargets(Arrays.asList("enemy1", "enemy2"));
		towerFiring.setTimer(100);
		superTower.addComponent(towerFiring);

		IEntity towerBullet = new Entity(123, "towerBullet", "bullet");
		towerBullet.addComponent(new PositionComponent());
		towerBullet.addComponent(new MovementComponent());
		towerBullet.addComponent(new SizeComponent(20, 20));
		towerBullet.addComponent(new DisplayComponent("resources/images/Beekeeper_Icon.png"));
		towerBullet.addComponent(new CollisionComponent());
		FiringComponent bulletFiring = new FiringComponent("towerBullet", 10, 4, 300, new Vector(0, 0), 1, 50);
		bulletFiring.setTargets(Arrays.asList("enemy1", "enemy2"));
		towerBullet.addComponent(bulletFiring);

		ShopItem superTowerItem = new ShopItem("superTower", "resources/images/Beekeeper_Icon.png", 15);
		level.setShopItems(Arrays.asList(superTowerItem));

		// towerBullet actions
		EntityAction towerBulletDissapearAction = new EntityAction("towerBullet", "Display", "CanBeShown", "false");
		EntityAction towerBulletDeleteAction = new EntityAction("towerBullet", "Display", "Delete", "true");
		
		// build Enemy1
		IEntity enemy1 = new Entity(44, "enemy1", "Enemies");

		IComponent enemy1Position = new PositionComponent(0, 100);

		MovementComponent enemy1Movement = new MovementComponent(2, 0);
		enemy1Movement.setTheta(0);
		enemy1Movement.setCanMove(true);
		enemy1Movement.setCanRotate(false);

		IComponent enemy1Collision = new CollisionComponent();
		IComponent enemy1Display = new DisplayComponent("resources/images/Bug1T.png");
		IComponent enemy1Size = new SizeComponent(40, 40);
		IComponent enemy1Health = new HealthComponent(1);
		IComponent enemy1Path = new PathComponent(0, 0);

		enemy1.addComponent(enemy1Display);
		enemy1.addComponent(enemy1Size);
		enemy1.addComponent(enemy1Position);
		enemy1.addComponent(enemy1Movement);
		enemy1.addComponent(enemy1Collision);
		enemy1.addComponent(enemy1Health);
		enemy1.addComponent(enemy1Path);

		// enemy1 actions
		EntityAction enemy1DeathAction = new EntityAction("enemy1", "Display", "CanBeShown", "false");
		EntityAction enemy1DeathAction1 = new EntityAction("enemy1", "Display", "Delete", "true");
		LevelAction enemy1KillAction = new LevelAction("CurrentResources", "10");

		// build Enemy2
		IEntity enemy2 = new Entity(1234, "enemy2", "Enemies");

		IComponent enemy2Position = new PositionComponent(0, 100);

		MovementComponent enemy2Movement = new MovementComponent(2, 0);
		enemy2Movement.setTheta(0);
		enemy2Movement.setCanMove(true);
		enemy2Movement.setCanRotate(false);

		IComponent enemy2Collision = new CollisionComponent();
		IComponent enemy2Display = new DisplayComponent("resources/images/Bug2T.png");
		IComponent enemy2Size = new SizeComponent(40, 40);
		IComponent enemy2Health = new HealthComponent(1);
		IComponent enemy2Path = new PathComponent(0, 0);

		enemy2.addComponent(enemy2Display);
		enemy2.addComponent(enemy2Size);
		enemy2.addComponent(enemy2Position);
		enemy2.addComponent(enemy2Movement);
		enemy2.addComponent(enemy2Collision);
		enemy2.addComponent(enemy2Health);
		enemy2.addComponent(enemy2Path);

		// enemy2 actions
		EntityAction enemy2DeathAction = new EntityAction("enemy2", "Display", "CanBeShown", "false");
		EntityAction enemy2DeathAction1 = new EntityAction("enemy2", "Display", "Delete", "true");
		LevelAction enemy2KillAction = new LevelAction("CurrentResources", "10");

		// rules
		Rule towerBulletOffMapRule = new Rule();
		towerBulletOffMapRule.addActions(Arrays.asList(towerBulletDissapearAction, towerBulletDeleteAction));
		towerBulletOffMapRule.addEvents(Arrays.asList("towerBullet-OutOfMapEvent"));

		Rule enemy1LeaveScreenRule = new Rule();
		enemy1LeaveScreenRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1));
		enemy1LeaveScreenRule.addEvents(Arrays.asList("enemy1-EndOfPathEvent"));

		Rule enemy1DeathRule = new Rule();
		enemy1DeathRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1, enemy1KillAction));
		enemy1DeathRule.addEvents(Arrays.asList("enemy1-DeathEvent"));

		Rule enemy2LeaveScreenRule = new Rule();
		enemy2LeaveScreenRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1));
		enemy2LeaveScreenRule.addEvents(Arrays.asList("enemy2-EndOfPathEvent"));

		Rule enemy2DeathRule = new Rule();
		enemy2DeathRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1, enemy2KillAction));
		enemy2DeathRule.addEvents(Arrays.asList("enemy2-DeathEvent"));

		Rule enemy1CollisionRule = new Rule();
		enemy1CollisionRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1, enemy1KillAction, towerBulletDissapearAction, towerBulletDeleteAction));
		enemy1CollisionRule.addEvents(Arrays.asList("enemy1-towerBullet-CollisionEvent"));

		Rule enemy2CollisionRule = new Rule();
		enemy2CollisionRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1, enemy2KillAction, towerBulletDissapearAction, towerBulletDeleteAction));
		enemy2CollisionRule.addEvents(Arrays.asList("enemy2-towerBullet-CollisionEvent"));

		level.setRuleAgenda(Arrays.asList(towerBulletOffMapRule, enemy1LeaveScreenRule, enemy1DeathRule,
				enemy2LeaveScreenRule, enemy2DeathRule, enemy1CollisionRule, enemy2CollisionRule));

		BezierCurve tempCurve1 = new BezierCurve(50, 50, 50, 50, 650, 50, 650, 50);
		BezierCurve tempCurve2 = new BezierCurve(650, 50, 650, 50, 650, 450, 650, 450);
		BezierCurve tempCurve3 = new BezierCurve(650, 450, 650, 450, 50, 450, 50, 450);
		BezierCurve tempCurve4 = new BezierCurve(50, 450, 50, 450, 50, 50, 50, 50);

		Path tempPath = new Path();
		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);
		tempPath.addCurve(tempCurve4);
		
		BezierCurve tempCurve8 = new BezierCurve(50, 50, 50, 50, 50, 450, 50, 450);
		BezierCurve tempCurve7 = new BezierCurve(50, 450, 50, 450, 650, 450, 650, 450);
		BezierCurve tempCurve6 = new BezierCurve(650, 450, 650, 450, 650, 50, 650, 50);
		BezierCurve tempCurve5 = new BezierCurve(650, 50, 650, 50, 50, 50, 50, 50);
		
		Path tempPath1 = new Path();
		tempPath1.addCurve(tempCurve8);
		tempPath1.addCurve(tempCurve7);
		tempPath1.addCurve(tempCurve6);
		tempPath1.addCurve(tempCurve5);
		
		Path[] pathArray = new Path[2];
		pathArray[0] = tempPath;
		pathArray[1] = tempPath1;

		GameMap tempMap = new GameMap("resources/images/Spider_Map.png", pathArray, 700, 500);

		IEntity tempSpawn = new Entity(40, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("enemy1", 2, 0, 10);
		Spawn spawn2 = new Spawn("enemy1", 1, 1, 20);
		IComponent tempSpawner = new SpawnerComponent(Arrays.asList(spawn, spawn2), 0);

		IEntity tempSpawn2 = new Entity(-40, "tempSpawn2", "spawner");
		Spawn spawn3 = new Spawn("enemy2", 2, 0, 10);
		Spawn spawn4 = new Spawn("enemy2", 1, 1, 20);
		IComponent tempSpawner1 = new SpawnerComponent(Arrays.asList(spawn3, spawn4), 1);

		IComponent tempPosition4 = new PositionComponent(0, 100);
		IComponent tempDisplay4 = new DisplayComponent(false);
		IComponent tempSize4 = new SizeComponent();
		tempSpawn.addComponent(tempSize4);
		tempSpawn.addComponent(tempSpawner);
		tempSpawn.addComponent(tempPosition4);
		tempSpawn.addComponent(tempDisplay4);

		IComponent tempPosition5 = new PositionComponent(0, 100);
		IComponent tempDisplay5 = new DisplayComponent(false);
		IComponent tempSize5 = new SizeComponent();
		tempSpawn2.addComponent(tempSize5);
		tempSpawn2.addComponent(tempSpawner1);
		tempSpawn2.addComponent(tempPosition5);
		tempSpawn2.addComponent(tempDisplay5);

		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(superTower, towerBullet, enemy1, enemy2));

		level.addEntityToMap(tempSpawn);
		level.addEntityToMap(tempSpawn2);
		level.setCurrentWaveIndex(0);
		level.setMap(tempMap);
		level.setNumWaves(2);
		level.setWaveDelayTimer(10);
		level.setAuthoredEntities(authoredEntities);

		return level;
	}

}