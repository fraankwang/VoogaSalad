/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.Observable;

public class GameStatistics extends Observable{

	private double myMoney;
	private double myScore;
	private double myHealth;
	private int myLives;
	private int myLivesForDefeat;
	private double gameTimer;
	private double myResources;
	
	private int currentMode = 0;
	private int currentLevel = 0;
	private int numOfLevels = 0;
	private int numOfModes = 0;
	private int nextAvailableID = 0;
	private int nextAvailableEntityID = 0;
	
	public GameStatistics() {
	}
	
	public void setStartMoney(double d) {
		this.myMoney = d;
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
