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
	private GameStatistics myGameStatistics;

	public GameWorld() {
		this.myGameStatistics = new GameStatistics();
		this.modes = new ArrayList<Mode>();
	}

	public GameStatistics getGameStatistics() {
		return myGameStatistics;
	}

	public void addMode(Mode mode) {
		modes.add(mode);
		myGameStatistics.incrementNumModes();
	}

	public Mode getModeWithName(String name){
		for (Mode mode : modes){
			if (mode.getName().equals(name)){
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
				if (level.getEntities().containsKey(id)) {
					return level.getEntities().get(id);
				}
			}
		}
		return null;
	}

	public void addNewLevelToCurrentMode(Mode mode) {
		Level level1 = new Level(0);
		mode.addLevel(level1);
		myGameStatistics.incrementNumLevels();
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

	public Map<String, Map<String, Entity>> getEntityMap(){
		return myEntityTypeMap;
	}

}
