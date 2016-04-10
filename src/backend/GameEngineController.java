/**
 * 
 * @author mario_oliver93
 *
 */
package backend;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GameEngineController {

	public GameEngineController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create the game's frame
	 */
	public KeyFrame start(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> step());
	}

	/**
	 * Our game loop
	 */
	public void step() {

	}

}
