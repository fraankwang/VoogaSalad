package engine.frontend.overall;

import java.io.File;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import engine.controller.EngineController;

public class EndView {
	
	private Scene myScene;
	private EngineController myController;
	private String selectedMode;
	private Integer selectedLevel;

	private VBox myVBox;
	private Button loadButton;
	private ComboBox<String> modeComboBox;
	private ComboBox<Integer> levelComboBox;
	private Button restartButton;

	public EndView(EngineController ec) {
		myController = ec;
	}

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
		//startButton.setDisable(true);

		restartButton.setOnAction(e -> restart());

		bindHeight(restartButton, myScene.heightProperty().divide(4));
		bindWidth(restartButton, myScene.widthProperty());
		
		return restartButton;
	}

	public void bindWidth(Region region, DoubleExpression db) {
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}

	public void bindHeight(Region region, DoubleExpression db) {
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}
	
	private void restart(){
		myController.initStartView(false);
	}
}


