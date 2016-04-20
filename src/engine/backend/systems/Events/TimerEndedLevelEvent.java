package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class TimerEndedLevelEvent extends LevelEvent{
	
	public TimerEndedLevelEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}
}
