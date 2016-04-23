/**
 * author Raghav Kedia rk145
 */

package engine.backend.components;

public class RotationComponent extends Component implements IComponent{
	
	private double myAngle;
	private double myAngularVelocity;
	private double myAngularAcceleration;
	
	public RotationComponent(RotationComponent component) {
		this.myAngle = component.getAngle();
		this.myAngularVelocity = component.getMyAngularVelocity();
		this.myAngularAcceleration = component.getMyAngularAcceleration();
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public double getMyAngularVelocity() {
		return myAngularVelocity;
	}
	
	public double getMyAngularAcceleration() {
		return myAngularAcceleration;
	}
	
	public void setAngle(double dir){
		myAngle = dir;
	}
	
	public void setAngle(String dir){
		double newVal = Double.parseDouble(dir);
		myAngle = newVal;
	}

	@Override
	public void initWithParams(String[] params) {
		myAngle = Double.parseDouble(params[0]);
	}

}
