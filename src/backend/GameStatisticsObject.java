package backend;

public class GameStatisticsObject {

	private GameObject myCurrGame;
	private int currentMode = 0;
	private int currentLevel = 0;
	private int numOfLevels = 0;
	private int numOfModes = 0;
	private int nextAvailableID = 0;
	
	public GameStatisticsObject(GameObject currGame) {
		this.myCurrGame = currGame;
	}
	
	public void nextLevel(){
		++currentLevel;
	}
	
	public int getCurrentMode(){
		return currentMode;
	}
	
	public int getCurrentLevel(){
		return currentLevel;
	}
	
	public void incrementNumLevels(){
		++numOfLevels;
	}
	
	public void incrementNumModes(){
		++numOfModes;
	}

	public int nextAvailableID() {
		return nextAvailableID++;
	}
	
}
