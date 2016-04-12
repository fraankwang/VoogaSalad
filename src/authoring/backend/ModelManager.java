package authoring.backend;

import java.util.Map;

import authoring.controller.GlobalData;
import backend.GameWorld;
import backend.Level;
import backend.Mode;
import backend.game_object.entities.Entity;

/*
 * @author: Jonathan Ma
 */

public class ModelManager implements IModel {
	
	private final GlobalData globaldata;
	
	public ModelManager(GlobalData globaldata) {
		this.globaldata = globaldata;
	}
	
	public void updateEntities(Map<String, String> data) {
		//TODO: invoke factory class to make a new Entity
		Entity entity = new Entity(0);
		for (Entity e : globaldata.getEntities()) {
			if (e.equals(entity)) {
				e = entity;
				return;
			}
		}
		globaldata.getEntities().add(entity);
	}

	public void updateLevels(Map<String, String> data) {
		//TODO: invoke factory class to make new Level
		Level level = new Level();
		for (Level l : globaldata.getLevels()) {
			if (l.equals(level)) {
				l = level;
				return;
			}
		}
		globaldata.getLevels().add(level);
		
	}

	public void updateModes(Map<String, String> data) {
		//TODO: invoke factory class to make new Mode
		Mode mode = new Mode();
		for (Mode m : globaldata.getModes()) {
			if (m.equals(mode)) {
				m = mode;
				return;
			}
		}
		globaldata.getModes().add(mode);
	}
	
	public GameWorld initializeGame() {
		GameWorld game = new GameWorld();
		
		return game;
	}
	

}
