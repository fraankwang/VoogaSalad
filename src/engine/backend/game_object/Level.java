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
import engine.backend.game_features.ShopItem;
import engine.backend.map.GameMap;
import engine.backend.rules.EntityAction;
import engine.backend.rules.Rule;

/**
 * 
 * @author 
 *
 */
public class Level {
	
	//put spawning entities in this map
	private Map<Integer, IEntity> entities;
	private Map<String, List<EntityAction>> myEventMap;
	private List<IEntity> authoredEntities;
	private List<ShopItem> myShopItems;
	private String myName;
	private GameMap map;
	private double waveDelayTimer;
	private int numWaves;
	private int currentWaveIndex;
	private List<Rule> ruleAgenda;
	private int index;
	
	/**
	 * Authoring Environment Constructor.
	 */
	public Level(String myName, GameMap myMap, double waveDelayTimer, List<IEntity> authoredEntities) {
		this.myName = myName;
		this.map = myMap;
		this.waveDelayTimer = waveDelayTimer;
		this.authoredEntities = authoredEntities;
	}
	
	/**
	 * Engine Testing Constructor.
	 */
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
	 * Engine Testing Method.
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	public void removeEntites(Collection<IEntity> entitiesToRemove){
		for(IEntity entity : entitiesToRemove){
			entities.remove(entity.getID());
		}
	}

	public IEntity getEntityWithID(int entityID) {
		return entities.get(entityID);
	}

	public int getNumWaves() {
		return numWaves;
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
	
	public double getWaveDelayTimer() {
		return waveDelayTimer;
	}
	
	public List<IEntity> getAuthoredEntities() {
		return authoredEntities;
	}
	
	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}

	public List<ShopItem> getShopItems() {
		return myShopItems;
	}

	public void setShopItems(List<ShopItem> myShopItems) {
		this.myShopItems = myShopItems;
	}

}
