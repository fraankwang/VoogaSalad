package engine.backend.systems.Events;

public class EntityDroppedEvent implements IEvent {

	private double xPosition;
	private double yPosition;
	private String entityName;
	private double entityValue;
	
	public EntityDroppedEvent(double x, double y, String name, double cost) {
		setXCoordinate(x);
		setYCoordinate(y);
		setEntityName(name);
		setEntityValue(cost);
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

	public void setEntityValue(double value) {
		entityValue = value;
	}

	public double getEntityValue() {
		return entityValue;
	}

	@Override
	public String getEventID() {
		// TODO Auto-generated method stub
		return entityName + this.getClass().getSimpleName();
	}
}
