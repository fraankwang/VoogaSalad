/**
 * @author austinwu
 */
package engine.frontend.status;

import java.text.DecimalFormat;
import engine.controller.EngineController;
import engine.frontend.overall.EndView;
import engine.frontend.overall.ResourceUser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControlManager extends ResourceUser {
	private StatusPane myStatusPane;
	private static final String RESOURCE_NAME = "status";
	
	private double clockTime;
	private Timeline timerAnimation;
	
	private Button play;
	private Button nextWave;
	private Button nextLevel;
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
			myStatusPane.getEngineView().getEngineController().toggleStepping();
		});

		nextWave.setDisable(true);
		nextWave.setOnMouseClicked(e -> {
			myStatusPane.getEngineView().getEngineController().nextWaveClicked();
			resetNextWaveTimer();
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
	
	public void togglePlayButton(boolean playing){
		if (playing) {
			play.setText(loadStringResource("PauseLabel"));
		} else {
			play.setText(loadStringResource("PlayLabel"));
		}
	}
	
	/**
	 * Enables the nextwave button
	 */
	public void nextWaveEnable(double time) {
		nextWave.setDisable(false);
		startNextWaveTimer(time);
	}
	
	private void startNextWaveTimer(double time){
		clockTime = time;
		timerAnimation = new Timeline();
		timerAnimation.setCycleCount(Animation.INDEFINITE);
		KeyFrame frame = new KeyFrame(Duration.millis(100), e -> {
			if(clockTime > 0){
				DecimalFormat df = new DecimalFormat("#.##");
				nextWave.setText(loadStringResource("NextWaveTimerLabel") + df.format(clockTime));
				clockTime -= .1;
			} else {
				resetNextWaveTimer();
			}
		});
		timerAnimation.getKeyFrames().add(frame);
		timerAnimation.play();
	}
	
	private void resetNextWaveTimer(){
		timerAnimation.stop();
		nextWave.setText(loadStringResource("NextWaveLabel"));
		nextWave.setDisable(true);
	}

	public void nextLevelEnable() {
		nextLevel.setDisable(false);
		resetNextWaveTimer();
	}
}
