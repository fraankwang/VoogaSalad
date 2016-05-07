package authoring.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.factories.AuthoringEntityFactory;
import authoring.backend.factories.AuthoringLevelFactory;
import authoring.backend.factories.AuthoringModeFactory;
import authoring.backend.factories.GameFactory;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;
import backend.xml_converting.GameWorldToXMLWriter;
import backend.xml_converting.ObjectToXMLWriter;
import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

/*
 * @author: Jonathan Ma
 */

public class ModelManager implements IModel {

	private final GlobalData globaldata;
	private final AuthoringEntityFactory entityfactory;
	private final AuthoringLevelFactory levelfactory;
	private final AuthoringModeFactory modefactory;
	private final GameFactory gameFactory;

	public ModelManager(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.entityfactory = new AuthoringEntityFactory();
		this.levelfactory = new AuthoringLevelFactory();
		this.modefactory = new AuthoringModeFactory();
		this.gameFactory = new GameFactory(globaldata);
	}

	public void updateEntities(String command, Map<String, String> data) {
		AuthoringEntity entity = entityfactory.createEntity(data);
		if (command.equals("Update")) {
			globaldata.getEntities().add(entity);
			return;
		}
		if (command.equals("Delete")) {
			globaldata.getEntities().remove(entity);
			return;
		}
	}

	public void updateLevels(String command, Map<String, String> data) {
		AuthoringLevel level = levelfactory.createLevel(data);
		if (command.equals("Update")) {
			globaldata.getLevels().add(level);
			return;
		}
		if (command.equals("Delete")) {
			globaldata.getLevels().remove(level);
			return;
		}
	}

	public void updateModes(String command, Map<String, String> data) {
		AuthoringMode mode = modefactory.createMode(data);
		if (command.equals("Update")) {
			globaldata.getModes().add(mode);
			return;
		}
		if (command.equals("Delete")) {
			globaldata.getModes().remove(mode);
			return;
		}
	}

	public void updateGame(Map<String, String> data) {
		if (data.containsKey("Name")) {
			globaldata.getGame().setName(data.get("Name"));
		}
	}

	public void exportGame(String url) throws IOException {
		GameWorld game = gameFactory.createGame();
		GameWorldToXMLWriter writer = new GameWorldToXMLWriter();
		String raw = writer.getXMLfromObject(game);
		ObjectToXMLWriter.stringToDocument(raw, url);
	}

	public void reloadGame(String url) throws IOException {
		System.out.println("reached reload game");
		File file = new File(url);
		GameWorldToXMLWriter writer = new GameWorldToXMLWriter();
		String raw = ObjectToXMLWriter.documentToString(file);
		GameWorld game = (GameWorld) writer.xMLToObject(raw);
		reload(game);
	}

	private void reload(GameWorld game) {
		Collection<Mode> modes = game.getModes().values();
		Map<String, Level> levelMap = new HashMap<String, Level>();
		Map<String, IEntity> entityMap = new HashMap<String, IEntity>();
		for (Mode mode : modes) {
			Collection<Level> modeLevels = mode.getLevels().values();
			for (Level level : modeLevels) {
				levelMap.put(level.getName(), level);
			}
		}
		
		Collection<Level> levels = levelMap.values();

		for (Level level : levels) {
			Collection<IEntity> levelEntities = level.getAuthoredEntities();
			for (IEntity entity : levelEntities) {
				entityMap.put(entity.getName(), entity);
			}
		}

		Collection<IEntity> entities = entityMap.values();

		reloadEntities(entities);
		reloadLevels(levels);
		reloadModes(modes);
	}

	private void reloadModes(Collection<Mode> modes) {
		for (Mode mode : modes) {
			AuthoringMode authoringMode = new AuthoringMode(mode);
			globaldata.getModes().add(authoringMode);
		}
	}

	private void reloadLevels(Collection<Level> levels) {
		for (Level level : levels) {
			AuthoringLevel authoringLevel = new AuthoringLevel(level);
			globaldata.getLevels().add(authoringLevel);
		}
	}

	private void reloadEntities(Collection<IEntity> entities) {
		for (IEntity entity : entities) {
			AuthoringEntity authoringEntity = new AuthoringEntity(entity);
			globaldata.getEntities().add(authoringEntity);
		}
	}

}
