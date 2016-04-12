package authoring.controller;

/*
 * @author: Jonathan Ma
 */

import java.util.ArrayList;
import java.util.List;

import backend.Level;
import backend.Mode;
import backend.game_object.entities.Entity;

public class GlobalData {
	
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
	
}
