package authoring.controller;

import java.util.ArrayList;
import java.util.List;

import backend.Level;
import backend.Mode;
import backend.game_object.components.Component;
import backend.game_object.entities.Entity;

public class GlobalData {
	
	private List<Component> components;
	private List<Entity> entities;
	private List<Level> levels;
	private List<Mode> modes;
	
	public GlobalData() {
		this.components = new ArrayList<Component>();
		this.entities = new ArrayList<Entity>();
		this.levels = new ArrayList<Level>();
		this.modes = new ArrayList<Mode>();
	}
	
	public List<Component> getComponents() {
		return components;
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
