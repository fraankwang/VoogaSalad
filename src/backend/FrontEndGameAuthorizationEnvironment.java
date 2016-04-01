package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrontEndGameAuthorizationEnvironment {

	public int modesWanted;
	public int levelsWanted;
	public int entitiesWanted;
	public String[][] level1SpritesComponentWanted;
	
	public FrontEndGameAuthorizationEnvironment() {
		initMockData();
	}
	
	private void initMockData(){
		//pretend that bae clicks on these things that she wants in her game --> bae is picky
		modesWanted = 1;
		levelsWanted = 5;
		entitiesWanted = 2;
		//also pretend that she also wants a list of things she wants one sprite to be able to do
		// outside list represents level 1, inside list is components for one sprite
		String[][] mock = { {"Display"}, {"Display" } };
		level1SpritesComponentWanted = mock;
	}
	
}
