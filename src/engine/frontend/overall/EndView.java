//This entire file is part of my masterpiece
//Hayden Bader
package engine.frontend.overall;

import engine.controller.EngineController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class EndView extends AbstractVBoxSplash  {

	private Button restartButton;

	public EndView(EngineController ec) {
		super(ec);
	}

	/**
	 * Instantiates scene for ending screen
	 * 
	 * @return
	 */
	public Scene buildScene() {

		super.buildVBoxScene();
		
		Region myRegion = myController.setupHUD();
		bindWidth(myRegion, myVBox.widthProperty());
		bindHeight(myRegion, myVBox.heightProperty().divide(2));
		myVBox.getChildren().add(buildRestartButton());
		myVBox.getChildren().add(myRegion);

		return myScene;
	}

	private Node buildRestartButton() {
		restartButton = new Button("RESTART");
		// startButton.setDisable(true);

		restartButton.setOnAction(e -> restart());

		bindHeight(restartButton, myScene.heightProperty().divide(4));
		bindWidth(restartButton, myScene.widthProperty());

		return restartButton;
	}

	
	private void restart() {
		myController.initStartView(false);
	}
}
