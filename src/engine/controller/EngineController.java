package engine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.FakeGAEBackend;
import engine.backend.MockGAEData;
import engine.backend.components.IComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;
import engine.backend.systems.SystemsController;
import engine.frontend.EngineView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

public class EngineController {
	private Stage myStage;
	private Main myMain;
	
	private static final int NUM_FRAMES_PER_SECOND = 60;
	
	private FakeGAEBackend bae;
	private GameWorld myGameWorld;
	private SystemsController systems;
	
	private EngineView myEngineView;
	
	public EngineController(Stage s, Main m){
		myStage = s;
		myMain = m;
		
		//bae = new FakeGAEBackend(); //temporary for now
		
		//this is the major part, where the GameWorld and backend are created
		//myGameWorld = bae.createFakeGameObject(new MockGAEData()); //create fake game object
		String tempImage;
		myGameWorld = new GameWorld();
		Mode tempMode = new Mode("tempMode");
		Level tempLevel = new Level(0);
		Path tempPath = new Path();
		BezierCurve tempCurve = new BezierCurve(0,0, 0,0, 0,0, 100,0);
		tempPath.addCurve(tempCurve);
		GameMap tempMap = new GameMap("", tempPath, 200, 200);
		
		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
		IComponent tempPosition = new PositionComponent(0, 0);
		IComponent tempMovement = new MovementComponent(2, 0);
		IComponent pathComp = new PathComponent(0, 0);
		tempEntity.addComponent(tempPosition);
		tempEntity.addComponent(tempMovement);
		tempEntity.addComponent(pathComp);
		
		tempLevel.addToEntities(tempEntity);
		tempLevel.setMap(tempMap);
		tempMode.addLevel(tempLevel);
		myGameWorld.addMode(tempMode);
		
		systems = new SystemsController(this);
		
		myEngineView = new EngineView(myStage, this); 
        myStage.setScene(myEngineView.getScene());
        myStage.show();
        System.out.println("START");
	}
	
	public void start(){
		KeyFrame frame = new KeyFrame(Duration.millis(100 / NUM_FRAMES_PER_SECOND), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step() {
		PositionComponent pos = (PositionComponent) myGameWorld.getModes().get(0).getLevels().get(0).getEntities().get(0).getComponent("PositionComponent");
		//System.out.println(pos.getX());
		systems.iterateThroughSystems(myGameWorld);
		
	}
	
	//backend endpoint 
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height){
		myEngineView.getBoardPane().updateEntity(xCoord, yCoord, image, id, width, height);
	}
	
//	public void newShop(Shop shop){
//		myEngineView.getShopPane().updateShop(shop);
//	}
//	public void newStatistics(Statistics statistics){
//		myEngineView.getStatusPane().updateStatistics(statistics);
//	}
//	public void shopClicked(String name){
//		//call backend to say shop object clicked
//	}
//	public void statisticsClicked(String name){
//		//call backend to say stat object clicked
//	}
	
	public void attemptTower(double xLoc, double yLoc) {
		// TODO Auto-generated method stub
	}

	public void entityClicked(int myID) {
		// TODO Auto-generated method stub
	}
	
	public void deleteEntity(int id){
		myEngineView.getBoardPane().deleteEntity(id);
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
