package engine.frontend;

import java.util.ResourceBundle;

import engine.controller.EngineController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;


public class EngineView{

	/*
	 * TODO:
	 * Fix size of overall window
	 * Add menu bar DONE
	 * Fix aspect ratio of game player
	 * 
	 * Future Big Items:
	 * Dynamic window resizing- make EVERYTHING relative and in terms of ratios
	 * Resizing/rearranging Panes
	 * Fix rendering
	 * Add game recorder functionality as an add-on
	 * 
	 */
	public static final String DEFAULT_RESOURCE = "engine/resources/engine_window";
	private ResourceBundle myResources;
	private Stage myStage;
	
	private EngineController myController;
	private MenubarManager myMenubarManager;
	private BoardPane myBoardPane;
	private ShopPane myShopPane;
	private StatusPane myStatusPane;
	
	public EngineView(Stage s, EngineController c){
		myStage = s;
		myController = c;
		
		myMenubarManager = new MenubarManager(this);
		myBoardPane = new BoardPane(this);
		myShopPane = new ShopPane(this);
		myStatusPane = new StatusPane(this);
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
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
		MenuBar menubar = myMenubarManager.buildMenuBar();
		myBody.getChildren().addAll(menubar, top, bottom);
		
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

	public EngineController getEngineController(){
		return myController;
	}
	
	protected int loadUIIntResource(String input){
		return Integer.parseInt(myResources.getString(input));
	}
	
	protected String loadUIStringResource(String input){
		return myResources.getString(input);
	}
	
}
