package simulation;

import backend.FrontEndGameAuthoringEnvironment;
import backend.GameAuthoringEnvironment;
import backend.GameObject;
import backend.systems.SystemsController;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * 
 * @author mario_oliver93
 *
 */
public class MasterController {

	private static final int SIZE = 600;
	private GameAuthoringEnvironment bae;
	private GameObject trumpGame;
	SystemsController systems;
	
	public MasterController() {
		systems = new SystemsController();
		bae = new GameAuthoringEnvironment();
		//creates a game object
		trumpGame = bae.setGameObjectWithFrontEndInfo(new FrontEndGameAuthoringEnvironment());
	}

	public Scene init(Stage primaryStage) {
		Group myRoot = new Group();
		bae.addDrumpfImage(myRoot);
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
	
	public void step(GameObject game){
		systems.iterateThroughSystems(game);
	}

}
