package authoring.backend.data;

/*
 * @author: Jonathan Ma
 */

import java.util.Map;

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
	
	public ObservableList<Entity> getEntities() {
		return entities;
	}
	
	public ObservableList<Level> getLevels() {
		return levels;
	}
	
	public ObservableList<Mode> getModes() {
		return modes;
	}

}
