package authoring.backend;

import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.factories.AuthoringEntityFactory;
import authoring.backend.factories.AuthoringLevelFactory;
import authoring.backend.factories.AuthoringModeFactory;
import authoring.backend.game_objects.AuthoringEntity;


/*
 * @author: Jonathan Ma
 */

public class ModelManager implements IModel {
	
	private final GlobalData globaldata;
	private final AuthoringEntityFactory entityfactory;
	private final AuthoringLevelFactory levelfactory;
	private final AuthoringModeFactory modefactory;
	
	public ModelManager(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.entityfactory = new AuthoringEntityFactory();
		this.levelfactory = new AuthoringLevelFactory();
		this.modefactory = new AuthoringModeFactory();
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
		Level level = levelfactory.createLevel(data);
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
		Mode mode = modefactory.createMode(data);
		if (command.equals("Update")) {
			globaldata.getModes().add(mode);
			return;
		}
		if (command.equals("Delete")) {
			globaldata.getModes().remove(mode);
			return;
		}
	}

	public void updateGame(String command, Map<String, String> data) {
		globaldata.getGame().update(data);
	}
		
}
