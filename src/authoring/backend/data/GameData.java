package authoring.backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class GameData extends Observable {
	
	private Map<String, String> gameInfo;
	
	public GameData() {
		this.gameInfo = new HashMap<String, String>();
	}
	
	public void update(Map<String, String> data) {
		this.gameInfo = data;	
		setChanged();
		notifyObservers(getInfo());
	}
	
	public Map<String, String> getInfo() {
		return gameInfo;
	}
	
	public String getGameType() {
		return gameInfo.get("GameType");
	}
	
	public int getNumPlayers() {
		return Integer.parseInt(gameInfo.get("NumberOfPlayers"));
	}
	
	public int getStartLives() {
		return Integer.parseInt(gameInfo.get("NumberOfStartingLives"));
	}
	
	public int getNumLivesDefeat() {
		return Integer.parseInt(gameInfo.get("NumberOfLivesForDefeat"));
	}
	
	public double getGameTimer() {
		return Double.parseDouble(gameInfo.get("GameTimer"));
	}
	
	public double getStartResources() {
		return Double.parseDouble(gameInfo.get("StartingResources"));
	}
	
}
