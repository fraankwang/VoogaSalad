package engine.backend.systems.Events;

import java.util.Collection;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Mode;

public class ModeEvent implements IEvent {
	private Mode myCurrentMode;

	public ModeEvent(Mode myCurrentMode) {
		this.myCurrentMode = myCurrentMode;
	}

	@Override
	public String[] getEventID() {
		String[] eventID = { "Mode" + myCurrentMode.getName() + this.getClass().getSimpleName() };
		return eventID;
	}

	@Override
	public Collection<IEntity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
