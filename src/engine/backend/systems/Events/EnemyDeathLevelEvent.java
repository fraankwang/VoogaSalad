package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public class EnemyDeathLevelEvent extends LevelEvent {

	public EnemyDeathLevelEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}

}
