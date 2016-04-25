package authoring.backend;

import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.factories.EntityFactory;
import authoring.backend.factories.LevelFactory;
import authoring.backend.factories.ModeFactory;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

/*
 * @author: Jonathan Ma
 */

public class ModelManager implements IModel {
	
	private final GlobalData globaldata;
	private final EntityFactory entityfactory;
	private final LevelFactory levelfactory;
	private final ModeFactory modefactory;
	
	public ModelManager(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.entityfactory = new EntityFactory();
		this.levelfactory = new LevelFactory();
		this.modefactory = new ModeFactory();
	}
	
	public void updateEntities(String command, Map<String, String> data) {
		Entity entity = entityfactory.createEntity(data);
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
