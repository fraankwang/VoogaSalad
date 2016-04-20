package engine.backend.systems;

import engine.backend.game_object.Level;
import engine.backend.systems.Events.LevelEvent;

public class ResourceBoughtLevelEvent extends LevelEvent{

	public ResourceBoughtLevelEvent(Level myCurrentLevel) {
		super.setmyCurrentLevel(myCurrentLevel);
	}

}
