

package engine.backend.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author raghavkedia
 *
 */

public class Path implements IPath{
	
	//This class should contain a list or array of Bezier Curves
	
	private List<BezierCurve> myCurves;
	private int myID;
	/**
	 * Default constructor.  Assigns path with empty list of Bezier curves and ID
	 * @param ID
	 */
	public Path(int ID) {
		this.myCurves = new ArrayList<BezierCurve>();
		this.myID = ID;
	}
	/**
	 * Constructs path with list of bezier curves and ID
	 * @param curves
	 * @param ID
	 */
	public Path(List<BezierCurve> curves, int ID) {
		this.myCurves = curves;
		this.myID = ID;
	}
	/**
	 * Builds path without an ID
	 */
	public Path() {
		this.myCurves = new ArrayList<BezierCurve>();
	}
	/**
	 * returns ID of the path
	 * @return ID
	 */
	public int getID() {
		return myID;
	}
	/**
	 * returns the number of Bezier curves in this path
	 * @return number of Curves
	 */
	public int numCurves(){
		return myCurves.size();
	}
	/**
	 * Adds a curve to the path
	 */
	public void addCurve(BezierCurve curve){
		myCurves.add(curve);
	}
	
/**
 * Return the proper curve based on the bezTime of entity. 
 */
	public BezierCurve getCurveFromTime(double bezTime){
		
		int numCurves = myCurves.size();
		int index = (int) Math.floor(bezTime); 
		if(index == numCurves){
			return myCurves.get(index - 1);
		}
		else if(index < 0){
			return myCurves.get(0);
		}
		return myCurves.get(index); 
	}
	/**
	 * Returns a String beginning with an ID and then all of the Bezier Curves to strings
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(myID);
		sb.append(":");
		for (BezierCurve curve : myCurves) {
			sb.append(curve.toString());
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
}
