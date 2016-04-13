package engine.controller;

import engine.backend.FakeGAEBackend;
import engine.backend.MockGAEData;
import engine.backend.game_object.GameWorld;
import engine.backend.systems.SystemsController;
import engine.frontend.EngineView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

public class EngineController {
	private Stage myStage;
	private Main myMain;
	
	private static final int NUM_FRAMES_PER_SECOND = 60;
	
	private FakeGAEBackend bae;
	private GameWorld myGame;
	private SystemsController systems;
	
	private EngineView myEngineView;
	
	public EngineController(Stage s, Main m){
		myStage = s;
		myMain = m;
		
		bae = new FakeGAEBackend(); 
		
		//this is the major part, where the backend and game are created
		myGame = bae.createFakeGameObject(new MockGAEData()); //create fake game object
		systems = new SystemsController(this);
		
		myEngineView = new EngineView(myStage, this); 
        myStage.setScene(myEngineView.getScene());
        myStage.show();
	}
	
	public void start(){
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step() {
		systems.iterateThroughSystems(myGame);
	}
	
	public void createCharacterImage(double xCoord, double yCoord, String image, double width, double height){
		myEngineView.getBoardPane().createCharacterImage(xCoord, yCoord, image, width, height);
	}
	
	public Main getMain(){
		return myMain;
	}
}
