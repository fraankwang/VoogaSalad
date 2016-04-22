package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.List;

import engine.backend.game_object.Mode;

public class ModeEvent implements IEvent {
	private Mode myCurrentMode;

	public ModeEvent(Mode myCurrentMode) {
		this.myCurrentMode = myCurrentMode;
	}

	@Override
	public List<String> getEventID(List<String> identifiers) {
		List<String> eventIDs = new ArrayList<String>();
		eventIDs.add("Mode" + myCurrentMode.getName() + this.getClass().getSimpleName());
		return eventIDs;
	}

}
