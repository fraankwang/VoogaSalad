
/**
 * 
 * @author mario_oliver93
 * 
 */
package backend;

import backend.game_object.entities.Entity;
import backend.game_object.entities.EntityFactoryClass;
import backend.rules.Action;
import backend.rules.Predicate;
import backend.rules.Rule;
import backend.systems.SystemsController;
//import exception.DrumpfTowerException;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameAuthoringEnvironment {

	private SystemsController systems;
	private EntityFactoryClass entityFactory;
	private GameObject trumpGame;
	private FrontEndGameAuthoringEnvironment frontMockData;

	public GameAuthoringEnvironment() {
		entityFactory = new EntityFactoryClass();
		frontMockData = new FrontEndGameAuthoringEnvironment();
		
	}

	public GameObject createGameObject() {
		return new GameObject();
	}

	public GameObject setGameObjectWithFrontEndInfo(FrontEndGameAuthoringEnvironment mockData) {
		trumpGame = new GameObject();
		trumpGame.initializeGameObject(mockData.modesWanted, mockData.levelsWanted);
		// can add extra layer iterating through each level so we add entity to
		// the right level
		// assuming we only want to add to level one right now
		Level currLevel = (trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
				.get(trumpGame.getGameStats().getCurrentLevel()));
		int tempx_y = 0;
//		frontendController.createCharacterImage(tempx_y, tempx_y, currLevel.getMap().getImage(), currLevel.getMap().getXSize(), currLevel.getMap().getYSize());
		for (int eachSprite = 0; eachSprite < mockData.level1SpritesComponentWanted.length; eachSprite++) {
			Entity entity;
			System.out.println(mockData.level1SpritesComponentWanted[eachSprite]);
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
