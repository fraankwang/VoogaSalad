package backend.game_object.map;

import backend.game_object.components.Vector;

public interface IBezierCurve {
	
	public Vector calculateNewBezierPoint(double t);
	public Vector calculateNewBezierTangent(double t);
	public double getLength();
	public double calculateBezierTime(double velocity);
	
	
}
