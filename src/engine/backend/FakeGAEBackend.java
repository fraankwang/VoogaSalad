
/**
 * 
 * @author mario_oliver93
 * 
 */

package engine.backend;

import engine.backend.entities.Entity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.rules.Action;
import engine.backend.rules.Predicate;
import engine.backend.rules.Rule;
import engine.backend.systems.SystemsController;

public class FakeGAEBackend {

	private SystemsController systems;
	//private EntityFactoryClass entityFactory;
	private GameWorld trumpGame;
	private MockGAEData frontMockData;

	public FakeGAEBackend() {
		//entityFactory = new EntityFactoryClass();
		frontMockData = new MockGAEData();
		
	}

	public GameWorld createGameObject() {
		return new GameWorld();
	}

	public GameWorld createFakeGameObject(MockGAEData mockData) {
		trumpGame = new GameWorld();
		trumpGame.initializeGameObject(mockData.modesWanted, mockData.levelsWanted);
		// can add extra layer iterating through each level so we add entity to
		// the right level
		// assuming we only want to add to level one right now
		Level currLevel = (trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
				.get(trumpGame.getGameStats().getCurrentLevel()));
		int tempx_y = 0;
		for (int eachSprite = 0; eachSprite < mockData.level1SpritesComponentWanted.length; eachSprite++) {
			Entity entity;
//			System.out.println(mockData.level1SpritesComponentWanted[eachSprite]);
			entity = entityFactory.makeEntity(trumpGame, mockData.level1SpritesComponentWanted[eachSprite]);
			// this is the line that assumes we are on mode one and level one
			Rule myRule = new Rule(); myRule.addPredicate(new Predicate(mockData.myRules[eachSprite][0])); 
			myRule.setMyAction(new Action(mockData.myRules[eachSprite][1]));
			myRule.setMyMethodToCall(mockData.myRules[eachSprite][2]);
			entity.addRule(myRule);
			trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
					.get(trumpGame.getGameStats().getCurrentLevel()).addToEntities(entity);
		}
		trumpGame.printWhatIHave();
		return trumpGame;
	}

}
