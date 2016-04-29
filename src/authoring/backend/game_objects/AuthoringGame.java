package authoring.backend.game_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class AuthoringGame extends Observable {
	
	private Map<String, String> gameInfo;
	
	public AuthoringGame() {
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
	
	public int getStartLives() {
		return Integer.parseInt(gameInfo.get("NumberOfStartingLives"));
	}
	
	public double getStartResources() {
		return Double.parseDouble(gameInfo.get("StartingResources"));
	}

}
