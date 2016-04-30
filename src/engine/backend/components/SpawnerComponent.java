package engine.backend.components;

import java.util.List;


import engine.backend.entities.IEntity;

public class SpawnerComponent extends Component implements IComponent {

/**
 * A component that gives the ability to an entity to spawn more entities.
 * @author 
 *
 */

	private List<Spawn> mySpawns;
	private int pathID;
	
	public SpawnerComponent(List<Spawn> spawns, int id){
		mySpawns = spawns;
		pathID = id;
	}
	
	public SpawnerComponent(SpawnerComponent component) {
		this.mySpawns = component.getSpawns();
		this.pathID = component.getPathID();
	}

	public void addSpawn(Spawn spawn) {
		mySpawns.add(spawn);
	}

	public List<Spawn> getSpawns() {
		return mySpawns;
	}

	@Override
	public String getComponentInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(pathID);
		sb.append(":");
		for (Spawn spawn : mySpawns) {
			sb.append(spawn.getInfo());
			sb.append("_");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

	public int getPathID() {
		return pathID;
	}

}
