package authoring.controller;

/*
 * @author: Jonathan Ma
 */

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;

public class GlobalData {
	
	private IController myController;
	private DataContainer input;
	private List<Entity> entities;
	private List<Level> levels;
	private List<Mode> modes;
	
	public GlobalData() {
		this.input = new DataContainer();
		this.entities = new ArrayList<Entity>();
		this.levels = new ArrayList<Level>();
		this.modes = new ArrayList<Mode>();
	}
	
	public DataContainer getInput() {
		return input;
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

	public void setController(IController controller) {
		myController = controller;
		
	}
	
}
