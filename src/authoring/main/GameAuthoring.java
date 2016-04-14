package authoring.main;

import javafx.stage.Stage;
import authoring.controller.FrontAuthoringController;

/**
 * @author: Jonathan Ma, Frank
 */

public class GameAuthoring {
	
	public GameAuthoring(Stage stage) {
		FrontAuthoringController frontend = new FrontAuthoringController(stage);
		frontend.start();
	}
	
}
