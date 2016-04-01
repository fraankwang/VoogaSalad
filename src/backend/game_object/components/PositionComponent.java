/**
 * author raghav kedia rk145
 */

package backend.game_object.components;

public class PositionComponent extends Component{
	
	private double coordX;
	private double coordY;
	
	public PositionComponent(double x, double y) {
		// TODO Auto-generated constructor stub
		coordX = x;
		coordY = y;
		tag = "Position";
	}
	
	public double[] getPosition(){
		double[] position = {coordX, coordY};
		return position;
	}


}
