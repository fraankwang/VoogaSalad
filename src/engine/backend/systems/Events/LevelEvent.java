package engine.backend.systems.Events;

import java.util.Collection;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;

public abstract class LevelEvent implements IEvent {
	private static final String LEVEL = "Level";
	private Level myCurrentLevel;

	@Override
	public String[] getEventID() {
		String[] eventID = { LEVEL + myCurrentLevel.getId() + this.getClass().getSimpleName() };
		return eventID;
	}

	@Override
	public Collection<IEntity> getEntities() {
		return null;
	}
	
	public void setmyCurrentLevel(Level myCurrentlevel) {
		this.myCurrentLevel = myCurrentLevel;
	}

}
