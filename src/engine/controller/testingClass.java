package engine.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.backend.components.CollisionComponent;
import engine.backend.components.DisplayComponent;
import engine.backend.components.FiringComponent;
import engine.backend.components.IComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.components.Vector;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;

public class testingClass {
	
	public testingClass() {
		
	}
	public GameWorld initTestGame(GameWorld myGameWorld) {
		GameWorld collisionTest = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		Level tempLevel = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		GameMap tempMap = new GameMap("", tempPath, 200, 200);

		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
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

		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2", 20);
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

		IEntity tempEntity3 = new Entity(2, "tempEntity3", "object3", 20);
		IComponent tempPosition3 = new PositionComponent(450, 450);
		IComponent tempDisplay3 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize3 = new SizeComponent();
		tempEntity3.addComponent(tempDisplay3);
		tempEntity3.addComponent(tempSize3);
		tempEntity3.addComponent(tempPosition3);

		tempLevel.addToEntities(tempEntity);
		tempLevel.addToEntities(tempEntity2);
		tempLevel.addToEntities(tempEntity3);
		tempLevel.setMap(tempMap);
		tempMode.addLevel(tempLevel);
		collisionTest.addMode(tempMode);
		return collisionTest;
	}

	public GameWorld testFiring() {
		GameWorld firingTest = new GameWorld();
		Mode mode = new Mode("test firing");
		Level level = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);
		GameMap tempMap = new GameMap("", tempPath, 200, 200);

		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
		IComponent tempPosition = new PositionComponent(0, 60);
		IComponent tempMovement = new MovementComponent(2, 0);
		IComponent tempCollision = new CollisionComponent();
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize = new SizeComponent();
		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(tempCollision);
		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2", 20);
		IComponent tempPosition2 = new PositionComponent(700, 60);
		IComponent tempDisplay2 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		Vector myBulletVector = new Vector(-1,0);
		FiringComponent simpleFire = new FiringComponent("SimpleBullet", 100, 5, 
				100, myBulletVector);
		IEntity mySimpleBullet = new Entity(2, "SimpleBullet", "Ammunition", 0);
		Map<String, Map<String, IEntity>> myCreatableEntityMap = new HashMap<String, Map<String, IEntity>>();
		Map<String, IEntity> createdAmmunition = new HashMap<String, IEntity>();
		createdAmmunition.put("SimpleBullet", mySimpleBullet);
		myCreatableEntityMap.put("Ammunition", createdAmmunition);
		firingTest.setEntityMap(myCreatableEntityMap);
		mySimpleBullet.addComponent(tempDisplay);
		ArrayList<String> myTargets = new ArrayList<String>();
		myTargets.add("tempEntity");
		simpleFire.setTargets(myTargets);
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(simpleFire);

		level.addToEntities(tempEntity);
		level.addToEntities(tempEntity2);
		level.addEntityToMap(tempEntity);
		level.addEntityToMap(tempEntity2);
		level.setMap(tempMap);
		mode.addLevel(level);
		firingTest.addMode(mode);
		return firingTest;
	}

	public GameWorld testCollision() {
		GameWorld collisionTest = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		Level tempLevel = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0, 0, 0, 0, 0, 0, 200, 200);
		BezierCurve tempCurve2 = new BezierCurve(200, 200, 50, 50, 150, 150, 0, 300);
		BezierCurve tempCurve3 = new BezierCurve(0, 300, 150, 150, 250, 250, 400, 400);

		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);

		GameMap tempMap = new GameMap("", tempPath, 200, 200);

		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
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

		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2", 20);
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

		IEntity tempEntity3 = new Entity(2, "tempEntity3", "object3", 20);
		IComponent tempPosition3 = new PositionComponent(450, 450);
		IComponent tempDisplay3 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize3 = new SizeComponent();
		tempEntity3.addComponent(tempDisplay3);
		tempEntity3.addComponent(tempSize3);
		tempEntity3.addComponent(tempPosition3);

		tempLevel.addToEntities(tempEntity);
		tempLevel.addToEntities(tempEntity2);
		tempLevel.addToEntities(tempEntity3);
		tempLevel.addEntityToMap(tempEntity);
		tempLevel.addEntityToMap(tempEntity2);
		tempLevel.setMap(tempMap);
		tempMode.addLevel(tempLevel);
		collisionTest.addMode(tempMode);
		return collisionTest;
	}

}
