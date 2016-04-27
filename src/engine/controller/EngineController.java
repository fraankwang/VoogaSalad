package engine.controller;

import java.util.List;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.ModeStatistics;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.frontend.overall.EngineView;
import engine.frontend.overall.StartView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import utility.GameCapture;

public class EngineController implements IEngineController{
	private Stage myStage;
	private Main myMain;

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private boolean playing;
	
	private EventManager myEventManager;
	private GameWorld myGameWorld;
	private SystemsController mySystems;
	private InGameEntityFactory myEntityFactory;
	
	//testing
	private testingClass myTestingClass;

	private EngineView myEngineView;
	private GameCapture myGameCapture;

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
		
		StartView myStartView = new StartView(this);
		myStage.setScene(myStartView.buildScene());
		myStage.show();
	}
	
	public void startGame(){
		myEntityFactory = new InGameEntityFactory(myGameWorld.getGameStatistics(),
				myEventManager.getCurrentLevel().getAuthoredEntities());
		myEventManager.setEntityFactory(myEntityFactory);
		mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
		
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
		myStage.setX(myEngineView.loadIntResource("StartX"));
		myStage.setY(myEngineView.loadIntResource("StartY"));
		myStage.setScene(myEngineView.buildScene()); 
		myStage.show();
		setupGameCapture();
	}
	
	private void setupGameCapture(){
		myGameCapture = new GameCapture( 
				myEngineView.loadIntResource("StartX"), 
				myEngineView.loadIntResource("StartY"),
				myEngineView.loadIntResource("StageMinWidth"),
				myEngineView.loadIntResource("StageMinHeight"));
		
		myStage.xProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureX(newValue.intValue());
			}
		});
		
		myStage.yProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureY(newValue.intValue());
			}
		});
		
		myStage.widthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureWidth(newValue.intValue());
			}
		});
		
		myStage.heightProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureHeight(newValue.intValue());
			}
		});
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
	
	public void updateShop(List<ShopItem> shoplist){
		myEngineView.getShopPane().updateShop(shoplist);
	}
//	public void updateStatistics(Statistics statistics){
//		myEngineView.getStatusPane().updateStatistics(statistics);
//	}
	
//	public void statisticsClicked(String name){
//		//call backend to say stat object clicked
//	}
	
	public void attemptTower(double xLoc, double yLoc, String type) {
		EntityDroppedEvent event = new EntityDroppedEvent(xLoc, yLoc, type);
		myEventManager.handleEntityDropEvent(event);
	}

	public void entityClicked(int myID) {
		EntityClickedEvent clickedEvent = new EntityClickedEvent(myID);
		myEventManager.handleClickEvent(clickedEvent);
	}
	
	public void levelIsOver(){
		
	}
	
	public void waveIsOver(){
		
	}
	
	public Main getMain(){
		return myMain;
	}
	
	public void setPlaying(boolean b){
		playing = b;
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
	public GameCapture getGameCapture(){
		return myGameCapture;
	}
	
	public EngineView getEngineView(){
		return myEngineView;
	}
}
