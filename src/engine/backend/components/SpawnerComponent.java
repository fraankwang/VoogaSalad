package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.IEntity;

public class SpawnerComponent extends Component implements IComponent {

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

	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub

	}

	public int getPathID() {
		return pathID;
	}

}
