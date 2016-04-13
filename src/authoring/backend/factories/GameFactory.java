package authoring.backend.factories;

import authoring.controller.GlobalData;
import engine.backend.GameWorld;
import engine.backend.Level;
import engine.backend.Mode;
import engine.backend.components.Component;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameFactory {
	
	private final GameWorld game;
	private final GlobalData globaldata;
	
	public GameFactory(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.game = new GameWorld();
	}
	
	private void createGame() {
		
	}
	
	
	
}
