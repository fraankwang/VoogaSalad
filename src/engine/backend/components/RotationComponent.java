/**
 * author Raghav Kedia rk145
 */

package engine.backend.components;

public class RotationComponent extends Component {
	
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
	
	public void setAngle(String dir){
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
