/**
 * author raghav kedia rk145
 */

package backend.game_object.components;

public class PositionComponent extends Component{
	
	private double myCoordX;
	private double myCoordY;
	
	public PositionComponent(){
		myCoordX = 100;
		myCoordY = 100;
		tag = "Position";
	}
	
	public PositionComponent(double x, double y) {
		// TODO Auto-generated constructor stub
		myCoordX = x;
		myCoordY = y;
		tag = "Position";
	}
	
	public double[] getPosition(){
		double[] position = {myCoordX, myCoordY};
		return position;
	}
	
	public void setPosition(double x, double y){
		myCoordX = x;
		myCoordY = y;
	}

	public double getX() {
		return myCoordX;
	}
	
	public double getY() {
		return myCoordY;
	}

}
