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

public class Galaga implements ITestingGame{

	public GameWorld initGame() {
		GameWorld galaga = new GameWorld();
		Mode mode = new Mode("Galaga");
		Level level = getLevel1();
		mode.addLevel(level);
		GameStatistics gameStats = new GameStatistics(20, 0);
		mode.setGameStatistics(gameStats);

		galaga.addMode(mode);

		return galaga;
	}

	private Level getLevel1() {
		Level level = new Level("GalagaLevel1");
		level.setIndex(0);
		
		// build Galaga Bullet
		IEntity playerBullet = new Entity(141, "GalagaBullet", "Ammo");
		
		IComponent playerBulletPosition = new PositionComponent(0,0);
		
		MovementComponent playerBulletMovement = new MovementComponent(0, 0);
		playerBulletMovement.setTheta(0);
		playerBulletMovement.setCanMove(true);
		playerBulletMovement.setCanRotate(false);
		
		IComponent playerBulletCollision = new CollisionComponent();
		IComponent playerBulletDisplay = new DisplayComponent("resources/images/PurpleRedBullet.png");
		IComponent playerBulletSize = new SizeComponent(30, 60);
		
		playerBullet.addComponent(playerBulletDisplay);
		playerBullet.addComponent(playerBulletSize);
		playerBullet.addComponent(playerBulletPosition);
		playerBullet.addComponent(playerBulletCollision);
		playerBullet.addComponent(playerBulletMovement);
		
		//player bullet collision actions
		EntityAction playerBulletAction = new EntityAction("GalagaBullet", "Display", "CanBeShown", "false");
		EntityAction playerBulletAction1 = new EntityAction("GalagaBullet", "Display", "Delete", "true");
		
		
		
		// build Galaga Ship
		IEntity ship = new Entity(0, "GalagaShip", "Ships");
		
		IComponent shipPosition = new PositionComponent(320, 800);
		
		MovementComponent shipMovement = new MovementComponent(0, 0);
		shipMovement.setTheta(0);
		shipMovement.setCanMove(true);
		shipMovement.setCanRotate(false);
		
		IComponent shipCollision = new CollisionComponent();
		IComponent shipDisplay = new DisplayComponent("resources/images/GalagaShip (1).png");
		IComponent shipSize = new SizeComponent(60, 60);
		IComponent shipHealth = new HealthComponent(20);
		
		FiringComponent shipGun = new FiringComponent();
		shipGun.setAmmunition("GalagaBullet");
		shipGun.setDirectionToFire(new Vector(0,-1));
		shipGun.setAmmunitionSpeed(20);
		shipGun.setTargets(Arrays.asList("Enemy1", "enemy2", "enemy3"));
		shipGun.setEnemyInSightRange(100000);
		shipGun.setFiringRate(100000000);
		
		ship.addComponent(shipDisplay);
		ship.addComponent(shipSize);
		ship.addComponent(shipPosition);
		ship.addComponent(shipMovement);
		ship.addComponent(shipCollision);
		ship.addComponent(shipHealth);
		ship.addComponent(shipGun);
		
		// ship actions
		EntityAction shipDeathAction = new EntityAction("GalagaShip", "Display", "Delete", "true");
		EntityAction shipDeathAction1 = new EntityAction("GalagaShip", "Display", "CanBeShown", "false");
		EntityAction shipCollideAction = new EntityAction("GalagaShip", "Health", "Health", "-2");
		LevelAction shipDeathAction2 = new LevelAction("CurrentNumLives", "-10");
		
		LevelAction shipHitAction = new LevelAction("CurrentNumLives", "-1");
		
		EntityAction keyActionLeft = new EntityAction("GalagaShip", "Position", "XCoordinate", "-20");
		EntityAction keyActionRight = new EntityAction("GalagaShip", "Position", "XCoordinate", "20");
		EntityAction keyActionDown = new EntityAction("GalagaShip", "Position", "YCoordinate", "20");
		EntityAction keyActionUp = new EntityAction("GalagaShip", "Position", "YCoordinate", "-20");

		Rule ruleKeyLeft = new Rule();
		ruleKeyLeft.addActions(keyActionLeft);
		ruleKeyLeft.addEvents(Arrays.asList("GalagaShip-KeyPressedEntityEvent-LEFT"));
		Rule ruleKeyRight = new Rule();
		ruleKeyRight.addActions(keyActionRight);
		ruleKeyRight.addEvents(Arrays.asList("GalagaShip-KeyPressedEntityEvent-RIGHT"));
		Rule ruleKeyUp = new Rule();
		ruleKeyUp.addActions(keyActionUp);
		ruleKeyUp.addEvents(Arrays.asList("GalagaShip-KeyPressedEntityEvent-UP"));
		Rule ruleKeyDown = new Rule();
		ruleKeyDown.addActions(keyActionDown);
		ruleKeyDown.addEvents(Arrays.asList("GalagaShip-KeyPressedEntityEvent-DOWN"));

		
		// build Enemy Bullets
		IEntity enemyBullet = new Entity(23, "EnemyBullet", "Ammo");
		
		IComponent enemyBulletPosition = new PositionComponent(0, 0);
		
		MovementComponent enemyBulletMovement = new MovementComponent(0, 1);
		enemyBulletMovement.setTheta(0);
		enemyBulletMovement.setCanMove(true);
		enemyBulletMovement.setCanRotate(false);
		
		IComponent enemyBulletCollision = new CollisionComponent();
		IComponent enemyBulletDisplay = new DisplayComponent("resources/images/red-ball.png");
		IComponent enemyBulletSize = new SizeComponent(10, 30);
		
		enemyBullet.addComponent(enemyBulletDisplay);
		enemyBullet.addComponent(enemyBulletSize);
		enemyBullet.addComponent(enemyBulletPosition);
		enemyBullet.addComponent(enemyBulletCollision);
		enemyBullet.addComponent(enemyBulletMovement);
		
		// EnemyBullet collision actions
		
		EntityAction enemyBulletAction = new EntityAction("EnemyBullet", "Display", "CanBeShown", "false");
		EntityAction enemyBulletAction1 = new EntityAction("EnemyBullet", "Display", "Delete", "true");
		
		
		// build Enemy1
		IEntity enemy1 = new Entity(44, "Enemy1", "Enemies");
		
		IComponent enemy1Position = new PositionComponent(0, 100);
		
		MovementComponent enemy1Movement = new MovementComponent(7, 0);
		enemy1Movement.setTheta(0);
		enemy1Movement.setCanMove(true);
		enemy1Movement.setCanRotate(false);
		
		IComponent enemy1Collision = new CollisionComponent();
		IComponent enemy1Display = new DisplayComponent("resources/images/Bug1T.png");
		IComponent enemy1Size = new SizeComponent(40, 40);
		IComponent enemy1Health = new HealthComponent(1);
		IComponent enemy1Path = new PathComponent(0,0);
		
		FiringComponent enemy1Gun = new FiringComponent();
		enemy1Gun.setAmmunition("EnemyBullet");
		enemy1Gun.setDirectionToFire(new Vector(0,1));
		enemy1Gun.setAmmunitionAmount(50);
		enemy1Gun.setAmmunitionSpeed(6);
		enemy1Gun.setEnemyInSightRange(600);
		enemy1Gun.setFiringRate(3);
		enemy1Gun.setTargets(Arrays.asList("GalagaShip"));

		
		enemy1.addComponent(enemy1Display);
		enemy1.addComponent(enemy1Size);
		enemy1.addComponent(enemy1Position);
		enemy1.addComponent(enemy1Movement);
		enemy1.addComponent(enemy1Collision);
		enemy1.addComponent(enemy1Health);
		enemy1.addComponent(enemy1Gun);
		enemy1.addComponent(enemy1Path);
		
		// enemy1 actions
		EntityAction enemy1DeathAction = new EntityAction("Enemy1", "Display", "CanBeShown", "false");
		EntityAction enemy1DeathAction1 = new EntityAction("Enemy1", "Display", "Delete", "true");
		LevelAction enemy1KillAction = new LevelAction("CurrentResources", "10");
		
		// build Enemy2
				IEntity enemy2 = new Entity(1234, "enemy2", "Enemies");
				
				IComponent enemy2Position = new PositionComponent(0, 100);
				
				MovementComponent enemy2Movement = new MovementComponent(5, 0);
				enemy2Movement.setTheta(0);
				enemy2Movement.setCanMove(true);
				enemy2Movement.setCanRotate(false);
				
				IComponent enemy2Collision = new CollisionComponent();
				IComponent enemy2Display = new DisplayComponent("resources/images/Bug2T.png");
				IComponent enemy2Size = new SizeComponent(40, 40);
				IComponent enemy2Health = new HealthComponent(1);
				IComponent enemy2Path = new PathComponent(0,0);
				
				FiringComponent enemy2Gun = new FiringComponent();
				enemy2Gun.setAmmunition("EnemyBullet");
				enemy2Gun.setDirectionToFire(new Vector(0,1));
				enemy2Gun.setAmmunitionSpeed(5);
				enemy2Gun.setAmmunitionAmount(50);
				enemy2Gun.setEnemyInSightRange(500);
				enemy2Gun.setFiringRate(2);
				enemy2Gun.setTargets(Arrays.asList("GalagaShip"));
				
				enemy2.addComponent(enemy2Display);
				enemy2.addComponent(enemy2Size);
				enemy2.addComponent(enemy2Position);
				enemy2.addComponent(enemy2Movement);
				enemy2.addComponent(enemy2Collision);
				enemy2.addComponent(enemy2Health);
				enemy2.addComponent(enemy2Gun);
				enemy2.addComponent(enemy2Path);
				
				// enemy2 actions
				EntityAction enemy2DeathAction = new EntityAction("enemy2", "Display", "CanBeShown", "false");
				EntityAction enemy2DeathAction1 = new EntityAction("enemy2", "Display", "Delete", "true");
				LevelAction enemy2KillAction = new LevelAction("CurrentResources", "10");
		
				EntityAction enemyBulletAction2 = new EntityAction("EnemyBullet1", "Display", "CanBeShown", "false");
				EntityAction enemyBulletAction3 = new EntityAction("EnemyBullet1", "Display", "Delete", "true");
				
				//make enemy3
				
				IEntity enemy3 = new Entity(560, "enemy3", "Enemies");
				
						IComponent enemy3Position = new PositionComponent(0, 100);
						
						MovementComponent enemy3Movement = new MovementComponent(5, 0);
						enemy3Movement.setTheta(0);
						enemy3Movement.setCanMove(true);
						enemy3Movement.setCanRotate(false);
						
						IComponent enemy3Collision = new CollisionComponent();
						IComponent enemy3Display = new DisplayComponent("resources/images/Bug3T.png");
						IComponent enemy3Size = new SizeComponent(40, 40);
						IComponent enemy3Health = new HealthComponent(1);
						IComponent enemy3Path = new PathComponent(0, 0);
						
						FiringComponent enemy3Gun = new FiringComponent();
						enemy3Gun.setAmmunition("EnemyBullet1");
						enemy3Gun.setDirectionToFire(new Vector(0, 1));
						enemy3Gun.setAmmunitionSpeed(20);
						enemy3Gun.setEnemyInSightRange(700);
						enemy3Gun.setFiringRate(2);
						enemy3Gun.setTargets(Arrays.asList("GalagaShip"));
						
						enemy3.addComponent(enemy3Display);
						enemy3.addComponent(enemy3Size);
						enemy3.addComponent(enemy3Position);
						enemy3.addComponent(enemy3Movement);
						enemy3.addComponent(enemy3Collision);
						enemy3.addComponent(enemy3Health);
						enemy3.addComponent(enemy3Gun);
						enemy3.addComponent(enemy3Path);
				
						//EnemyBullet1
						IEntity enemyBullet1 = new Entity(232, "EnemyBullet1", "Ammo");

						IComponent enemyBulletPosition1 = new PositionComponent(0, 0);

						MovementComponent enemyBulletMovement1 = new MovementComponent(0, -10);
						enemyBulletMovement1.setTheta(0);
						enemyBulletMovement1.setCanMove(true);
						enemyBulletMovement1.setCanRotate(false);

						IComponent enemyBulletCollision1 = new CollisionComponent();
						IComponent enemyBulletDisplay1 = new DisplayComponent("resources/images/Bug5T.png");
						IComponent enemyBulletSize1 = new SizeComponent(50, 50);

						enemyBullet1.addComponent(enemyBulletDisplay1);
						enemyBullet1.addComponent(enemyBulletSize1);
						enemyBullet1.addComponent(enemyBulletPosition1);
						enemyBullet1.addComponent(enemyBulletCollision1);
						enemyBullet1.addComponent(enemyBulletMovement1);
						
						// enemy3 actions
						EntityAction enemy3DeathAction = new EntityAction("enemy3", "Display", "CanBeShown", "false");
						EntityAction enemy3DeathAction1 = new EntityAction("enemy3", "Display", "Delete", "true");
						LevelAction enemy3KillAction = new LevelAction("CurrentResources", "15");
				

//		List<EntityAction> myActions = new ArrayList<EntityAction>();
//		myActions.add(shipDeathAction);
//		List<String> myEvents = new ArrayList<String>();
//		myEvents.add("SimpleBullet-tempEntity-CollisionEvent");
//		myEvents.add("GalagaShip-DeathEvent");

		Rule playerHitRule = new Rule();
		playerHitRule.addActions(Arrays.asList(shipHitAction, shipCollideAction, enemyBulletAction, enemyBulletAction1));
		playerHitRule.addEvents(Arrays.asList("EnemyBullet-GalagaShip-CollisionEvent"));

		Rule playerHitRule1 = new Rule();
		playerHitRule1.addActions(Arrays.asList(shipHitAction, shipCollideAction, enemyBulletAction2, enemyBulletAction3));
		playerHitRule1.addEvents(Arrays.asList("EnemyBullet1-GalagaShip-CollisionEvent"));
		
		Rule playerDeathRule = new Rule();
		playerDeathRule.addActions(Arrays.asList(shipDeathAction, shipDeathAction1));
		playerDeathRule.addEvents(Arrays.asList("GalagaShip-DeathEvent"));

		Rule playerFireRule = new Rule();
		EntityAction shootAction = new EntityAction("GalagaShip", "Firing", "FireNow", "true");
		playerFireRule.addActions(Arrays.asList(shootAction));
		playerFireRule.addEvents(Arrays.asList("GalagaShip-KeyPressedEntityEvent-S"));

		Rule playerBulletOffMapRule = new Rule();
		playerBulletOffMapRule.addActions(Arrays.asList(playerBulletAction, playerBulletAction1));
		playerBulletOffMapRule.addEvents(Arrays.asList("GalagaBullet-OutOfMapEvent"));

		Rule enemy1LeaveScreenRule = new Rule();
		enemy1LeaveScreenRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1));
		enemy1LeaveScreenRule.addEvents(Arrays.asList("Enemy1-EndOfPathEvent"));
		
		Rule enemy1DeathRule = new Rule();
		enemy1DeathRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1, enemy1KillAction));
		enemy1DeathRule.addEvents(Arrays.asList("Enemy1-DeathEvent"));
		
		Rule enemy1CollideRule = new Rule();
		enemy1CollideRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1, shipDeathAction2, shipDeathAction, shipDeathAction1));
		enemy1CollideRule.addEvents(Arrays.asList("Enemy1-GalagaShip-CollisionEvent"));
		
		Rule enemy1HitRule = new Rule();
		enemy1HitRule.addActions(Arrays.asList(enemy1DeathAction, enemy1DeathAction1, playerBulletAction, playerBulletAction1, enemy1KillAction));
		enemy1HitRule.addEvents(Arrays.asList("Enemy1-GalagaBullet-CollisionEvent"));
		
		
		Rule enemy2LeaveScreenRule = new Rule();
		enemy2LeaveScreenRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1));
		enemy2LeaveScreenRule.addEvents(Arrays.asList("enemy2-EndOfPathEvent"));
		
		Rule enemy2DeathRule = new Rule();
		enemy2DeathRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1, enemy2KillAction));
		enemy2DeathRule.addEvents(Arrays.asList("enemy2-DeathEvent"));
		
		Rule enemy2CollideRule = new Rule();
		enemy2CollideRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1, shipDeathAction2, shipDeathAction, shipDeathAction1));
		enemy2CollideRule.addEvents(Arrays.asList("GalagaShip-enemy2-CollisionEvent"));
		
		Rule enemy2HitRule = new Rule();
		enemy2HitRule.addActions(Arrays.asList(enemy2DeathAction, enemy2DeathAction1, playerBulletAction, playerBulletAction1, enemy2KillAction));
		enemy2HitRule.addEvents(Arrays.asList("GalagaBullet-enemy2-CollisionEvent"));
		
		//enemy 3 rules
		Rule enemy3LeaveScreenRule = new Rule();
		enemy3LeaveScreenRule.addActions(Arrays.asList(enemy3DeathAction, enemy3DeathAction1));
		enemy3LeaveScreenRule.addEvents(Arrays.asList("enemy3-EndOfPathEvent"));
		
		Rule enemy3DeathRule = new Rule();
		enemy3DeathRule.addActions(Arrays.asList(enemy3DeathAction, enemy3DeathAction1, enemy3KillAction));
		enemy3DeathRule.addEvents(Arrays.asList("enemy3-DeathEvent"));
		
		Rule enemy3CollideRule = new Rule();
		enemy3CollideRule.addActions(Arrays.asList(enemy3DeathAction, enemy3DeathAction1, shipDeathAction2, shipDeathAction, shipDeathAction1));
		enemy3CollideRule.addEvents(Arrays.asList("GalagaShip-enemy3-CollisionEvent"));
		
		Rule enemy3HitRule = new Rule();
		enemy3HitRule.addActions(Arrays.asList(enemy3DeathAction, enemy3DeathAction1, playerBulletAction, playerBulletAction1, enemy3KillAction));
		enemy3HitRule.addEvents(Arrays.asList("GalagaBullet-enemy3-CollisionEvent"));

		level.setRuleAgenda(
				Arrays.asList(playerHitRule, playerHitRule1, playerDeathRule, playerFireRule, playerBulletOffMapRule, enemy1LeaveScreenRule, enemy1DeathRule,
						enemy1CollideRule, enemy2LeaveScreenRule, enemy2DeathRule, enemy2CollideRule, enemy3LeaveScreenRule, enemy3DeathRule, enemy3CollideRule, 
						enemy1HitRule, enemy2HitRule, enemy3HitRule, ruleKeyUp, ruleKeyDown, ruleKeyLeft, ruleKeyRight));
		
		//level.getRuleAgenda().forEach(e -> System.out.println(e));
		
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(68.0, 6.0, 388.0, 54.0, 76.0, 133.0, 425.0, 150.0);
		BezierCurve tempCurve2 = new BezierCurve(425.0,150.0,616.0,200.0,423.0,289.0,313.0,278.0);
		BezierCurve tempCurve3 = new BezierCurve(313.0,278.0,83.0,277.0,225.0,170.0,423.0,110.0);
		BezierCurve tempCurve4 = new BezierCurve(423.0,110.0,288.0,22.0,90.0,211.0,133.0,381.0);
		BezierCurve tempCurve5 = new BezierCurve(133.0,381.0,78.0,491.0,464.0,399.0,507.0,578.0);
		BezierCurve tempCurve6 = new BezierCurve(507.0,578.0,519.0,719.0,219.0,528.0,100.0,943.0);



		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);
		tempPath.addCurve(tempCurve4);
		tempPath.addCurve(tempCurve5);
		tempPath.addCurve(tempCurve6);


		Path tempPath1 = new Path();
		BezierCurve tempCurve7 = new BezierCurve(497.0,5.0,181.0,125.0,574.0,233.0,555.0,320.0);
		BezierCurve tempCurve8 = new BezierCurve(555.0,320.0,528.0,479.0,278.0,411.0,126.0,227.0);
		BezierCurve tempCurve9 = new BezierCurve(126.0,227.0,355.0,155.0,472.0,300.0,433.0,503.0);
		BezierCurve tempCurve10 = new BezierCurve(433.0,503.0,410.0,569.0,610.0,680.0,249.0,748.0);
		BezierCurve tempCurve11 = new BezierCurve(249.0,748.0,145.0,504.0,601.0,608.0,568.0,958.0);

		
		tempPath1.addCurve(tempCurve7);
		tempPath1.addCurve(tempCurve8);
		tempPath1.addCurve(tempCurve9);
		tempPath1.addCurve(tempCurve10);
		tempPath1.addCurve(tempCurve11);

		Path[] pathArray = new Path[2];
		pathArray[0] = tempPath;
		pathArray[1] = tempPath1;
		
		GameMap tempMap = new GameMap("resources/images/Galaga-Background1.jpg", pathArray, 640, 960);

		IEntity tempSpawn = new Entity(40, "tempSpawn", "spawner");
		Spawn spawn = new Spawn("Enemy1", 1, 0, 7);
		Spawn spawn2 = new Spawn("Enemy1", 1, 1, 7);
		Spawn spawn3 = new Spawn("enemy2", 1, 2, 7);
		
		IComponent tempSpawner = new SpawnerComponent(Arrays.asList(spawn, spawn2, spawn3), 0);

		IEntity tempSpawn2 = new Entity(-40, "tempSpawn2", "spawner");
		Spawn spawn4 = new Spawn("enemy2", 2, 0, 5);
		Spawn spawn5 = new Spawn("enemy2", 2, 1, 5);
		Spawn spawn6 = new Spawn("Enemy1", 1, 2, 8);
		Spawn spawn7 = new Spawn("enemy2", 2, 2, 4);
		IComponent tempSpawner1 = new SpawnerComponent(Arrays.asList(spawn4, spawn5, spawn6), 1);

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
		
		//makes another spawner 
				IEntity tempSpawn3 = new Entity(-50, "tempSpawn3", "spawner");
				Spawn spawn8 = new Spawn("enemy3", 2, 1, 10);
				Spawn spawn9 = new Spawn("enemy3", 2, 2, 10);
				IComponent tempSpawner3 = new SpawnerComponent(Arrays.asList(spawn8, spawn9), 0);

				IComponent tempPosition6 = new PositionComponent(0, 100);
				IComponent tempDisplay6 = new DisplayComponent(false);
				IComponent tempSize6 = new SizeComponent();
				tempSpawn3.addComponent(tempSize6);
				tempSpawn3.addComponent(tempSpawner3);
				tempSpawn3.addComponent(tempPosition6);
				tempSpawn3.addComponent(tempDisplay6);
		

//		IEntity tempEntity = new Entity(0, "tempEntity", "Spawns");
//		IComponent tempPosition = new PositionComponent(0, 100);
//		IComponent tempMovement = new MovementComponent(6, 0);
//		IComponent tempCollision = new CollisionComponent();
//		IComponent tempDisplay = new DisplayComponent("resources/images/DrumpfVader.png");
//		IComponent tempSize = new SizeComponent();
//		IComponent tempHealth = new HealthComponent(5);
//		IComponent pathComp = new PathComponent(0, 0);
//
//		tempEntity.addComponent(tempDisplay);
//		tempEntity.addComponent(tempSize);
//		tempEntity.addComponent(tempPosition);
//		tempEntity.addComponent(tempMovement);
//		tempEntity.addComponent(tempCollision);
//		tempEntity.addComponent(tempHealth);
//		tempEntity.addComponent(pathComp);

//		ShopItem item = new ShopItem("tempEntity2", "resources/images/DrumpfVader.png", 30);
//		ShopItem item2 = new ShopItem("SpeedPowerUp", "resources/images/bullet_sprite.png", 30);
//
		level.setShopItems(new ArrayList<ShopItem>());

//		IEntity tempEntity2 = new Entity(-5, "tempEntity2", "object2");
//		IComponent tempPosition2 = new PositionComponent(700, 60);
//		IComponent tempDisplay2 = new DisplayComponent("resources/images/DrumpfVader.png");
//		IComponent tempSize2 = new SizeComponent();
//		IComponent tempCollision2 = new CollisionComponent();
//		Vector myBulletVector = new Vector(0, 1222);
//		IComponent tempDisplay3 = new DisplayComponent("resources/images/bullet_sprite.png");
//		IComponent tempSize3 = new SizeComponent();
//
//		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 500, myBulletVector, 1);
//
//		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition");
//
//		mySimpleBullet.addComponent(tempCollision2);
//		mySimpleBullet.addComponent(tempPosition);
//		mySimpleBullet.addComponent(new MovementComponent(10, 0));
//		mySimpleBullet.addComponent(tempDisplay3);
//		mySimpleBullet.addComponent(tempSize3);
		
		
		Map<String, Map<String, IEntity>> myCreatableEntityMap = new HashMap<String, Map<String, IEntity>>();

		Map<String, IEntity> createdSpawns = new HashMap<String, IEntity>();
		createdSpawns.put("Enemy1", enemy1);
		createdSpawns.put("enemy2", enemy2);
		createdSpawns.put("enemy3", enemy3);

		Map<String, IEntity> createdAmmunition = new HashMap<String, IEntity>();
		createdAmmunition.put("GalagaBullet", playerBullet);
		createdAmmunition.put("EnemyBullet", enemyBullet);
		createdAmmunition.put("EnemyBullet1", enemyBullet1);	


		myCreatableEntityMap.put("Ammunition", createdAmmunition);
		myCreatableEntityMap.put("Spawns", createdSpawns);

		List<IEntity> authoredEntities = new ArrayList<IEntity>();
		authoredEntities.addAll(Arrays.asList(ship, playerBullet, enemy1, enemy2, enemy3, enemyBullet, enemyBullet1));

//		ArrayList<String> myTargets = new ArrayList<String>();
//		myTargets.add("tempEntity");
//		simpleFire.setTargets(myTargets);
//		tempEntity2.addComponent(tempDisplay2);
//		tempEntity2.addComponent(tempSize2);
//		tempEntity2.addComponent(tempPosition2);
//		tempEntity2.addComponent(simpleFire);

		Map<String, IEntity> createdTowers = new HashMap<String, IEntity>();
		myCreatableEntityMap.put("Ships", createdTowers);

		level.addEntityToMap(tempSpawn);
		level.addEntityToMap(tempSpawn2);
		level.addEntityToMap(tempSpawn3);
		level.addEntityToMap(ship);
		level.setCurrentWaveIndex(0);
		level.setMap(tempMap);
		level.setNumWaves(2);
		level.setWaveDelayTimer(5);
		level.setCurrentWaveTimer(50);
		level.setSendNextWave(false);
		level.setAuthoredEntities(authoredEntities);

		return level;
	}



}
