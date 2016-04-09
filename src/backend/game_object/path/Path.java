package backend.game_object.path;

import java.util.List;

import backend.game_object.components.Vector;

public class Path implements IPath{
	
	//This class should contain a list or array of Bezier Curves
	
	private List<BezierCurve> myCurves;
	
	public Path() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector getNewPositionOnPath(Vector posVector, Vector velVector, double currint curveID){
		Vector newPos = new Vector();
		BezierCurve currCurve = myCurves.get(curveID);
		double speed = velVector.calculateMagnitude();
		double bezTimeStep = currCurve.getLength() / speed;
		
		
		
		return newPos;
	}
	
}
