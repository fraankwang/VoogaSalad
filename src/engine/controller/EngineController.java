package engine.controller;

import engine.backend.game_object.GameWorld;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.frontend.EngineView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import engine.backend.game_object.ModeStatistics;

public class EngineController implements IEngineController{
	private Stage myStage;
	private Main myMain;

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private boolean playing;
	
	private EventManager myEventManager;
	private GameWorld myGameWorld;
	private SystemsController mySystems;
	
	//testing
	private testingClass myTestingClass;

	private EngineView myEngineView;

	public EngineController(Stage s, Main m) {
		myStage = s;
		myMain = m;
		myEngineView = new EngineView(myStage, this); 
        myStage.setScene(myEngineView.buildScene());
        myStage.show();
        
	}
	
	public void start(){
		myGameWorld = new GameWorld();
		myTestingClass = new testingClass();
		myGameWorld = myTestingClass.testFiring();
		//initTestGame();
		
		ModeStatistics stats = new ModeStatistics(10, 10);
		myEventManager = new EventManager(this, myGameWorld, stats);
		mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
		playing = true;
		
		myEngineView = new EngineView(myStage, this);
		myStage.setScene(myEngineView.buildScene());
		myStage.show();
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public void step() {
		if(playing){
			mySystems.iterateThroughSystems(myEventManager.getCurrentLevel());			
		}

	}
	
	//backend endpoint 
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height, boolean show){
		myEngineView.getBoardPane().updateEntity(xCoord, yCoord, image, id, width, height, show);
	}
	
//	public void updateShop(Shop shop){
//		myEngineView.getShopPane().updateShop(shop);
//	}
//	public void updateStatistics(Statistics statistics){
//		myEngineView.getStatusPane().updateStatistics(statistics);
//	}
	public void shopClicked(String name){
		//call backend to say shop object clicked
	}
//	public void statisticsClicked(String name){
//		//call backend to say stat object clicked
//	}
	
	public void attemptTower(double xLoc, double yLoc) {
		// TODO Auto-generated method stub
	}

	public void entityClicked(int myID) {
		// TODO Auto-generated method stub
	}
	
	public Main getMain(){
		return myMain;
	}

	public String getBackgroundFile(){
		return "test";
//		return myGameWorld.someGetFileName();
	}

	public GameWorld getMyGameWorld(){
		return myGameWorld;
	}

}
