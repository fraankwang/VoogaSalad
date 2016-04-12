/**
 * author raghav kedia rk145
 */

package backend.game_object.components;

import java.util.List;

public class PositionComponent extends Component{
	
	private double myCoordX;
	private double myCoordY;
	private Vector myPositionVector;
	
	
/*	public PositionComponent(){
		myCoordX = 100;
		myCoordY = 100;
	}
*/
	public PositionComponent() {
		
	}
	
	public double[] getPosition(){
		double[] position = {myCoordX, myCoordY};
		return position;
	}
	
	public void setPosition(double x, double y){
		myCoordX = x;
		myCoordY = y;
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public String getTag(){
		return "Position";
	}

	public Vector getPositionVector() {
		return myPositionVector;
	}

	public void setPositionVector(Vector myPositionVector) {
		this.myPositionVector = myPositionVector;
	}

	public double getX() {
		return myCoordX;
	}
	
	public double getY() {
		return myCoordY;
	}

	@Override
	public void initWithParams(List params) {
//		setPositionVector(new Vector(x, y));
		myCoordX = (double) params.get(0);
		myCoordY = (double) params.get(1);
	}

}
