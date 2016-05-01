package authoring.backend;

import java.io.IOException;
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
import engine.backend.game_object.GameWorld;

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
	
	public void exportGame() throws IOException {
		GameWorld game = gameFactory.createGame();
		GameWorldToXMLWriter writer = new GameWorldToXMLWriter();
		String raw = writer.getXMLfromObject(game);
		writer.stringToDocument(raw, "game1.xml");
	}
		
}
