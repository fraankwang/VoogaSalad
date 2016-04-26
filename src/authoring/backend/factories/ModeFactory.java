package authoring.backend.factories;

import authoring.backend.game_objects.AuthoringMode;
import engine.backend.game_object.Mode;
import engine.backend.game_object.ModeStatistics;

public class ModeFactory {
	
	public ModeFactory() {
	
	}
	
	public Mode createMode(AuthoringMode authoringMode) {
		String name = authoringMode.getName();
		int numLives = authoringMode.getInitialLives();
		double resources = authoringMode.getInitialResources();
		ModeStatistics modeStatistics = new ModeStatistics(numLives, resources);
		
		return new Mode(name, modeStatistics);
	}
}
