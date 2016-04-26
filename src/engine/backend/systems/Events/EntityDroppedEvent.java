package engine.backend.systems.Events;

public class EntityDroppedEvent {
	
	private double xPosition;
	private double yPosition;
	private String entityName;
	
	public EntityDroppedEvent(double x, double y, String name) {
		xPosition = x;
		yPosition = y;
		entityName = name;
	}
	
}
