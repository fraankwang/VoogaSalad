package backend;

import backend.game_object.components.Vector;

public class BezierCurve {
	
	private Vector startPointVector;
	private Vector control1Vector;
	private Vector control2Vector;
	private Vector endPointVector;
	
	public BezierCurve(double startX, double startY, double c1X, double c1Y, 
			double c2X, double c2Y, double endX, double endY) {
		
		startPointVector = new Vector(startX, startY);
		control1Vector = new Vector(c1X, c1Y);
		control2Vector = new Vector(c2X, c2Y);
		endPointVector = new Vector(endY, endX);
		
	}

}
