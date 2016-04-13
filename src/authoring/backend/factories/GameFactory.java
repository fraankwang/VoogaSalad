package authoring.backend.factories;

import authoring.controller.GlobalData;
import engine.backend.game_object.GameWorld;


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
