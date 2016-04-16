package engine.controller;

import engine.backend.FakeGAEBackend;
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
		BezierCurve tempCurve1 = new BezierCurve(0,0, 0,0, 0,0, 200,200);
		BezierCurve tempCurve2 = new BezierCurve(200,200, 50,50, 150,150, 0,300);
		BezierCurve tempCurve3 = new BezierCurve(0,300, 150, 150, 250, 250, 400,400);
		
//		tempPath.addCurve(tempCurve1);
//		tempPath.addCurve(tempCurve2);
//		tempPath.addCurve(tempCurve3);
		
		GameMap tempMap = new GameMap("", tempPath, 200, 200);
		
		IEntity tempEntity = new Entity(0, "tempEntity", "object", 20);
		IComponent tempPosition = new PositionComponent(0, 0);
		IComponent tempMovement = new MovementComponent(2, 0);
		IComponent pathComp = new PathComponent(0, 0);
		IComponent tempDisplay = new DisplayComponent("DrumpfVader.png");
		IComponent tempSize = new SizeComponent();
		tempEntity.addComponent(tempDisplay);
		tempEntity.addComponent(tempSize);
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
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
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
