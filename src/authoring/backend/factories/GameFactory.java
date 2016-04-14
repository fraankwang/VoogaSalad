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
		setUpLevels();
		setUpModes();
		setUpEntityMap();
		return myGame;
	}
	
	private void setUpModes(){
		for (Mode mode : myGlobalData.getModes()){
			for (String levelName : mode.getLevelNames()) {
				for (Level level : myGlobalData.getLevels()) {
					if (level.getName().equals(levelName)) {
						mode.addLevel(level);
					}
				}
			}
		}
	}
	
	private void setUpLevels(){
		for (Level level : myGlobalData.getLevels()){
			for (String entityName : level.getEntityNames()) {
				for (Entity entity : myGlobalData.getEntities()) {
					if (entity.getName().equals(entityName)) {
						level.addEntity(entity);
					}
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
