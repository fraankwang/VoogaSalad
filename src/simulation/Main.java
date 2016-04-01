package simulation;

import backend.FrontEndGameAuthorizationEnvironment;
import backend.GameAuthenticationEnvironment;
import backend.GameObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private static final int SIZE = 600;
	private GameAuthenticationEnvironment bae;
	
	public Main() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Simulation");
		bae = new GameAuthenticationEnvironment();
		Scene scene = bae.init(primaryStage, SIZE, SIZE);
		primaryStage.setScene(scene);
		primaryStage.show();

		bae.setGameObjectWithFrontEndInfo(new FrontEndGameAuthorizationEnvironment());
		
		// setup game's loop
		KeyFrame frame = bae.startGameLoop(NUM_FRAMES_PER_SECOND);
        Timeline animation = new Timeline();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
