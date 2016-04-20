package engine.backend.systems.Events;

import engine.backend.game_object.Level;

public abstract class LevelEvent implements IEvent {
	private static final String LEVEL = "Level";
	private Level myCurrentLevel;

	@Override
	public String[] getEventID() {
		String[] eventID = { LEVEL + myCurrentLevel.getId() + this.getClass().getSimpleName() };
		return eventID;
	}

	public void setmyCurrentLevel(Level myCurrentLevel) {
		this.myCurrentLevel = myCurrentLevel;
	}

}
