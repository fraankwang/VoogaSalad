package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class LoseOfLevelEvent extends LevelEvent {

	public LoseOfLevelEvent(Level levelEnded) {
		super.setmyCurrentLevel(levelEnded);
	}

}
