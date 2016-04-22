package engine.backend.map;

public interface IPath {

	public int numCurves();
	
	public void addCurve(BezierCurve curve);
	
	public BezierCurve getCurveFromTime(double bezTime);

}
