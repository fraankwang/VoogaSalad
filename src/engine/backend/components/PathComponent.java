
package engine.backend.components;

import java.util.List;

/**
 * 
 * @author raghavkedia
 *
 */

public class PathComponent extends Component implements IComponent{
	
	//stores which path you're on
	private int pathID;
	private double myBezierTime;
	
	
	private boolean reachedEndOfPath;
	private boolean movesWithTime;
	//stores which curve in the path you're on
	private int curveID;

	public PathComponent(){
	}
	
	public int getCurveID(){
		return curveID;
	}
	public void setCurveID(int newID){
		curveID = newID;
	}
	public int getPathID(){
		return pathID;
	}
	public void setPathID(int newID){
		pathID = newID;
	}

	@Override
	public void initWithParams(String[] params) {
		pathID = Integer.parseInt(params[0]);
		curveID = Integer.parseInt(params[1]);
		movesWithTime = Boolean.parseBoolean(params[2]);
		myBezierTime = Double.parseDouble(params[3]);
		reachedEndOfPath = false;
	}
	
	public double getBezierTime() {
		return myBezierTime;
	}

	public void setBezierTime(double myBezierTime) {
		this.myBezierTime = myBezierTime;
	}

	public boolean movesWithTime(){
		return movesWithTime;
	}
	
	public boolean getReachedEndOfPath(){
		return reachedEndOfPath;
	}
	
	public void didReachEndOfPath(){
		reachedEndOfPath = true;
	}
	
}
