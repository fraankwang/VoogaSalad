package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import engine.backend.entities.Entity;

public class EntityList extends Observable {
	
	private List<Entity> entities;
	
	public EntityList(List<Entity> entities) {
		this.entities = entities;
	}
	
	public EntityList() {
		
	}
	
	public void add(Entity object) {
		entities.add(object);
		setChanged();
		notifyObservers(getInfo());
	}
	
	public List<Entity> getList() {
		return entities;
	}
	
	public List<Map<String, String>> getInfo() {
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		for (Entity entity : entities) {
			info.add(entity.getInfo());
		}
		return info;
	}

}
