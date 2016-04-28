package engine.backend.systems.Events;

public class WaveOverEvent implements IEvent {

	public WaveOverEvent() {
	}

	@Override
	public String getEventID() {
		return this.getClass().getSimpleName();
	}
}
