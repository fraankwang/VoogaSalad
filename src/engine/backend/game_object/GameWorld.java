/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */
package engine.backend.game_object;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entities.IEntity;

public class GameWorld {

	private List<IEntity> authoredEntities;
	private Map<Integer, Mode> myModes;
	private String myGameType;
	private GameStatistics myGameStatistics;
	
	public GameWorld() {
		this.myGameStatistics = new GameStatistics();
		this.myModes = new HashMap<Integer, Mode>();
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
		myModes.put(mode.getIndex(), mode);
		myGameStatistics.incrementNumModes();
	}

	public Level getLevelWithId(int modeIndex, int levelIndex){
		Mode mode = myModes.get(modeIndex);
		Level level = mode.getLevels().get(levelIndex);
		return level;
	}

	public void printWhatIHave() {
		System.out.println("I am game object " + this.toString() + " and I have been created");
		System.out.println("I have " + myModes.size() + " mode(s) and they are composed of " + myModes);
	}

	public List<IEntity> getAuthoredEntities() {
		return  authoredEntities;
	}
	
	public void setAuthoredEntities(List<IEntity> entities) {
		authoredEntities = entities;
	}

}
