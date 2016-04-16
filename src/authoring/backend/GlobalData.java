package authoring.backend;

/*
 * @author: Jonathan Ma
 */

import java.util.List;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class GlobalData {
	
	private DataContainer datacontainer;
	private EntityList entities;
	private LevelList levels;
	private ModeList modes;
	
	public GlobalData() {
		this.datacontainer = new DataContainer();
		this.entities = new EntityList();
		this.levels = new LevelList();
		this.modes = new ModeList();
	}
	
	public void updateData(Map<String, String> data) {
		datacontainer.updateData(data);
	}
	
	public DataContainer getData() {
		return datacontainer;
	}
	
	public List<Entity> getEntities() {
		return entities.getList();
	}
	
	public List<Level> getLevels() {
		return levels.getList();
	}
	
	public List<Mode> getModes() {
		return modes.getList();
	}

}
