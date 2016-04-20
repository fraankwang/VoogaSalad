package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class NoMoreLivesEvent extends LevelEvent{
	
	public NoMoreLivesEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}
}
