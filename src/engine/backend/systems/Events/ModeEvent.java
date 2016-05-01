package engine.backend.systems.Events;

import engine.backend.game_object.Mode;

public class ModeEvent implements IEvent {
	private static final String MODE = "Mode";
	private Mode myCurrentMode;

	public ModeEvent(Mode myCurrentMode) {
		this.myCurrentMode = myCurrentMode;
	}

	@Override
	public String getEventID() {
		return MODE + this.getClass().getSimpleName();
	}

}
