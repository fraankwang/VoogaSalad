package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private MasterController master;
	
	public Main() {
		master = new MasterController();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Simulation");
		Scene scene = master.init(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.show();

		// setup game's loop
		KeyFrame frame = master.startGameLoop(NUM_FRAMES_PER_SECOND);
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
