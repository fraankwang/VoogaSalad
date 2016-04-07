/**
 * author raghav kedia rk145
 */

package backend.game_object.components;

public class PositionComponent extends Component{
	
	private double myCoordX;
	private double myCoordY;
	private Vector myPositionVector;
	
	
	public PositionComponent(){
		myCoordX = 100;
		myCoordY = 100;
		tag = "Position";
	}
	
	public PositionComponent(double x, double y) {
		// TODO Auto-generated constructor stub
//		setPositionVector(new Vector(x, y));
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
	
	@Override
	public void update(){
		
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

}
