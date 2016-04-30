package engine.backend.systems.Events;

public abstract class LevelEvent implements IEvent {

	private static final String LEVEL = "Level";
	private int levelindex;

	@Override
	public String getEventID() {
		return (LEVEL + levelindex + this.getClass().getSimpleName());
	}

	public int getLevelindex() {
		return levelindex;
	}

	public void setLevelindex(int levelindex) {
		this.levelindex = levelindex;
	}

}
