/**
 * 
 * @author mario_oliver93
 *
 */
package gamecontroller;

import engine.backend.FrontEndAccessController;
import engine.backend.FrontEndGameAuthoringEnvironment;
import engine.backend.GameAuthoringEnvironment;
import engine.backend.GameWorld;
import engine.backend.systems.SystemsController;
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
	private GameWorld trumpGame;
	private SystemsController systems;
	private Group myRoot;
	private FrontEndAccessController displayController;

	public MasterController() {	
		myRoot = new Group();
		displayController = new FrontEndAccessController(myRoot);
		systems = new SystemsController(displayController);
		bae = new GameAuthoringEnvironment();
		bae.setBackendController(displayController);
		
		// creates a game object
		trumpGame = bae.setGameObjectWithMockData(new FrontEndGameAuthoringEnvironment());
	}

	public Scene init(Stage primaryStage) {
		Scene myScene = new Scene(myRoot, SIZE, SIZE, Color.WHITE);
		setEventHandler(myScene);
		return myScene;
	}
	
	public void setEventHandler(Scene myScene){
		myScene.setOnKeyPressed(e->displayController.handleEvent(e));
		myScene.setOnMouseClicked(e->displayController.handleEvent(e));
		myScene.setOnMouseDragEntered(e->displayController.handleEvent(e));
		myScene.setOnMouseDragEntered(e->displayController.handleEvent(e));
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

	public void step(GameWorld game) {
		systems.iterateThroughSystems(game);
	}

}
