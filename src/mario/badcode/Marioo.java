package mario.badcode;

import engine.backend.game_object.Mode;

public class Marioo {
	
	public void addMode(Mode mode) {
		
	}
	
	public void addNewLevelToCurrentMode(Mode mode) {
		
	}
	
	public void initializeGameObject(int numOfMode, int numOfLevels) {
		int iteration = 0;
		while (iteration < numOfMode) {
			Mode mode1 = new Mode();
			addMode(mode1);
			for (int i = 0; iteration < numOfLevels; iteration++) {
				addNewLevelToCurrentMode(mode1);
			}
			iteration++;
		}
	}

}
