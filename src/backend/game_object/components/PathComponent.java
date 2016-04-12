package backend.game_object.components;

import java.util.List;

public class PathComponent extends Component implements IComponent{
	
	//stores which path you're on
	private int pathID;
	
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
	public void initWithParams(List params) {
		pathID = (int) params.get(0);
		curveID = (int) params.get(1);
	}
	
}
