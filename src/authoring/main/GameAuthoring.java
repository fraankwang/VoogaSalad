package authoring.main;

import authoring.backend.data.GlobalData;
import authoring.controller.AuthoringController;
import authoring.frontend.AuthoringView;
import javafx.stage.Stage;
import main.Main;

/**
 * @author: Jonathan Ma, Frank
 */

public class GameAuthoring {
	
	public GameAuthoring(Stage stage, Main main) {
		GlobalData globaldata = new GlobalData();
		AuthoringController controller = new AuthoringController(globaldata);
		AuthoringView view = new AuthoringView(stage, globaldata, main);
		view.start();
	}
	
}
