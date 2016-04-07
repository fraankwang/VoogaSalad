/**
 * 
 * @author mario_oliver93
 *
 */
package gamecontroller;

import backend.FrontEndGameAuthoringEnvironment;
import backend.FrontEndAccessController;
import backend.GameAuthoringEnvironment;
import backend.GameObject;
import backend.systems.SystemsController;
import exception.DrumpfTowerException;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MasterController {

	private static final int SIZE = 600;
	private GameAuthoringEnvironment bae;
	private GameObject trumpGame;
	private SystemsController systems;
	private Group myRoot;
	private FrontEndAccessController displayController;

	public MasterController() {	
		myRoot = new Group();
		displayController = new FrontEndAccessController(myRoot);
		systems = new SystemsController(displayController);
		bae = new GameAuthoringEnvironment();
		
		// creates a game object
		trumpGame = bae.setGameObjectWithMockData(new FrontEndGameAuthoringEnvironment());
	}

	public Scene init(Stage primaryStage) {
		Scene myScene = new Scene(myRoot, SIZE, SIZE, Color.WHITE);
		return myScene;
	}

	/**
	 * Create game loop
	 * 
	 * @param numFramesPerSecond
	 * @return
	 */
	public KeyFrame startGameLoop(int numFramesPerSecond) {
		return new KeyFrame(Duration.millis(1000 / numFramesPerSecond), e -> step(trumpGame));
	}

	public void step(GameObject game) {
		systems.iterateThroughSystems(game);
	}

}
