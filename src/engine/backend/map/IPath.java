package engine.backend.map;

import java.util.ResourceBundle;

import engine.backend.entities.IEntity;

public interface IPath {

	public int numCurves();
	
	public void addCurve(BezierCurve curve);
	
	public BezierCurve getCurveFromTime(double bezTime);

}
