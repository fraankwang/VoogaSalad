package authoring.backend.game_objects;

import java.util.Map;

public class AuthoringGame {
	
	private Map<String, String> myInfo;
	private String myName;
	
	public AuthoringGame(String name) {
		this.myName = name;
	}
	
	public AuthoringGame() {
		
	}
	
	public void initializeInfo() {
		this.myInfo.put("Name", myName);
	}
	
	public void setName(String name) {
		this.myName = name;
		this.myInfo.put("Name", myName);
	}
	
	public String getName() {
		return myName;
	}
	
	public Map<String, String> getInfo() {
		return myInfo;
	}

}
