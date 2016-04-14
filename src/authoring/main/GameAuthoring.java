package authoring.main;

import javafx.stage.Stage;
import authoring.backend.GlobalData;
import authoring.controller.AuthoringController;
import authoring.frontend.AuthoringView;

/**
 * @author: Jonathan Ma, Frank
 */

public class GameAuthoring {
	
	public GameAuthoring(Stage stage) {
		GlobalData globaldata = new GlobalData();
		AuthoringController controller = new AuthoringController(globaldata);
		AuthoringView view = new AuthoringView(stage, globaldata);
		view.start();
	}
	
}
