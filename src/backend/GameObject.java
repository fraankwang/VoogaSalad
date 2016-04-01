package backend;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

	private List<Mode> modes;
	private GameStatisticsObject gameStats;

	public GameObject() {
		this.gameStats = new GameStatisticsObject(this);
		this.modes = new ArrayList<Mode>();
	}

	public void initializeGameObject(int numOfMode, int numOfLevels) {
		int iteration = 0;
		while (iteration < numOfMode) {
			Mode mode1 = new Mode();
			addMode(mode1);
			for(int i = 0; iteration<numOfLevels; iteration++){
				addNewLevelToCurrentMode(mode1);
			}
			iteration++;
		}
	}
	
	public GameStatisticsObject getGameStats(){
		return gameStats;
	}

	public void addMode(Mode mode) {
		modes.add(mode);
		gameStats.incrementNumModes();
	}

	public void addNewLevelToCurrentMode(Mode mode) {
		Level level1 = new Level();
		mode.addToLevelsList(level1);
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

	public List<?> getLevelsForMode(Mode mode, int modeNum) {
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

}