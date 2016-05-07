package engine.backend.systems.Events;

public class WaveOverEvent implements IEvent {

	private double timerLength;

	public WaveOverEvent(double timer) {
		setTimerLength(timer);
	}

	@Override
	public String getEventID() {
		return this.getClass().getSimpleName();
	}

	public double getTimerLength() {
		return timerLength;
	}

	public void setTimerLength(double timerLength) {
		this.timerLength = timerLength;
	}
}
