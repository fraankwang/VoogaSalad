package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

public class SpawnerComponent extends Component {

	private List<Spawn> mySpawns;
	private int pathID;
	
	public SpawnerComponent(){
		mySpawns = new ArrayList<Spawn>();
	}
	
	public void addSpawn(Spawn spawn){
		mySpawns.add(spawn);
	}
	
	public List<Spawn> getSpawns(){
		return mySpawns;
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub
		
	}
	
	public int getPathID(){
		return pathID;
	}

}
