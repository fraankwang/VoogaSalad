package authoring.backend.factories;

import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameFactory {

	private final GameWorld myGame;
	private final GlobalData myGlobalData;
	private final EntityFactory entityFactory;
	private final LevelFactory levelFactory;
	private final ModeFactory modeFactory;

	public GameFactory(GlobalData globaldata) {
		this.myGlobalData = globaldata;
		this.myGame = new GameWorld();
		this.entityFactory = new EntityFactory();
		this.levelFactory = new LevelFactory();
		this.modeFactory = new ModeFactory();
	}

	public GameWorld createGame() {
		
		return myGame;
	}
	
	public List<IEntity> getEntitiesList() {
		List<IEntity> entities = new ArrayList<IEntity>();
		for (AuthoringEntity authoringEntity : myGlobalData.getEntities().getList()) {
			entities.add(entityFactory.createEntity(authoringEntity));
		}
		return entities;
	}
	
	public List<Level> getLevelsList() {
		List<Level> levels = new ArrayList<Level>();
		for (AuthoringLevel authoringLevel : myGlobalData.getLevels().getList()) {
			levels.add(levelFactory.createLevel(authoringLevel));
		}
		return levels;
	}
	
	public List<Mode> getModesList() {
		List<Mode> modes = new ArrayList<Mode>();
		for (AuthoringMode authoringMode : myGlobalData.getModes().getList()) {
			modes.add(modeFactory.createMode(authoringMode));
		}
		return modes;
	}
	
	
}
