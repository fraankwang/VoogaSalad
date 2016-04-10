/**
 * 
 * @author mario_oliver93
 *
 */
package backend;

public class FrontEndGameAuthoringEnvironment {

	public int modesWanted;
	public int levelsWanted;
	public int entitiesWanted;
	public String[][] level1SpritesComponentWanted;

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
		String[][] mock = { { "Display", "Position" }, { "Display", "Position" } };
		level1SpritesComponentWanted = mock;
	}

}
