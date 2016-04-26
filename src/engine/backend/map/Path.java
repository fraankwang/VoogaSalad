

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
	
	public Path(int ID) {
		this.myCurves = new ArrayList<BezierCurve>();
		this.myID = ID;
	}
	
	public Path(List<BezierCurve> curves, int ID) {
		this.myCurves = curves;
		this.myID = ID;
	}
	
	public Path() {
	}
	
	public int getID() {
		return myID;
	}
	
	public int numCurves(){
		return myCurves.size();
	}
	
	public void addCurve(BezierCurve curve){
		myCurves.add(curve);
	}
	
	//return the proper curve based on the bezTime of entity. 
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
