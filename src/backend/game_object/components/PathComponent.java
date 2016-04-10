package backend.game_object.components;

public class PathComponent extends Component implements IComponent{
	
	//stores which path you're on
	private int pathID;
	private double myBezierTime;
	
	private boolean movesWithTime;
	//stores which curve in the path you're on
	private int curveID;
	
	public PathComponent(int path, int curve, boolean moves, double time){
		pathID = path;
		movesWithTime = moves;
		myBezierTime = time;
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
	
	public double getBezierTime() {
		return myBezierTime;
	}

	public void setBezierTime(double myBezierTime) {
		this.myBezierTime = myBezierTime;
	}

	public boolean movesWithTime(){
		return movesWithTime;
	}
	
	@Override
	public String getTag(){
		return "Path";
	}
	
}
