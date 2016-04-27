/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.game_object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entities.IEntity;
import engine.backend.map.GameMap;
import engine.backend.rules.EntityAction;
import engine.backend.rules.Rule;

/**
 * 
 * @author 
 *
 */
public class Level {
	
	private List<IEntity> authoredEntities;
	private Map<Integer, IEntity> entities;
	private Map<String, List<EntityAction>> myEventMap;
	private String myName;
	private int index;
	private GameMap map;
	private int numWaves;
	private int currentWaveIndex;
	private List<Rule> ruleAgenda;

	public Level(String name) {
		this.authoredEntities = new ArrayList<IEntity>();
		this.entities = new HashMap<Integer, IEntity>();
		this.myName = name;
		this.myEventMap = new HashMap<String, List<EntityAction>>();
		ruleAgenda = new ArrayList<Rule>();
	}

	/**
	 * 
	 * @return The unique identifier for the level.
	 */
	public String getName() {
		return myName;
	}
	
	public List<Rule> getRuleAgenda(){
		return ruleAgenda;
	}
	
	public void setRuleAgenda(List<Rule> rules){
		ruleAgenda = rules;
	}
	
	public void addActionToEventMap(String eventID, List<EntityAction> actions) {
		myEventMap.put(eventID, actions);
	}

	/**
	 * 
	 * @return The entities currently on the game screen.
	 */
	public Map<Integer, IEntity> getEntities() {
		return entities;
	}

	/**
	 * Adds an entity created to the map that stores the entities on the game
	 * screen.
	 * 
	 * @param entity
	 */
	public void addEntityToMap(IEntity entity) {
		entities.put(entity.getID(), entity);
	}

	/**
	 * Adds a collection of entities to the map that stores the entities on the
	 * game screen.
	 * 
	 * @param entities
	 */
	public void addEntityToMap(Collection<IEntity> entities) {
		entities.stream().forEach(e -> addEntityToMap(e));
	}


	/**
	 * 
	 * @return The instance of GameMap for the level.
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * Setting the map for the game.
	 * 
	 * @param map
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	
	public void removeEntites(Collection<IEntity> entitiesToRemove){
		for(IEntity entity : entitiesToRemove){
			entities.remove(entity.getID());
		}
	}

	public IEntity getEntityWithID(int entityID) {
		// TODO Auto-generated method stub
		return entities.get(entityID);
	}

	public int getNumWaves() {
		return numWaves;
	}

	public void setNumWaves(int numWaves) {
		this.numWaves = numWaves;
	}

	public int getCurrentWaveIndex() {
		return currentWaveIndex;
	}

	public void setCurrentWaveIndex(int currentWaveIndex) {
		this.currentWaveIndex = currentWaveIndex;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
