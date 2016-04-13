package authoring.backend.factories;


import authoring.controller.GlobalData;
import engine.backend.game_object.GameWorld;

import java.util.HashMap;
import java.util.Map;
import engine.backend.components.Component;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameFactory {
	
	private final GameWorld myGame;
	private final GlobalData myGlobalData;
	
	public GameFactory(GlobalData globaldata) {
		this.myGlobalData = globaldata;
		this.myGame = new GameWorld();
	}
	
	private void createGame() {
		
	}
	
	private void setUpEntityMap(){
		Map<String, Map<String, Entity>> map = new HashMap<String, Map<String, Entity>>();
		for (Entity entity : myGlobalData.getEntities()){
			Map<String, Entity> existingMap = null;
			if (map.containsKey(entity.getMyType())){
				existingMap = map.get(entity.getMyType());
			}
			else{
				existingMap = new HashMap<String, Entity>();
				map.put(entity.getMyType(), existingMap);
			}
			existingMap.put(entity.getName(), entity);
		}
		myGame.setEntityMap(map);
	}
}
