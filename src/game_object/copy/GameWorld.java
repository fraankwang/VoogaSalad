/**
 * 
 * @author mario_oliver93
 *
 */
package game_object.copy;

import java.util.ArrayList;
import java.util.List;

import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;

public class GameWorld {

	private List<Mode> modes;
	private GameStatisticsObject gameStats;

	public GameWorld() {
		this.gameStats = new GameStatisticsObject();
		this.modes = new ArrayList<Mode>();
	}

	public void initializeGameObject(int numOfMode, int numOfLevels) {
		int iteration = 0;
		while (iteration < numOfMode) {
			Mode mode1 = new Mode();
			addMode(mode1);
			for (int i = 0; iteration < numOfLevels; iteration++) {
				addNewLevelToCurrentMode(mode1);
			}
			iteration++;
		}
	}

	public GameStatisticsObject getGameStats() {
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
		Level level1 = new Level();
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

	public List<Level> getLevelsForMode(Mode mode) {
		return mode.getLevels();
	}

	public void printWhatIHave() {
		System.out.println("I am game object " + this.toString() + " and I have been created");
		System.out.println("I have " + modes.size() + " mode(s) and they are composed of " + modes.get(0).toString());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void addMapImage(Level level) {
		// TODO Auto-generated method stub
		
	}

}
