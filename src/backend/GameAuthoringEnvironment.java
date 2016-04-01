package backend;

import backend.game_object.entities.Entity;
import backend.game_object.entities.EntityFactoryClass;
import backend.systems.SystemsController;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author mario_oliver93
 *
 */
public class GameAuthoringEnvironment {

	SystemsController systems;
	EntityFactoryClass entityFactory;
	GameObject trumpGame;
	FrontEndGameAuthoringEnvironment frontMockData;

	public GameAuthoringEnvironment() {
		systems = new SystemsController();
		entityFactory = new EntityFactoryClass();
		frontMockData = new FrontEndGameAuthoringEnvironment();
		
	}

	public void addDrumpfImage(Group myRoot) {
		// adding arbitrary donald drumpf darth vader character
		ImageView myPlayer = new ImageView(new Image(getClass().getResourceAsStream("/DrumpfVader.png")));
		myPlayer.setFitWidth(80);
		myPlayer.setFitHeight(100);
		myPlayer.setX(200);
		myPlayer.setY(200);
		myRoot.getChildren().add(myPlayer);
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
		for (int eachSprite = 0; eachSprite < mockData.level1SpritesComponentWanted.length; eachSprite++) {
			Entity entity = entityFactory.makeEntity(trumpGame, mockData.level1SpritesComponentWanted[eachSprite]);
			// this is the line that assumes we are on mode one and level one
			trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
					.get(trumpGame.getGameStats().getCurrentLevel()).addToEntities(entity);
		}
		trumpGame.printWhatIHave();
		return trumpGame;
	}

}
