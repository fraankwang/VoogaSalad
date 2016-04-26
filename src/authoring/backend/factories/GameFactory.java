package authoring.backend.factories;

import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

import java.util.HashMap;
import java.util.Map;

import authoring.backend.data.GameData;
import authoring.backend.data.GlobalData;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameFactory {

	private final GameWorld myGame;
	private final GlobalData myGlobalData;

	public GameFactory(GlobalData globaldata) {
		this.myGlobalData = globaldata;
		this.myGame = new GameWorld();
	}

	public GameWorld createGame() {
		setUpGame();
		setUpLevels();
		setUpModes();
		setUpEntityMap();
		return myGame;
	}
	
	private void setUpGame() {
		GameStatistics gameStatistics = new GameStatistics();
		GameData gameData = myGlobalData.getGame();
		gameStatistics.setGameTimer(gameData.getGameTimer());
		gameStatistics.setStartMoney(gameData.getStartResources());
		gameStatistics.setNumLivesDefeat(gameData.getNumLivesDefeat());
		gameStatistics.setStartLives(gameData.getStartLives());
		
		myGame.setGameStatistics(gameStatistics);
		myGame.setGameType(gameData.getGameType());
		myGame.setNumPlayers(gameData.getNumPlayers());		
	}
	
	private void setUpModes(){
		for (Mode mode : myGlobalData.getModes().getList()){
			for (String levelName : mode.getLevelNames()) {
				for (Level level : myGlobalData.getLevels().getList()) {
					if (level.getName().equals(levelName)) {
						mode.addLevel(level);
					}
				}
			}
		}
	}
	
	private void setUpLevels(){
		for (Level level : myGlobalData.getLevels().getList()){
			for (String entityName : level.getEntityNames()) {
				for (Entity entity : myGlobalData.getEntities().getList()) {
					if (entity.getName().equals(entityName)) {
						level.addAuthoredEntity(entity);
					}
				}
			}
		}
	}

	private void setUpEntityMap() {
		Map<String, Map<String, IEntity>> map = new HashMap<String, Map<String, IEntity>>();
		for (IEntity entity : myGlobalData.getEntities().getList()) {
			Map<String, IEntity> existingMap = null;
			if (map.containsKey(entity.getType())) {
				existingMap = map.get(entity.getType());
			} else {
				existingMap = new HashMap<String, IEntity>();
				map.put(entity.getType(), existingMap);
			}
			existingMap.put(entity.getName(), entity);
		}
		myGame.setEntityMap(map);
	}
}
