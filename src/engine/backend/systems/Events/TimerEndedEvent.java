package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class TimerEndedEvent extends LevelEvent{
	
	public TimerEndedEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}
}
