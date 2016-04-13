/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameWorld {
	
	private Map<String, Map<String, Entity>> myEntityTypeMap; //maps types of entities to a map containing specific entity names of that type
	private List<Mode> modes;
	private GameStatistics gameStats;

	public GameWorld() {
		this.gameStats = new GameStatistics();
		this.modes = new ArrayList<Mode>();
	}

	public GameStatistics getGameStats() {
		return gameStats;
	}

	public void addMode(Mode mode) {
		modes.add(mode);
		gameStats.incrementNumModes();
	}
	
	public Mode getModeWithId(int modeId){
		for (Mode mode : modes){
			if (mode.getId() == modeId){
				return mode; //potential exception
			}
		}
		return null;
	}
	
	public Level getLevelWithId(int id){
		for (Mode mode: modes){
			for (Level level : mode.getLevels()){
				if (level.getId() == id){
					return level;
				}
			}
		}
		return null;
	}
	
	public IEntity getEntityWithId(int id){
		for (Mode mode : modes){
			for(Level level : mode.getLevels()){
				for (IEntity entity : level.getEntities()){
					if(((Entity)entity).getID() == id){
						return entity;
					}
				}
			}
		}
		return null;
	}
	
	public void addNewLevelToCurrentMode(Mode mode) {
		Level level1 = new Level(0);
		mode.addLevel(level1);
		gameStats.incrementNumLevels();
	}

	/**
	 * Returns all possible types of modes
	 * 
	 * @return List<Modes>
	 */
	public List<Mode> getModes() {
		return modes;
	}

	public void printWhatIHave() {
		System.out.println("I am game object " + this.toString() + " and I have been created");
		System.out.println("I have " + modes.size() + " mode(s) and they are composed of " + modes.get(0).toString());
	}
	
	public void setEntityMap(Map<String, Map<String, Entity>> map){
		this.myEntityTypeMap = map;
	}

}
