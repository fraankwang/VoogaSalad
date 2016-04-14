/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;


public class Level {

	private final String myName;
	private GameMap map;
	private List<IEntity> entities;
	private Set<String> entityNames;
	
	public Level(String myName, GameMap map, Set<String> entityNames) {
		this.myName = myName;
		this.map = map;
		this.entities = new ArrayList<IEntity>();
		this.entityNames = entityNames;
	}

	public Level(String myName) {
		this.entities = new ArrayList<IEntity>();
		this.myName = myName;
	}
	
	public String getName(){
		return myName;
	}
	
	public List<IEntity> getEntities() {
		return entities;
	}
	
	public Set<String> getEntityNames() {
		return entityNames;
	}
	
	public GameMap getMap(){
		return map;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void addEntityName(String name) {
		entityNames.add(name);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Level) {
			Level temp = (Level) o;
			if (this.myName.equals(temp.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}
	
}
