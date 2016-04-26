/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */
package engine.backend.game_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entities.IEntity;

public class GameWorld {

	private Map<String, Map<String, IEntity>> myEntityTypeMap; //maps types of entities to a map containing specific entity names of that type
	private Map<Integer, Mode> myModes;
	private List<Mode> modes;
	private GameStatistics myGameStatistics;

	public GameWorld() {
		this.myGameStatistics = new GameStatistics();
		//this.modes = new ArrayList<Mode>();
		myModes = new HashMap<Integer, Mode>();
	}

	public GameStatistics getGameStatistics() {
		return myGameStatistics;
	}

	public void addMode(Mode mode) {
		myModes.put(mode.getIndex(), mode);
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

	public Level getLevelWithId(int modeIndex, int levelIndex){
		Mode mode = myModes.get(modeIndex);
		Level level = mode.getLevels().get(levelIndex);
		return level;
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

	public void setEntityMap(Map<String, Map<String, IEntity>> map){
		this.myEntityTypeMap = map;
	}

	public Map<String, Map<String, IEntity>> getEntityMap(){
		return myEntityTypeMap;
	}

}
