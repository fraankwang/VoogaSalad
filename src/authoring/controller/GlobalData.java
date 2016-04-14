package authoring.controller;

/*
 * @author: Jonathan Ma
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class GlobalData {
	
	private DataContainer datacontainer;
	private List<Entity> entities;
	private List<Level> levels;
	private List<Mode> modes;
	
	public GlobalData() {
		this.datacontainer = new DataContainer();
		this.entities = new ArrayList<Entity>();
		this.levels = new ArrayList<Level>();
		this.modes = new ArrayList<Mode>();
	}
	
	public void updateData(Map<String, String> data) {
		datacontainer.updateData(data);
	}
	
	public DataContainer getData() {
		return datacontainer;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<Level> getLevels() {
		return levels;
	}
	
	public List<Mode> getModes() {
		return modes;
	}
	
}
