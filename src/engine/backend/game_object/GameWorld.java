/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */
package engine.backend.game_object;


import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GameWorld {

	//myModes is a map of mode name to Mode object
	private Map<String, Mode> myModes;

	private String myName;
	private GameStatistics myGameStatistics;

	
	/**
	 * Authoring Environment Constructor
	 */

	public GameWorld(String name, Map<String, Mode> modes) {
		this.myName = name;
		this.myModes = modes;
	}
	
	/**
	 * Engine Environment Testing.
	 */
	public GameWorld() {
		this.myModes = new HashMap<String, Mode>();
	}

	public GameStatistics getGameStatistics(String mode) {
		return myModes.get(mode).getGameStatistics();
	}

	public void setGameName(String name) {
		this.myName = name;
	}
	
	public void setGameStatistics(GameStatistics gameStatistics) {
		this.myGameStatistics = gameStatistics;
	}

	public String getName() {
		return myName;
	}
	
	public void addMode(Mode mode) {
		myModes.put(mode.getName(), mode);
	}

	public Level getLevelWithId(String modeString, int levelIndex){
		Mode mode = myModes.get(modeString);
		Level level = mode.getLevels().get(levelIndex);
		return level;
	}
	
	//write the thing to set level here.
	/**
	 * Places a level in the map with the key being the level index.
	 * @param modeString
	 * @param levelIndex
	 * @param level
	 */
	public void putLevelInMap(String modeString, int levelIndex, Level level) {
		myModes.get(modeString).setLevelInMap(levelIndex, level);
	}

	public void printWhatIHave() {
		System.out.println("I am game object " + this.toString() + " and I have been created");
		System.out.println("I have " + myModes.size() + " mode(s) and they are composed of " + myModes);
	}
	
	public Map<String, Mode> getModes(){
		return myModes;
	}

}
