package engine.backend.systems.Events;

public interface IEvent {
	
	public boolean thisEventMatches(IEvent event);
	
}
