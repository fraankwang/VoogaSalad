/**
 * author Raghav Kedia rk145
 */

package engine.backend.components;

public class RotationComponent extends Component implements IComponent{
	
	private double myAngle;
	private double myAngularVelocity;
	private double myAngularAcceleration;
	
	public RotationComponent() {
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public void setAngle(double dir){
		myAngle = dir;
	}

	@Override
	public void initWithParams(String[] params) {
		myAngle = Double.parseDouble(params[0]);
	}
	
	@Override
	public String getValue() {
		return myAngularVelocity + "";
	}

}
