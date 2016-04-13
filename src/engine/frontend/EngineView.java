package engine.frontend;

import java.util.ResourceBundle;

import engine.controller.Engine2PlayerController;
import engine.controller.EngineBackendController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;

public class EngineView{

	public static final String DEFAULT_UI_RESOURCE = "engine/resources/engine_window";
	private ResourceBundle myUIResources;
	private Stage myStage;
	private Main myMain;
	private static final int NUM_FRAMES_PER_SECOND = 60;
	
	private Engine2PlayerController displayController;
	private EngineBackendController backendEngineController;
	private ToolbarManager myToolbarManager;
	private BoardPane myBoardPane;
	private TowerPane myTowerPane;
	private StatusPane myStatusPane;
	
	public EngineView(Stage s, Main m){
		myStage = s;
		myMain = m;
		
		myToolbarManager = new ToolbarManager(this);
		myBoardPane = new BoardPane(this);
		myTowerPane = new TowerPane(this);
		myStatusPane = new StatusPane(this);
		myUIResources = ResourceBundle.getBundle(DEFAULT_UI_RESOURCE);
	}
	
	/**
	 * builds a "body HBox" for the current view
	 * @return
	 */
	public Scene getScene(){
		int padding = loadUIIntResource("OuterPadding");
		int width = loadUIIntResource("WindowWidth");
		int height = loadUIIntResource("WindowHeight");
		
		HBox top = new HBox(padding);
		HBox bottom = new HBox(padding);
		
		top.setPadding(new Insets(0, padding, 0, padding));
		bottom.setPadding(new Insets(0, padding, padding, padding));
		
		fillTopHBox(top);
		fillBottomHBox(bottom);
		
		VBox myBody = new VBox(padding);
		ToolBar toolbar = myToolbarManager.buildToolBar();
		myBody.getChildren().addAll(toolbar, top, bottom);
		
		Scene scene = new Scene(myBody, width, height, Color.WHITE);
		
		
		backendEngineController = new EngineBackendController(displayController);
		backendEngineController.setEventHandler(scene);
		
		// setup game's loop
		KeyFrame frame = backendEngineController.startGameLoop(NUM_FRAMES_PER_SECOND);
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
		return scene;
	}
	
	/**
	 * Fills the top Hbox with the BoardPane and the TowerPane
	 * @param vbox
	 */
	private void fillTopHBox(HBox hbox){
		Node board = myBoardPane.buildNode();
		displayController = new Engine2PlayerController((GridPane)board);
		Node tower = myTowerPane.buildNode();
		hbox.getChildren().addAll(board, tower);
	}
	
	/**
	 * Fills the bottom HBox with the statuspane
	 * @param vbox
	 */
	private void fillBottomHBox(HBox hbox){
		Node status = myStatusPane.buildNode();
		hbox.getChildren().addAll(status);
	}
	
	protected int loadUIIntResource(String input){
		return Integer.parseInt(myUIResources.getString(input));
	}
	
	/**
	 * Loads an int resource from the UI resource bundle and string key
	 * @param r resource bundle
	 * @param input String key
	 */
	protected String loadUIStringResource(String input){
		return myUIResources.getString(input);
	}
	
	public Stage getStage(){
		return myStage;
	}
	
	protected Main getMain(){
		return myMain;
	}
	
}
