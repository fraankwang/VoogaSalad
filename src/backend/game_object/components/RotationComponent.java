/**
 * author Raghav Kedia rk145
 */

package backend.game_object.components;

import java.util.List;

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
	public String getTag(){
		return "Rotation";
	}

	@Override
	public void initWithParams(List params) {
		myAngle = (double) params.get(0);
	}

}
