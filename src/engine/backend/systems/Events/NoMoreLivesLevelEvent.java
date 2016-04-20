package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class NoMoreLivesLevelEvent extends LevelEvent{
	
	public NoMoreLivesLevelEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}
}
