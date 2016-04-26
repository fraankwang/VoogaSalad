
package engine.backend.components;

/**
 * 
 * @author raghav kedia rk145
 *
 */
public class PositionComponent extends Component {

	private Vector myPositionVector;
	private Vector myCriticalPosition;
	private double range;
	public static final int DEFAULTPOSITION = 50;
	
	public PositionComponent() {
		double posX = DEFAULTPOSITION;
		double posY = DEFAULTPOSITION;
		
		myPositionVector = new Vector(posX, posY);
	}

	public PositionComponent(PositionComponent posComp) {
		double posX = posComp.getX();
		double posY = posComp.getY();
		double range = 10;

		myPositionVector = new Vector(posX, posY);
	}
	
	/**
	 * 
	 *  Returns the firing range of the entity
	 * @return range 
	 */
	public double getRange(){
		return range;
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

	@Override
	public void initWithParams(String[] params) {
		// x is 1, y is 2
		myPositionVector = new Vector(Double.parseDouble(params[0]), Double.parseDouble(params[1]));
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

}
