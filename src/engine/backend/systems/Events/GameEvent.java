package engine.backend.systems.Events;

public class GameEvent implements IEvent {
	private String modeName;
	private int level;

	public GameEvent(String modeName, int level) {
		this.modeName = modeName;
		this.level = level;
	}

	@Override
	public String getEventID() {
		return null;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
