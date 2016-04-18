package engine.frontend;

import java.util.ResourceBundle;

import engine.controller.EngineController;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
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
		int width = loadUIIntResource("WindowWidth");
		int height = loadUIIntResource("WindowHeight");
		
		BorderPane myBody = new BorderPane();
		MenuBar menubar = myMenubarManager.buildMenuBar();
		myBody.setTop(menubar);
		myBody.setLeft(myBoardPane.buildNode());
		myBody.setRight(myShopPane.buildNode());
		myBody.setBottom(myStatusPane.buildNode());
		Scene scene = new Scene(myBody, width, height, Color.WHITE);
		return scene;
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
