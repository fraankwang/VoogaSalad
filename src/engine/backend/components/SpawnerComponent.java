package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class SpawnerComponent extends Component {
=======
import engine.backend.entities.IEntity;

public class SpawnerComponent extends Component implements IComponent {
>>>>>>> e93ac3fc426f825a1e77f5ed0035bef6cb53a4b2

	private List<Spawn> mySpawns;
	private int pathID;

	public SpawnerComponent() {
		mySpawns = new ArrayList<Spawn>();
	}

	public void addSpawn(Spawn spawn) {
		mySpawns.add(spawn);
	}

	public List<Spawn> getSpawns() {
		return mySpawns;
	}

<<<<<<< HEAD
	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

=======
>>>>>>> e93ac3fc426f825a1e77f5ed0035bef6cb53a4b2
	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

	public int getPathID() {
		return pathID;
	}

}
