/**
 * 
 * @author mario_oliver93
 *
 */
package engine.controller;

import engine.backend.FakeGAEBackend;
import engine.backend.MockGAEData;
import engine.backend.game_object.GameWorld;
import engine.backend.systems.SystemsController;
import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.util.Duration;

public class EngineBackendController {

	private static final int SIZE = 600;
	private FakeGAEBackend bae;
	private GameWorld trumpGame;
	private SystemsController systems;
	private Engine2PlayerController displayController;

	public EngineBackendController(Engine2PlayerController displayController) {	
		systems = new SystemsController(displayController);
		bae = new FakeGAEBackend();
		
		// creates a game object
		trumpGame = bae.createFakeGameObject(new MockGAEData());
	}

	public void setEventHandler(Scene myScene){
		myScene.setOnKeyPressed(e->displayController.handleEvent(e));
		myScene.setOnMouseClicked(e->displayController.handleEvent(e));
		myScene.setOnMouseDragEntered(e->displayController.handleEvent(e));
		myScene.setOnMouseDragEntered(e->displayController.handleEvent(e));
	}

	public void step(GameWorld game) {
		systems.iterateThroughSystems(game);
	}
	
	/**
	 * Create game loop
	 * 
	 * @param numFramesPerSecond
	 * @return
	 */
	public KeyFrame startGameLoop(int numFramesPerSecond) {
		//will eventually need to pass a GameObject into the step function, but handling that within
		return new KeyFrame(Duration.millis(1000 / numFramesPerSecond), e -> step(trumpGame));
	}

}
