/**
 * author Raghav Kedia rk145
 */

package backend.game_object.components;

public class DirectionComponent extends Component{
	
	private double myDirection;
	
	public DirectionComponent(double direction) {
		// TODO Auto-generated constructor stub
		myDirection = direction;
		tag = "Direction";
	}
	
	public double getDirection(){
		return myDirection;
	}
	
	public void setDirection(double dir){
		myDirection = dir;
	}

}
