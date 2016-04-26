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
import java.util.Set;

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
	
	private final String myName;
	private Map<Integer, IEntity> entities;
	private Map<String, List<EntityAction>> myEventMap;
	private GameMap map;
	private double levelTimer;
	private double waveDelayTimer;
	private double timer;
	private int numWaves;
	private int currentWaveIndex;
	private List<Rule> ruleAgenda;
	
	/*
	 * Authoring use variables
	 * 
	 */
	private Set<String> entityNames;
	private Map<String, String> levelInfo;
	private List<IEntity> authoredEntities;
	
	public Level(String myName) {
		this.myName = myName;
		this.authoredEntities = new ArrayList<IEntity>();
		this.entities = new HashMap<Integer, IEntity>();
		this.myEventMap = new HashMap<String, List<EntityAction>>();
		ruleAgenda = new ArrayList<Rule>();
	}
	
	private void initializeInfo() {
		levelInfo.put("Type", "Level");
		levelInfo.put("Name", myName);
		levelInfo.put("MapBackgroundImage", map.getMapImage());
		levelInfo.put("MapWidth", map.getMapWidth() + "");
		levelInfo.put("MapHeight", map.getMapHeight() + "");
		levelInfo.put("Paths", map.getPathsInfo());
	}
	
	public String getName(){
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
	
	public void removeEntites(Collection<IEntity> entitiesToRemove){
		for(IEntity entity : entitiesToRemove){
			entities.remove(entity.getID());
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
	
	@Override
	public String toString() {
		return "Level [entities=" + entities + "] ";
	}
	
	/*
	 * Authoring Use Methods
	 * 
	 */
	
	public Level(String myName, GameMap map) {
		this.myName = myName;
		this.map = map;
		this.authoredEntities = new ArrayList<IEntity>();
		this.levelInfo = new HashMap<String, String>();
		initializeInfo();
	}	
	
	public List<IEntity> getAuthoredEntities() {
		return authoredEntities;
	}
	
	public Set<String> getEntityNames() {
		return entityNames;
	}
	
	private String stringEntityNames() {
		StringBuilder sb = new StringBuilder();
		for (String entity : entityNames) {
			sb.append(entity);
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public void setEntityNames(Set<String> entityNames) {
		this.entityNames = entityNames;
		this.levelInfo.put("EntityNames", stringEntityNames());
	}

	public Map<String, String> getInfo() {
		return levelInfo;
	}
	
	public void addAuthoredEntity(IEntity entity) {
		authoredEntities.add(entity);
	}
	
	public void addEntityName(String name) {
		entityNames.add(name);
	}
	
	public void setWaveDelayTimer(double timer) {
		this.waveDelayTimer = timer;
		this.levelInfo.put("WaveDelayTimer", waveDelayTimer + "");
	}
	
	public void setLevelTimer(double timer) {
		this.levelTimer = timer;
		this.levelInfo.put("LevelTimer", levelTimer + "");
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
	
	/**
	 * Setting the map for the game.
	 * 
	 * @param map
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}
	
}
