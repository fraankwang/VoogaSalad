package authoring.backend.factories;

import engine.backend.Level;

public class LevelFactory {

	public LevelFactory() {
		
	}
	
	public Level createLevel(Object info){
		//do some parsing
		int parsedId = 0; //placeholder
		Level newLevel = new Level(parsedId);
		setUpLevel(newLevel, info);
		return newLevel;
	}
	
	private void setUpLevel(Level level, Object info){
		//do shit with info
		//make sure to set up parent id
	}

}
