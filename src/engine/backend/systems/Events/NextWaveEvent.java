package engine.backend.systems.Events;

public class NextWaveEvent implements IEvent {

	public NextWaveEvent() {
	}

	@Override
	public String getEventID() {
		return this.getClass().getSimpleName();
	}

}
