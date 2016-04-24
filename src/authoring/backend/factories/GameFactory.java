package authoring.backend.factories;

import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

import java.util.HashMap;
import java.util.Map;

import authoring.backend.data.GlobalData;
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
<<<<<<< HEAD
	
	private void setUpModes(){
		for (Mode mode : myGlobalData.getModes().getList()){
			for (String levelName : mode.getLevelNames()) {
				for (Level level : myGlobalData.getLevels().getList()) {
					if (level.getName().equals(levelName)) {
						mode.addLevel(level);
					}
=======

	private void setUpLevels() {
		for (Mode mode : myGlobalData.getModes()) {
			for (Level level : myGlobalData.getLevels()) {
				if (level.getModeID().equals(mode.getName())) {
					mode.addLevel(level);
>>>>>>> origin
				}
			}
		}
	}
<<<<<<< HEAD
	
	private void setUpLevels(){
		for (Level level : myGlobalData.getLevels().getList()){
			for (String entityName : level.getEntityNames()) {
				for (Entity entity : myGlobalData.getEntities().getList()) {
					if (entity.getName().equals(entityName)) {
						level.addEntity(entity);
					}
=======

	private void setUpEntities() {
		for (Level level : myGlobalData.getLevels()) {
			for (Entity entity : myGlobalData.getEntities()) {
				if (entity.getLevelID() == level.getId()) {
					level.addToEntities(entity);
>>>>>>> origin
				}
			}
		}
	}

	private void setUpEntityMap() {
		Map<String, Map<String, Entity>> map = new HashMap<String, Map<String, Entity>>();
		for (Entity entity : myGlobalData.getEntities().getList()) {
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
