/**
 * author Raghav Kedia rk145
 */

package backend.game_object.components;

public class RotationComponent extends Component implements IComponent{
	
	private double myAngle;
	private double myAngularVelocity;
	private double myAngularAcceleration;
	
	public RotationComponent(double direction) {
		// TODO Auto-generated constructor stub
		myAngle = direction;
	}
	
	public double getAngle(){
		return myAngle;
	}
	
	public void setAngle(double dir){
		myAngle = dir;
	}

}
