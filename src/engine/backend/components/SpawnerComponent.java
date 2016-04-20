package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

public class SpawnerComponent extends Component {

	private List<Spawn> mySpawns;
	
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
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
