/**
 * author raghav kedia rk145
 */

package engine.backend.components;

import java.util.List;

public class PositionComponent extends Component implements IComponent{
	
	private Vector myPositionVector;
	
	public PositionComponent(){
		myPositionVector = new Vector();
	}
	
	public PositionComponent(double x, double y) {
		// TODO Auto-generated constructor stub
//		setPositionVector(new Vector(x, y));
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

	@Override
	public void initWithParams(List params) {
		// TODO Auto-generated method stub
		
	}

}
