package authoring.backend.factories;

import authoring.controller.GlobalData;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

import java.util.HashMap;
import java.util.Map;
import engine.backend.entities.Entity;

public class GameFactory {

	private final GameWorld myGame;
	private final GlobalData myGlobalData;

	public GameFactory(GlobalData globaldata) {
		this.myGlobalData = globaldata;
		this.myGame = new GameWorld();
	}

	public GameWorld createGame() {
		setUpEntities();
		setUpLevels();
		setUpEntityMap();
		return myGame;
	}
	
	private void setUpLevels(){
		for (Mode mode: myGlobalData.getModes()){
			for(Level level: myGlobalData.getLevels()){
				if(level.getModeID().equals(mode.getName())){
					mode.addLevel(level);
				}
			}
		}
	}
	
	private void setUpEntities(){
		for (Level level : myGlobalData.getLevels()){
			for (Entity entity : myGlobalData.getEntities()){
				if (entity.getLevelID() == level.getId()){
					level.addToEntities(entity);
				}
			}
		}
	}

	private void setUpEntityMap() {
		Map<String, Map<String, Entity>> map = new HashMap<String, Map<String, Entity>>();
		for (Entity entity : myGlobalData.getEntities()) {
			Map<String, Entity> existingMap = null;
			if (map.containsKey(entity.getType())) {
				existingMap = map.get(entity.getType());
			} else {
				existingMap = new HashMap<String, Entity>();
				map.put(entity.getType(), existingMap);
			}
			existingMap.put(entity.getName(), entity);
		}
		myGame.setEntityMap(map);
	}
}
