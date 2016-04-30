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

	private Map<String, Mode> myModes;
	private String myGameType;
	private GameStatistics myGameStatistics;
	
	/**
	 * Authoring Environment Constructor
	 */
	public GameWorld(String gameType, GameStatistics gameStatistics, Map<String, Mode> modes) {
		this.myGameType = gameType;
		this.myGameStatistics = gameStatistics;
		this.myModes = modes;
	}
	
	/**
	 * Engine Environment Testing.
	 */
	public GameWorld() {
		this.myGameStatistics = new GameStatistics();
		this.myModes = new HashMap<String, Mode>();
	}

	public GameStatistics getGameStatistics() {
		return myGameStatistics;
	}
	
	public void setGameStatistics(GameStatistics gameStatistics) {
		this.myGameStatistics = gameStatistics;
	}
	
	public void setGameType(String gameType) {
		this.myGameType = gameType;
	}


	public String getGameType() {
		return myGameType;
	}
	
	public void addMode(Mode mode) {
		myModes.put(mode.getName(), mode);
	}

	public Level getLevelWithId(String modeString, int levelIndex){
		Mode mode = myModes.get(modeString);
		Level level = mode.getLevels().get(levelIndex);
		return level;
	}

	public void printWhatIHave() {
		System.out.println("I am game object " + this.toString() + " and I have been created");
		System.out.println("I have " + myModes.size() + " mode(s) and they are composed of " + myModes);
	}
	
	public Map<String, Mode> getModes(){
		return myModes;
	}

}
