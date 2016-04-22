package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.List;

public abstract class LevelEvent implements IEvent {
	private static final String LEVEL = "Level";

	@Override
	public List<String> getEventID(List<String> identifiers) {
		List<String> eventIDs = new ArrayList<String>();
		eventIDs.add(LEVEL + identifiers.get(0) + this.getClass().getSimpleName());
		return eventIDs;
	}

}
