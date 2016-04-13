package authoring.backend.factories;

import engine.backend.game_object.Mode;

public class ModeFactory {

	public ModeFactory() {
		
	}
	
	public Mode createMode(String name){
		//setUpMode(newMode, info);
		return new Mode(name);
	}
	
	private void setUpMode(Mode mode, Object info){
		//do other shit with string
	}

}
