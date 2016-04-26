package engine.backend.systems.Events;

public class EntityDroppedEvent {
	
	private double xPosition;
	private double yPosition;
	private String entityName;
	
	public EntityDroppedEvent(double x, double y, String name) {
		setXCoordinate(x);
		setYCoordinate(y);
		setEntityName(name);
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public double getXCoordinate() {
		return xPosition;
	}

	public void setXCoordinate(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getYCoordinate() {
		return yPosition;
	}

	public void setYCoordinate(double yPosition) {
		this.yPosition = yPosition;
	}
	
}
