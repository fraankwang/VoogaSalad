package engine.backend.systems.Events;

public interface IEvent {

	/**
	 * 
	 * @return The eventID that identifies what type of event this is.
	 */
	public String getEventID();

}
