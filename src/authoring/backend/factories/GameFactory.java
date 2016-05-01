package authoring.backend.factories;

import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringGame;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;
import engine.backend.entities.IEntity;

public class GameFactory {

	private GameWorld myGame;
	private final GlobalData myGlobalData;
	private Map<String, IEntity> entityMap;
	private Map<String, Level> levelMap;
	private Map<String, Mode> modeMap;
	
	private final EntityFactory entityFactory;
	private final LevelFactory levelFactory;
	private final ModeFactory modeFactory;

	public GameFactory(GlobalData globaldata) {
		this.myGlobalData = globaldata;
		this.entityMap = new HashMap<String, IEntity>();
		this.levelMap = new HashMap<String, Level>();
		this.modeMap = new HashMap<String, Mode>();
		this.entityFactory = new EntityFactory();
		this.levelFactory = new LevelFactory();
		this.modeFactory = new ModeFactory();
	}

	public GameWorld createGame() {
		setupEntityMap();
		setupLevelMap();
		setupModeMap();
		setupGame();
		return myGame;
	}







	
	public void setupGame() {

//		AuthoringGame authoringGame = myGlobalData.getGame();
//		int startLives = authoringGame.getStartLives();
//		int defeatLives = authoringGame.getNumLivesDefeat();
//		double gameTimer = authoringGame.getGameTimer();
//		double resources = authoringGame.getStartResources();
//		String gameType = authoringGame.getGameType();
//		GameStatistics gameStatistics = new GameStatistics(startLives, resources);
//		this.myGame = new GameWorld(gameType, gameStatistics, modeMap);

		AuthoringGame authoringGame = myGlobalData.getGame();		
		String name = authoringGame.getName();
		this.myGame = new GameWorld(name, modeMap);

	}
	
	private void setupEntityMap() {
		List<AuthoringEntity> entities = myGlobalData.getEntities().getList();
		for (AuthoringEntity entity : entities) {
			entityMap.put(entity.getName(), entityFactory.createEntity(entity));
		}
	}
	
	private void setupLevelMap() {
		List<AuthoringLevel> authoringLevels = myGlobalData.getLevels().getList();
		for (AuthoringLevel authoringLevel : authoringLevels) {
			levelMap.put(authoringLevel.getName(), levelFactory.createLevel(authoringLevel, entityMap));
		}
	}
	
	private void setupModeMap() {
		List<AuthoringMode> authoringModes = myGlobalData.getModes().getList();
		for (AuthoringMode authoringMode : authoringModes) {
			modeMap.put(authoringMode.getName(), modeFactory.createMode(authoringMode, levelMap));
		}
	}
	
}
