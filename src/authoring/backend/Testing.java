package authoring.backend;

import java.util.HashMap;
import java.util.Map;

import authoring.backend.data.DataContainer;
import authoring.backend.data.GlobalData;
import authoring.backend.factories.GameFactory;
import authoring.controller.AuthoringController;
import engine.backend.game_object.GameWorld;

public class Testing {
	
	public GameWorld test1() {
		GlobalData globalData = new GlobalData();
		AuthoringController controller = new AuthoringController(globalData);
		DataContainer dataContainer = globalData.getData();
		GameFactory gameFactory = new GameFactory(globalData);
		
		//Set up bullet1
		Map<String, String> bullet1 = new HashMap<String, String>();
		bullet1.put("Type", "Entity");
		bullet1.put("Name", "bullet1");
		bullet1.put("Genre", "Ammo");
		bullet1.put("DisplayComponent_Image", "bullet_sprite.png");
		bullet1.put("SizeComponent_Width", "10");
		bullet1.put("SizeComponent_Height", "10");
		bullet1.put("MovementComponent_Velocity", "50");
		bullet1.put("MovementComponent_CanMove", "True");
		bullet1.put("MovementComponent_CanRotate", "False");
		
		//Set up tower1
		Map<String, String> tower1 = new HashMap<String, String>();
		tower1.put("Type", "Entity");
		tower1.put("Name", "tower1");
		tower1.put("Genre", "Tower");
		tower1.put("DisplayComponent_Image", "DrumpfVader.png");
		tower1.put("FiringComponent_Ammunition", "bullet1");
		tower1.put("FiringComponent_AmmunitionSpeed", "50");
		tower1.put("FiringComponent_EnemyInSightRange", "20000");
		tower1.put("FiringComponent_FiringRate", "10");
		tower1.put("PurchaseComponent_Value", "20");
		tower1.put("SizeComponent_Width", "50");
		tower1.put("SizeComponent_Height", "50");
		
		//Set up ballon1
		Map<String, String> balloon1 = new HashMap<String, String>();
		balloon1.put("Type", "Entity");
		balloon1.put("Name", "balloon1");
		balloon1.put("Genre", "Enemy");
		balloon1.put("DisplayComponent_Image", "DrumpfVader.png");
		balloon1.put("SizeComponent_Width", "30");
		balloon1.put("SizeComponent_Height", "30");
		balloon1.put("MovementComponent_Velocity", "6");
		
		dataContainer.updateData(bullet1);
		dataContainer.updateData(tower1);
		dataContainer.updateData(balloon1);
		
		//Set up level1
		Map<String, String> level1 = new HashMap<String, String>();
		level1.put("Type", "Level");
		level1.put("Name", "level1");
		level1.put("WaveDelayTimer", "5");
		level1.put("MapBackgroundImage", "Park_Path.png");
		level1.put("MapWidth", "500");
		level1.put("MapHeight", "500");
		level1.put("Entities", "bullet1 tower1");
		level1.put("Paths", "0:0-0,0-0,0-0,200-200 200-200,50-50,150-150,0-300 0-300,150-150,250-250,400-400");
		level1.put("SpawnEntities", "0:balloon.0.10.2");
		
		dataContainer.updateData(level1);
		
		//Set up mode1
		Map<String, String> mode1 = new HashMap<String, String>();
		mode1.put("Type", "Mode");
		mode1.put("Name", "mode1");
		mode1.put("InitialLives", "5");
		mode1.put("InitialResources", "300");
		mode1.put("Levels", "0:level1");
		
		dataContainer.updateData(mode1);
		
		GameWorld gameWorld = gameFactory.createGame();
		
		return gameWorld;
	}

}
