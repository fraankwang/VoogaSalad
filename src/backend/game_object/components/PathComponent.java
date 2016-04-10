package backend.game_object.components;

public class PathComponent extends Component implements IComponent{
	
	//stores which path you're on
	private int pathID;
	
	//stores which curve in the path you're on
	private int curveID;
	
	public PathComponent(int path, int curve){
		pathID = path;
		curveID = curve;
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
	
}
