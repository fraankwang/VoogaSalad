
package engine.backend.components;

/**
 * 
 * @author raghavkedia
 *
 */

public class PathComponent extends Component {
	
	//stores which path you're on
	private int pathID;
	private double myBezierTime;

	private boolean reachedEndOfPath;
	private boolean movesWithTime;
	// stores which curve in the path you're on
	private int curveID;

	/**
	 * Initializes a path component from an existing path component.
	 * 
	 * @param component
	 */
	public PathComponent(PathComponent component) {
		this.pathID = component.getPathID();
		this.myBezierTime = component.getBezierTime();
		this.reachedEndOfPath = component.getReachedEndOfPath();
		this.movesWithTime = component.movesWithTime();
		this.curveID = component.getCurveID();
	}

	public PathComponent(int id, double time) {
		pathID = id;
		myBezierTime = time;
		movesWithTime = true;
	}

	/**
	 * 
	 * @return The int identifier for this curve.
	 */
	public int getCurveID() {
		return curveID;
	}

	/**
	 * Sets the identifier for this curve.
	 * 
	 * @param newID
	 */
	public void setCurveID(int newID) {
		curveID = newID;
	}

	/**
	 * 
	 * @return The int identifier for this path.
	 */
	public int getPathID() {
		return pathID;
	}

	/**
	 * Sets the path identifier.
	 * 
	 * @param newID
	 */
	public void setPathID(int newID) {
		pathID = newID;
	}

	public double getBezierTime() {
		return myBezierTime;
	}

	/**
	 * 
	 * @param myBezierTime
	 */
	public void setBezierTime(double myBezierTime) {
		this.myBezierTime = myBezierTime;
	}

	/**
	 * 
	 * @return
	 */
	public boolean movesWithTime() {
		return movesWithTime;
	}

	/**
	 * 
	 * @return A boolean representing whether or not the end of path has been reached.
	 */
	public boolean getReachedEndOfPath() {
		return reachedEndOfPath;
	}

	/**
	 * Sets the boolean for whether the end of path has been reached by the entity with this component.
	 * @param bool
	 */
	public void setReachedEndOfPath(boolean bool) {
		reachedEndOfPath = bool;
	}	
	
	@Override
	public String getComponentInfo() {
		return "PathID:" + pathID;
	}

	@Override
	public void update(String dataName, String data) {
		if (dataName.equals("PathID")) {
			this.pathID = Integer.parseInt(data);
		}
	}

}
