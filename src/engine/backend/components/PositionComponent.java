package engine.backend.components;

/**
 * 
 * @author raghav kedia rk145
 *
 */
public class PositionComponent extends Component {

	private Vector myPositionVector;
	private Vector myCriticalPosition;
	
	public PositionComponent() {
		
	}

	public PositionComponent(PositionComponent posComp) {
		double posX = posComp.getX();
		double posY = posComp.getY();

		myPositionVector = new Vector(posX, posY);
	}

	/**
	 * Creates a position component for an entity with this x and y coordinate.
	 * 
	 * @param x
	 * @param y
	 */
	public PositionComponent(double x, double y) {
		myPositionVector = new Vector(x, y);
	}

	/**
	 * 
	 * @return A vector representing the position.
	 */
	public Vector getPositionVector() {
		return myPositionVector;
	}

	/**
	 * Sets the vector representing the position.
	 * 
	 * @param myPositionVector
	 */
	public void setPositionVector(Vector myPositionVector) {
		this.myPositionVector = myPositionVector;
	}

	/**
	 * 
	 * @return X component of the position vector.
	 */
	public double getX() {
		return myPositionVector.getX();
	}

	/**
	 * 
	 * @return The y component of this position vector.
	 */
	public double getY() {
		return myPositionVector.getY();
	}

	/**
	 * 
	 * @return A vector representing a critical position.
	 */
	public Vector getCriticalPosition() {
		return myCriticalPosition;
	}

	/**
	 * Sets a critical position for the entity with this component.
	 * @param myCriticalPosition
	 */
	public void setCriticalPosition(Vector myCriticalPosition) {
		this.myCriticalPosition = myCriticalPosition;
	}

	@Override
	public String getComponentInfo() {
		return "XCoordinate:" + myPositionVector.getX() + "," + "YCoordinate:" + myPositionVector.getY();
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "XCoordinate":
			double x = Double.parseDouble(data);
			double y = 0;
			if (myPositionVector != null){
				y = myPositionVector.getY();
			}
			this.myPositionVector = new Vector(x, y);
			return;
		case "YCoordinate":
			double y2 = Double.parseDouble(data);
			double x2 = 0;
			if (myPositionVector != null) {
				x2 = myPositionVector.getX();
			}
			this.myPositionVector = new Vector(x2, y2);
			return;
		}
	}

}
