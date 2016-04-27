/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.Observable;

public class GameStatistics extends Observable{

	private double myResources;
	private double myScore;
	private double myHealth;
	private int myLives;
	private int myLivesForDefeat;
	private double gameTimer;
	
	private int currentMode = 0;
	private int currentLevel = 0;
	private int numOfLevels = 0;
	private int numOfModes = 0;
	private int nextAvailableID = 0;
	private int nextAvailableEntityID = 0;
	
	/**
	 * Authoring Environment Constructor
	 */
	public GameStatistics(int startLives, int defeatLives, double gameTimer, double resources) {
		this.myLives = startLives;
		this.myLivesForDefeat = defeatLives;
		this.gameTimer = gameTimer;
		this.myResources = resources;
	}
	
	/**
	 * Engine Testing Constructor
	 */
	public GameStatistics() {
		
	}
	
	public double getGameTimer() {
		return gameTimer;
	}
	
	public double getMyResources() {
		return myResources;
	}
	
	public double getMyScore() {
		return myScore;
	}
	
	public double getMyHealth() {
		return myHealth;
	}
	
	public int getmyLives() {
		return myLives;
	}
	
	public int getMyLivesForDefeat() {
		return myLivesForDefeat;
	}
	
	public void setStartMoney(double d) {
		this.myResources = d;
	}
	
	public void setStartLives(int lives) {
		this.myLives = lives;
	}
	
	public void setGameTimer(double gameTimer) {
		this.gameTimer = gameTimer;
	}
	
	public void setNumLivesDefeat(int numLivesDefeat) {
		this.myLivesForDefeat = numLivesDefeat;
	}

	public void nextLevel() {
		++currentLevel;
	}

	public int getCurrentMode() {
		return currentMode;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void incrementNumLevels() {
		++numOfLevels;
	}

	public void incrementNumModes() {
		++numOfModes;
	}

	public int nextAvailableID() {
		return nextAvailableID++;
	}
	
	public int nextEntityID(){
		return nextAvailableEntityID++;
	}

}
