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

	private List<IEntity> authoredEntities;
	private Map<Integer, Mode> myModes;
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

	public Level getLevelWithId(int modeIndex, int levelIndex){
		Mode mode = myModes.get(modeIndex);
		Level level = mode.getLevels().get(levelIndex);
		return level;
	}

	public List<IEntity> getAuthoredEntities() {
		// TODO Auto-generated method stub
		return  authoredEntities;
	}
	
	public void setAuthoredEntities(List<IEntity> entities) {
		authoredEntities = entities;
	}

}
