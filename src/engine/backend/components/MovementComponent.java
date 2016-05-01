package engine.backend.components;

/**
 * 
 * @author raghavkedia
 *
 */

public class MovementComponent extends Component {

	private double myVelocity;
	// private double myAcceleration;
	private Vector myCurrentVelocityVector;
	private Vector myDefaultVelocityVector;
	private double myTheta;

	// figure out direction of velocity;

	// this is the angle to rotate myTheta by
	private double myCurrentOmega;
	private double myInitialOmega;

	private boolean canMove;
	private boolean canRotate;

	/**
	 * Initializes a movement component from an existing movement component.
	 * 
	 * @param component
	 */
	public MovementComponent(MovementComponent component) {
		this.myCurrentVelocityVector = component.getCurrentVelocityVector();
		this.myDefaultVelocityVector = component.getCurrentVelocityVector();
		this.myTheta = component.getTheta();
		this.myCurrentOmega = component.getCurrentOmega();
		this.myInitialOmega = component.getInitialOmega();
		this.canMove = component.canMove();
		this.canRotate = component.canRotate();
		this.myVelocity = component.getVelocity();
	}

	// for demo purposes
	/**
	 * Initializes a movement component.
	 * 
	 * @param xspeed
	 * @param yspeed
	 */
	public MovementComponent(double xspeed, double yspeed) {
		setCurrentVelocityVector(new Vector(xspeed, yspeed));
	}
	
	public MovementComponent() {
		setCurrentVelocityVector(new Vector(0, 0));
	}
	
	public Vector getCurrentVelocityVector(){
		return myCurrentVelocityVector;
	}

	/**
	 * Sets the current velocity of the entity with this component.
	 * 
	 * @param vel
	 */
	public void setCurrentVelocityVector(Vector vel) {
		myCurrentVelocityVector = vel;
	}

	/**
	 * Sets a default velocity for an entity with this component.
	 * 
	 * @param vel
	 */
	public void setDefaultVelocityVector(Vector vel) {
		myDefaultVelocityVector = vel;
	}

	/**
	 * 
	 * @return
	 */
	public double getTheta() {
		return myTheta;
	}

	/**
	 * Sets the speed of movement for the entity with this component.
	 * 
	 * @param deltaSpeed
	 */
	public void setSpeed(String deltaSpeed) {
		double delta = Double.parseDouble(deltaSpeed);
		myCurrentVelocityVector.scale(delta);
	}

	/**
	 * 
	 * @param theta
	 */
	public void setTheta(double theta) {
		myTheta = theta;
	}

	/**
	 * 
	 * @return
	 */
	public double getCurrentOmega() {
		return myCurrentOmega;
	}

	/**
	 * 
	 * @return
	 */
	public double getInitialOmega() {
		return myInitialOmega;
	}

	/**
	 * 
	 * @param omega
	 */
	public void setOmega(double omega) {
		this.myCurrentOmega = omega;
	}

	/**
	 * 
	 * @return A boolean that tells whether or not this entity with this
	 *         component can currently move.
	 */
	public boolean canMove() {
		return canMove;
	}

	/**
	 * 
	 * @return Whether or not an entity with this component can currecntly
	 *         rotate.
	 */
	public boolean canRotate() {
		return canRotate;
	}

	/**
	 * Sets whether or not the entity with this component can move.
	 * 
	 * @param bool
	 */
	public void setCanMove(boolean bool) {
		canMove = bool;
	}

	/**
	 * Sets whether or not the entity with this component can rotate.
	 * 
	 * @param bool
	 */
	public void setCanRotate(boolean bool) {
		canRotate = bool;
	}

	public double getVelocity() {
		return myVelocity;
	}
	
	@Override
	public String toString() {
		return "Velocity: " + myVelocity;
	}

	@Override
	public String getComponentInfo() {
		return "Velocity:" + myVelocity + "," + "CanMove:" + canMove + "," + "CanRotate:" + canRotate;
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {

		case "Velocity":
			this.myVelocity = Double.parseDouble(data);
			return;
		case "CanMove":
			if (data.equals("True")) {
				this.canMove = true;
				return;
			} else {
				this.canMove = false;
				return;
			}
		case "CanRotate":
			if (data.equals("True")) {
				this.canRotate = true;
				return;
			} else {
				this.canRotate = false;
				return;
			}
		}
	}
}
