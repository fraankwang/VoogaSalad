/**
 * author raghav kedia rk145
 */

package engine.backend.components;

public class PositionComponent extends Component {
	
	private Vector myPositionVector;
	private Vector myCriticalPosition;
	
	public PositionComponent() {
	}
	
	public PositionComponent(double x, double y){
		myPositionVector = new Vector(x, y);
	}

	public Vector getPositionVector() {
		return myPositionVector;
	}

	public void setPositionVector(Vector myPositionVector) {
		this.myPositionVector = myPositionVector;
	}

	public double getX() {
		return myPositionVector.getX();
	}
	
	public double getY() {
		return myPositionVector.getY();
	}

	public Vector getCriticalPosition() {
		return myCriticalPosition;
	}

	public void setCriticalPosition(Vector myCriticalPosition) {
		this.myCriticalPosition = myCriticalPosition;
	}

	@Override
	public void initWithParams(String[] params) {
		//x is 1, y is 2
		myPositionVector = new Vector(Double.parseDouble(params[0]), Double.parseDouble(params[1]));
	}

	@Override
	public String getComponentInfo() {
		return "X-coordinate: " + myPositionVector.getX() + " " + "Y-coordinate: " + myPositionVector.getY();
	}

}
