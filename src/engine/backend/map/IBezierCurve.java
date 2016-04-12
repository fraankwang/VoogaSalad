package engine.backend.map;

import engine.backend.components.Vector;

public interface IBezierCurve {
	
	public Vector calculateNewBezierPoint(double t);
	public Vector calculateNewBezierTangent(double t);
	public double getLength();
	public double calculateBezierTime(double velocity);
	
	
}
