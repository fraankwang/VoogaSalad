package backend;

import java.util.ArrayList;
import java.util.List;

import backend.game_object.map.MapObject;
import java.util.Map;

public class FrontEndGameAuthoringEnvironment {

	public int modesWanted;
	public int levelsWanted;
	public int entitiesWanted;
	public List<MapObject> maps = new ArrayList<MapObject>();
	public String[][] level1SpritesComponentWanted;
	public String[][] myRules;

	public FrontEndGameAuthoringEnvironment() {
		initMockData();
	}

	private void initMockData() {
		// pretend that bae clicks on these things that she wants in her game
		// --> bae is picky
		modesWanted = 1;
		levelsWanted = 5;
		entitiesWanted = 2;
		// also pretend that she also wants a list of things she wants one
		// sprite to be able to do
		// outside list represents level 1, inside list is components for one sprite

//		String[][] mock = { { "Display", "Position", "Size"}, { "Display", "Position", "Size"} };
		String[][] mock = { { "Display", "Position", "Size"}};

		//String[][] mock = { { "Display", "Position", "Size", "Collision"}, { "Display", "Position", "Size", "Collision"} };

		// string rules
		String[][] rules = {{ "SizeComponent", "5", "increaseSize"}};//, {"Move", "5"}};
		myRules = rules;
		level1SpritesComponentWanted = mock;
	}
	
	private void addMap(){
		maps.add(new MapObject());
	}

}
