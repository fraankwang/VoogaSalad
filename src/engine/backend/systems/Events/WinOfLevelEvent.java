package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class WinOfLevelEvent extends LevelEvent {

	public WinOfLevelEvent(Level levelEnded) {
		super.setmyCurrentLevel(levelEnded);
	}

}
