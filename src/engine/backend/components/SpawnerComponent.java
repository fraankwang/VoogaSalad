package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class SpawnerComponent extends Component {
=======
import engine.backend.entities.IEntity;

public class SpawnerComponent extends Component implements IComponent {
>>>>>>> origin

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
>>>>>>> origin
	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

	public int getPathID() {
		return pathID;
	}

}
