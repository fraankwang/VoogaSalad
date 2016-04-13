package engine.frontend;

import java.util.ResourceBundle;

import engine.controller.EngineController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;

public class EngineView{

	public static final String DEFAULT_UI_RESOURCE = "engine/resources/engine_window";
	private ResourceBundle myUIResources;
	private Stage myStage;
	
	private EngineController myController;
	private ToolbarManager myToolbarManager;
	private BoardPane myBoardPane;
	private ShopPane myShopPane;
	private StatusPane myStatusPane;
	
	public EngineView(Stage s, EngineController c){
		myStage = s;
		myController = c;
		
		myToolbarManager = new ToolbarManager(this);
		myBoardPane = new BoardPane(this);
		myShopPane = new ShopPane(this);
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
		return scene;
	}
	
	/**
	 * Fills the top Hbox with the BoardPane and the TowerPane
	 * @param vbox
	 */
	private void fillTopHBox(HBox hbox){
		Node board = myBoardPane.buildNode();
		Node tower = myShopPane.buildNode();
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
	
	public Stage getStage(){
		return myStage;
	}
	
	protected Main getMain(){
		return myController.getMain();
	}
	
	public BoardPane getBoardPane(){
		return myBoardPane;
	}
	
	public ShopPane getShopPane(){
		return myShopPane;
	}
	
	public StatusPane getStatusPane(){
		return myStatusPane;
	}
	
	protected int loadUIIntResource(String input){
		return Integer.parseInt(myUIResources.getString(input));
	}
	
	protected String loadUIStringResource(String input){
		return myUIResources.getString(input);
	}
	
}
