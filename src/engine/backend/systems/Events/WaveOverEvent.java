package engine.backend.systems.Events;

public class WaveOverEvent implements IEvent{
	
	private String eventID;
	
	@Override
	public String getEventID() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	
	
	
}
