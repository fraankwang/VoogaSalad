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
	public String getComponentInfo() {
		return "X-coordinate: " + myPositionVector.getX() + " " + "Y-coordinate: " + myPositionVector.getY();
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "XCoordinate":
			double x = Double.parseDouble(data);
			double y = 0;
			if (myPositionVector != null){
				y = myPositionVector.getY();
			}
			this.myPositionVector = new Vector(x, y);
			return;
		case "YCoordinate":
			double y2 = Double.parseDouble(data);
			double x2 = 0;
			if (myPositionVector != null) {
				x2 = myPositionVector.getX();
			}
			this.myPositionVector = new Vector(x2, y2);
			return;
		}
	}

}
