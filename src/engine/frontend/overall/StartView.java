package engine.frontend.overall;

/**
 * @author austinwu
 */
import java.io.File;

import engine.controller.EngineController;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class StartView {
	private Scene myScene;
	private EngineController myController;
	private boolean firsttime;
	private String selectedMode;
	private Integer selectedLevel;

	private VBox myVBox;
	private Button loadButton;
	private ComboBox<String> modeComboBox;
	private ComboBox<Integer> levelComboBox;
	private Button startButton;

	public StartView(EngineController ec, boolean f) {
		myController = ec;
		firsttime = f;
	}


	/**
	 * Initialize startPrompt scene
	 * @return Scene - containing view for beginning prompt
	 */
	public Scene buildScene() {
		myVBox = new VBox();
		myScene = new Scene(myVBox, Color.WHEAT);

		buildGameChooser();
		buildModePicker();
		buildLevelPicker();
		buildStartButton();

		myVBox.getChildren().addAll(loadButton, modeComboBox, levelComboBox, startButton);

		bindHeight(myVBox, myScene.heightProperty());
		bindWidth(myVBox, myScene.widthProperty());
		return myScene;
	}

	private void buildGameChooser() {
		if (firsttime) {
			loadButton = new Button("Load Game");
		} else {
			loadButton = new Button("Load Different Game");
		}
		loadButton.setOnAction(e -> {
			loadGamePressed();
		});
		bindHeight(loadButton, myScene.heightProperty().divide(4));
		bindWidth(loadButton, myScene.widthProperty());
	}

	private void loadGamePressed() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(myController.getStage());
		if (file != null) {
			myController.initGameWorld(file);
			modeComboBox.setDisable(false);
			modeComboBox.getItems().addAll(myController.getGameWorld().getModes().keySet());
		}
	}

	private void buildModePicker() {
		modeComboBox = new ComboBox<String>();
		modeComboBox.setPromptText("Select Mode");
		if (!firsttime) {
			modeComboBox.getItems().addAll(myController.getGameWorld().getModes().keySet());
		} else {
			modeComboBox.setDisable(true);
		}
		modeComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				levelComboBox.setDisable(false);
				selectedMode = t1;
				levelComboBox.getItems().addAll(myController.currentLevelsUnlocked(selectedMode));
			}
		});

		bindHeight(modeComboBox, myScene.heightProperty().divide(4));
		bindWidth(modeComboBox, myScene.widthProperty());

	}

	private void buildLevelPicker() {
		levelComboBox = new ComboBox<Integer>();
		levelComboBox.setPromptText("Select Level");
		levelComboBox.setDisable(true);

		levelComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue ov, Integer t, Integer t1) {
				selectedLevel = t1;
				startButton.setDisable(false);
			}
		});

		bindHeight(levelComboBox, myScene.heightProperty().divide(4));
		bindWidth(levelComboBox, myScene.widthProperty());
	}

	private void buildStartButton() {
		startButton = new Button("START");
		startButton.setDisable(true);

		startButton.setOnAction(e -> myController.startGame(selectedMode, selectedLevel, firsttime));

		bindHeight(startButton, myScene.heightProperty().divide(4));
		bindWidth(startButton, myScene.widthProperty());
	}

	/**
	 * Helps bind width between two Regions
	 * @param region - Region to bind
	 * @param db - double expression describing binding
	 */
	public void bindWidth(Region region, DoubleExpression db) {
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}

	/**
	 * Helps bind height between two Regions
	 * @param region - Region to bind
	 * @param db - double expression describing binding
	 */
	public void bindHeight(Region region, DoubleExpression db) {
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}
}
