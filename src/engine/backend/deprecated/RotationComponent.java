/**
 * author Raghav Kedia rk145
 */

package engine.backend.deprecated;

import engine.backend.components.Component;

public class RotationComponent extends Component {

	private double myAngle;
	private double myAngularVelocity;
	private double myAngularAcceleration;

	/**
	 * Initializes a rotation component from an existing rotation component.
	 * 
	 * @param component
	 */
	public RotationComponent(RotationComponent component) {
		this.myAngle = component.getAngle();
		this.myAngularVelocity = component.getMyAngularVelocity();
		this.myAngularAcceleration = component.getMyAngularAcceleration();
	}

	/**
	 * 
	 * @return a double representing the current angle.
	 */
	public double getAngle() {
		return myAngle;
	}

	/**
	 * 
	 * @return The double representing the angular velocity of the entity with
	 *         this component.
	 */
	public double getMyAngularVelocity() {
		return myAngularVelocity;
	}

	/**
	 * 
	 * @return The double representing the angular acceleration of the entity
	 *         with this component.
	 */
	public double getMyAngularAcceleration() {
		return myAngularAcceleration;
	}

	/**
	 * Sets the current angle of the entity with this component.
	 * 
	 * @param dir
	 */
	public void setAngle(double dir) {
		myAngle = dir;
	}

	/**
	 * Sets the current angle of the entity with this component.
	 * 
	 * @param dir
	 */
	public void setAngle(String dir) {
		double newVal = Double.parseDouble(dir);
		myAngle = newVal;
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

}
