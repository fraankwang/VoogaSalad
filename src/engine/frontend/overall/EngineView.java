package engine.frontend.overall;

import java.util.ResourceBundle;

import engine.controller.EngineController;

import engine.frontend.board.BoardPane;
import engine.frontend.shop.ShopPane;
import engine.frontend.status.MenubarManager;
import engine.frontend.status.StatusPane;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.MenuBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import utility.GameCapture;


public class EngineView{

	/*
	 * TODO:
	 * Fix size of overall window- DONE
	 * Add menu bar- DONE
	 * Fix aspect ratio of game player- DONE
	 * Background Image- DONE
	 * Load in shop info
	 * Load in stat info
	 * General game state info- mode level etc.
	 * 
	 * Future Big Items:
	 * Dynamic window resizing- make EVERYTHING relative and in terms of ratios
	 * Resizing/rearranging Panes
	 * Add game recorder functionality as an add-on
	 * 
	 */
	public static final String DEFAULT_RESOURCE = "engine/resources/engine_window";
	private ResourceBundle myResources;
	private Stage myStage;
	private Scene myScene;
	
	private EngineController myController;
	private GameCapture myGameCapture;
	
	private MenubarManager myMenubarManager;
	
	private BorderPane myBody;
	private MenuBar myMenuBar;
	private BoardPane myBoardPane;
	private ShopPane myShopPane;
	private StatusPane myStatusPane;
	private DummyCursor myDummyCursor;
	
	
	public EngineView(Stage s, EngineController c){
		myStage = s;
		myController = c;
		//myGameCapture = new GameCapture(this);
		// game capture not working due to .classpath and jar libraries not quite set up
		
		myMenubarManager = new MenubarManager(this);
		myBoardPane = new BoardPane(this);
		myShopPane = new ShopPane(this);
		myStatusPane = new StatusPane(this);
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		myDummyCursor = new DummyCursor(loadDoubleResource("CursorWidth"), loadDoubleResource("CursorHeight"));
	}
	
	/**
	 * builds a "body HBox" for the current view
	 * @return
	 */
	public Scene buildScene(){
		
		myBody = new BorderPane();
		myScene = new Scene(myBody, Color.WHITE);
		myMenuBar = myMenubarManager.buildMenuBar();
		
		myBody.setTop(myMenuBar);
		myBody.setLeft(myBoardPane.buildNode());
		/*
		 * Boardpane is sized by the size of the map scaled down to what fits within the 75% of screen w/h
		 * Shoppane is sized by same height as boardpane and remaining width
		 * Status pane is sized by remaining width
		 */
		
		myBody.setRight(myShopPane.buildNode());
		myBody.setBottom(myStatusPane.buildNode());
		
		myScene.setOnMouseReleased(e -> handleEndMouseRelease(e));
		myBody.getChildren().add(myDummyCursor.getNode());
		myScene.setOnMouseDragged(e -> handleDummyCursor(e));
		
		return myScene;
	}

	private void handleDummyCursor(MouseEvent e){
		myDummyCursor.updateLocation(e.getSceneX(), e.getSceneY());
	}
	
	private void handleEndMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub
		if( myBody.getLeft().contains(e.getSceneX(), e.getSceneY())){
			myBoardPane.attemptTower(e.getSceneX(), e.getSceneY());
		}
		this.getStage().getScene().setCursor(Cursor.DEFAULT);
		myDummyCursor.changePic(null);
		this.getEngineController().shopUnclicked();
	}
	
	public DummyCursor getDummyCursor(){
		return myDummyCursor;
	}
	
	public DoubleBinding getUsableWidth(double porportion){
		return myScene.widthProperty().multiply(porportion);
	}
	
	public DoubleBinding getUsableHeight(double porportion){
		return myScene.heightProperty().multiply(porportion);
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
	
	public GameCapture getGameCapture(){
		return myGameCapture;
	}
	
	public BorderPane getBody(){
		return myBody;
	}
	
	public int loadIntResource(String input){
		return Integer.parseInt(myResources.getString(input));
	}
	
	public double loadDoubleResource(String input){
		return Double.parseDouble(myResources.getString(input));
	}
	
	protected String loadUIStringResource(String input){
		return myResources.getString(input);
	}
}
