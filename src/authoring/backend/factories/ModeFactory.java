package authoring.backend.factories;

import engine.backend.game_object.Mode;

public class ModeFactory {

	public ModeFactory() {
		
	}
	
	public Mode createMode(Object info){
		int parsedId = 0; //temporary placeholder
		Mode newMode = new Mode(parsedId);
		setUpMode(newMode, info);
		return new Mode(parsedId);
	}
	
	private void setUpMode(Mode mode, Object info){
		//do other shit with string
	}

}
