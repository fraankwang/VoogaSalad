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
	
	public void updateEntities(Map<String, String> data) {
		Entity entity = entityfactory.createEntity(data);
		globaldata.getEntities().add(entity);
	}

	public void updateLevels(Map<String, String> data) {
		Level level = levelfactory.createLevel(data);
		globaldata.getLevels().add(level);
	}

	public void updateModes(Map<String, String> data) {
		Mode mode = modefactory.createMode(data);
		globaldata.getModes().add(mode);
	}
	
	public void updateGame(Map<String, String> data) {
		globaldata.getGame().update(data);
	}
		
}
