package engine.controller;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.game_object.ModeStatistics;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.frontend.overall.EngineView;
//import engine.frontend.EngineView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

/*
 * Todos:
 * Scaling- for pixel amounts coming in from backend, only endpoint is EngineController.updateEntity, scale points
 * 		  - for pixel amounts going to backend, may be multiple endpoints, the various click events all need to be 
 * 		  backscaled before they go to the backend
 * 		Bind the width/height/x/y property of everything on the map to the current size
 * 
 *  Want to scale everything based on the size of the 
 */


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
	}
	
	public void start(){
		myGameWorld = new GameWorld();
		myTestingClass = new testingClass();
		myGameWorld = myTestingClass.testFiring();
		
		ModeStatistics stats = new ModeStatistics(10, 10);
		myEventManager = new EventManager(this, myGameWorld, stats);
		mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
		playing = true;
		
		myEngineView = new EngineView(myStage, this);
		buildStage();
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void buildStage(){
		myStage.setMinWidth(myEngineView.loadIntResource("StageMinWidth"));
		myStage.setMinHeight(myEngineView.loadIntResource("StageMinHeight"));
		myStage.setX(0);
		myStage.setY(0);
		
		Scene scene = myEngineView.buildScene();
		myStage.setScene(scene); 
		myStage.show();
	}
	
	public void step() {
		if(playing){
			mySystems.iterateThroughSystems(myEventManager.getCurrentLevel());			
		}

	}
	
	//backend endpoint 
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height, boolean show){
		myEngineView.getBoardPane().updateEntity(xCoord, yCoord, image, id, width, height, show);
		//these points need to be scaled based on the ratio of the size of the board to the size of the map
		//they need to be scaled dynamically
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
		EntityClickedEvent clickedEvent = new EntityClickedEvent(myID);
		myEventManager.handleClickEvent(clickedEvent);
	}
	
	public Main getMain(){
		return myMain;
	}

	public String getBackgroundImageFile(){
		return myEventManager.getCurrentLevel().getMap().getMapImage();
	}

	public GameWorld getGameWorld(){
		return myGameWorld;
	}
	
	public EventManager getEventManager(){
		return myEventManager;
	}
}
