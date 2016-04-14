package authoring.backend;

/*
 * @author: Jonathan Ma
 */

import java.util.List;
import java.util.Map;

import authoring.controller.DataContainer;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class GlobalData {
	
	private DataContainer datacontainer;
	private ObservableList<Entity> entities;
	private ObservableList<Level> levels;
	private ObservableList<Mode> modes;
	
	public GlobalData() {
		this.datacontainer = new DataContainer();
		this.entities = new ObservableList<Entity>();
		this.levels = new ObservableList<Level>();
		this.modes = new ObservableList<Mode>();
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
