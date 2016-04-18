package authoring.backend.factories;

import java.util.Map;

import engine.backend.game_object.Mode;

public class ModeFactory {

	public ModeFactory() {
		
	}
	
	public Mode createMode(Map<String, String> data){
		//setUpMode(newMode, info);
		String name = null;
		for (String key : data.keySet()) {
			if (key.equals("ModeName")) {
				name = data.get(key);
			}
		}
		return new Mode(name);
	}
	
	private void setUpMode(Mode mode, Object info){
		//do other shit with string
	}
}
