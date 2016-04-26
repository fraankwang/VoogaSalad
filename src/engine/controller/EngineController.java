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
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.frontend.overall.EngineView;
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

	private static final int NUM_FRAMES_PER_SECOND = 50;
	private boolean playing;
	
	private EventManager myEventManager;
	private GameWorld myGameWorld;
	private SystemsController mySystems;

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
		initTestGame();
		
		myEventManager = new EventManager(this, myGameWorld);
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
//	
//	public double scaleIncomingX(double xinput){
//		
//	}
//	
//	public double scaleIncomingY(double yinput){
//		
//	}
	
	public void scaleOutgoingYValue(){
		
	}
	
//	public void updateShop(Shop shop){
//		myEngineView.getShopPane().updateShop(shop);
//	}
//	public void updateStatistics(Statistics statistics){
//		myEngineView.getStatusPane().updateStatistics(statistics);
//	}
	public void shopClicked(String name){
		//call backend to say shop object clicked
		//if null is passed in, don't 
	}
	public void shopUnclicked(){
		//TODO
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

	public String getBackgroundImageFile(){
		return myEventManager.getCurrentLevel().getMap().getMapImage();
	}

	public GameWorld getGameWorld(){
		return myGameWorld;
	}
	
	public EventManager getEventManager(){
		return myEventManager;
	}
	
	private void initTestGame(){
		myGameWorld = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		Level tempLevel = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve1 = new BezierCurve(0,0, 0,0, 0,0, 200,200);
		BezierCurve tempCurve2 = new BezierCurve(200,200, 50,50, 150,150, 0,300);
		BezierCurve tempCurve3 = new BezierCurve(0,300, 150, 150, 250, 250, 400,400);
		
		tempPath.addCurve(tempCurve1);
		tempPath.addCurve(tempCurve2);
		tempPath.addCurve(tempCurve3);
		
		GameMap tempMap = new GameMap("", tempPath, 800, 600);
		
		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
		IComponent tempPosition = new PositionComponent(0, 0);
		IComponent tempMovement = new MovementComponent(2, 0);
		//IComponent pathComp = new PathComponent(0, 0);
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize = new SizeComponent();
		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		//tempEntity.addComponent(pathComp);
		
		IEntity tempEntity2 = new Entity(1, "tempEntity2", "object2", 20);
		IComponent tempPosition2 = new PositionComponent(0, 0);
		IComponent tempMovement2 = new MovementComponent(4, 0);
		IComponent pathComp2 = new PathComponent(0, 0);
		IComponent tempDisplay2 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize2 = new SizeComponent();
		tempEntity2.addComponent(tempDisplay2);
		tempEntity2.addComponent(tempSize2);
		tempEntity2.addComponent(tempPosition2);
		tempEntity2.addComponent(tempMovement2);
		tempEntity2.addComponent(pathComp2);
		
		IEntity tempEntity3 = new Entity(2, "tempEntity3", "object3", 20);
		IComponent tempPosition3 = new PositionComponent(450, 450);
		IComponent tempDisplay3 = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize3 = new SizeComponent();
		tempEntity3.addComponent(tempDisplay3);
		tempEntity3.addComponent(tempSize3);
		tempEntity3.addComponent(tempPosition3);
		
		tempLevel.addToEntities(tempEntity);
		tempLevel.addToEntities(tempEntity2);
		tempLevel.addToEntities(tempEntity3);
		tempLevel.setMap(tempMap);
		tempMode.addLevel(tempLevel);
		myGameWorld.addMode(tempMode);
	}
}
