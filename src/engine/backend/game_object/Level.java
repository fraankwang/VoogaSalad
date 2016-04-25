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
	private int myID;
	private String myParentModeName;
	private GameMap map;
	private double timer;
	private int numWaves;
	private int currentWaveIndex;
	private List<Rule> ruleAgenda;

	public Level(int myID, GameMap map) {
		this.myID = myID;
		this.map = map;
	}

	public Level(int myID) {
		this.authoredEntities = new ArrayList<IEntity>();
		this.entities = new HashMap<Integer, IEntity>();
		this.myID = myID;
		this.myEventMap = new HashMap<String, List<EntityAction>>();
	}

	/**
	 * 
	 * @return The unique identifier for the level.
	 */
	public int getId() {
		return myID;
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

	/**
	 * Authoring environment adds entities that can be created during the
	 * entity.
	 * 
	 * @param entity
	 */
	public void addToEntities(IEntity entity) {
		entity.setLevelID(myID);
		authoredEntities.add(entity);
	}

	/**
	 * Setting the name of the mode containing this level.
	 * 
	 * @param modeID
	 */
	public void setParentModeName(String modeID) {
		this.myParentModeName = modeID;
	}

	/**
	 * 
	 * @return A String with the identifier of the mode with this level.
	 */
	public String getModeID() {
		return myParentModeName;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Level) {
			Level temp = (Level) o;
			if (this.myID == temp.myID) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return A map containing the events that can occur during this level.
	 */
	public Map<String, List<EntityAction>> getCustomEvents() {
		return myEventMap;
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

}
