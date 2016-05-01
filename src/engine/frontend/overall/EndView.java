package engine.frontend.overall;


import engine.controller.EngineController;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EndView {

	private Scene myScene;
	private EngineController myController;

	private Button restartButton;

	public EndView(EngineController ec) {
		myController = ec;
	}

	/**
	 * Instantiates scene for ending screen
	 * @return
	 */
	public Scene buildScene() {
		VBox myVBox = new VBox();
		myScene = new Scene(myVBox, Color.WHEAT);
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

	private void bindWidth(Region region, DoubleExpression db) {
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}

	private void bindHeight(Region region, DoubleExpression db) {
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}

	private void restart() {
		myController.initStartView(false);
	}
}
