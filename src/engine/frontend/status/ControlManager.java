/**
 * @author austinwu
 */
package engine.frontend.status;

import engine.controller.EngineController;
import engine.frontend.overall.EndView;
import engine.frontend.overall.ResourceUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ControlManager extends ResourceUser {
	private StatusPane myStatusPane;
	private static final String RESOURCE_NAME = "status";

	private Button play;
	private Button nextWave;
	private Button nextLevel;
	private ComboBox<String> modeComboBox;
	private Button modeButton;

	/**
	 * Instantiates Control Manager
	 * @param sp - status pane that the control manager will become a child of
	 */
	public ControlManager(StatusPane sp) {
		super(RESOURCE_NAME);
		myStatusPane = sp;
	}

	/**
	 * Instantiates the Game Control Buttons within a vbox
	 * @return VBox containing Game Control buttons
	 */
	public VBox buildGameControls() {
		VBox vbox = new VBox();

		play = myStatusPane.createButton(loadStringResource("PlayLabel"), vbox.heightProperty().divide(4), vbox.widthProperty());
		nextWave = myStatusPane.createButton(loadStringResource("NextWaveLabel"), vbox.heightProperty().divide(4), vbox.widthProperty());
		nextLevel = myStatusPane.createButton(loadStringResource("NextLevelLabel"), vbox.heightProperty().divide(4), vbox.widthProperty());
		modeButton = myStatusPane.createButton(loadStringResource("ModeTitleLabel"), vbox.heightProperty().divide(4), vbox.widthProperty());		
		play.setOnMouseClicked(e -> {
			if (play.getText().equals(loadStringResource("PlayLabel"))) {
				myStatusPane.getEngineView().getEngineController().setPlaying(true);
				play.setText(loadStringResource("PauseLabel"));
			} else {
				myStatusPane.getEngineView().getEngineController().setPlaying(false);
				play.setText(loadStringResource("PlayLabel"));
			}
		});

		nextWave.setDisable(true);
		nextWave.setOnMouseClicked(e -> {
			myStatusPane.getEngineView().getEngineController().nextWaveClicked();
			nextWave.setDisable(true);
		});

		nextLevel.setDisable(true);
		nextLevel.setOnMouseClicked(e -> {
			myStatusPane.getEngineView().getEngineController().nextLevelClicked();
			nextLevel.setDisable(true);
		});

		modeButton.setOnMouseClicked(e -> myStatusPane.getEngineView().getEngineController().switchModeClicked());

		vbox.getChildren().addAll(play, nextWave, nextLevel, modeButton);
		myStatusPane.bindWidth(vbox, myStatusPane.getPane().widthProperty().divide(4));
		myStatusPane.bindHeight(vbox, myStatusPane.getPane().heightProperty());
		return vbox;
	}

	
	/**
	 * Enables the nextwave button
	 */
	public void nextWaveEnable() {
		nextWave.setDisable(false);
	}


	/**
	 * Enables next level button
	 */
	public void nextLevelEnable() {
		nextWave.setDisable(false);

	}

	/**
	 * Disables mode selection
	 */
	public void switchModeEnable() {
		modeComboBox.setDisable(false);
	}
}
