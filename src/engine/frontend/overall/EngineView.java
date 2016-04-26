package engine.frontend.overall;

import java.util.ResourceBundle;

import engine.controller.EngineController;

import engine.frontend.board.BoardPane;
import engine.frontend.shop.ShopPane;
import engine.frontend.status.MenubarManager;
import engine.frontend.status.StatusPane;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.MenuBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
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
		myDummyCursor = new DummyCursor(this);
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
		myBody.setRight(myShopPane.buildNode());
		myBody.setBottom(myStatusPane.buildNode());
		myScene.setOnDragExited(e -> handleEndMouseRelease(e));
		myBody.getChildren().add(myDummyCursor.getNode());
		myScene.setCursor(Cursor.DEFAULT);
		myScene.setOnDragOver(e -> handleDrop(e));
		return myScene;
	}

	private void handleDrop(DragEvent e){
		if (e.getGestureSource() != myScene &&  e.getDragboard().hasString()) {

			myDummyCursor.updateLocation(e.getSceneX(), e.getSceneY());
        }	
		
		if( myScene.getCursor() != Cursor.NONE){
			myScene.setCursor(Cursor.NONE);
		}
		
        e.consume();
	}
	
	private void handleEndMouseRelease(DragEvent e) {
		

		
		if( isInBoardPane( e.getSceneX(), e.getSceneY() ) && e.getDragboard().hasString()){
			myBoardPane.attemptTower(e.getSceneX(), e.getSceneY(), e.getDragboard().getString());
		}
		this.getStage().getScene().setCursor(Cursor.DEFAULT);

		/*if( isInScene(e.getSceneX(), e.getSceneY()){
			myDummyCursor.changePic(null);
		}
		*/
		
	}
	
	private boolean isInScene(double x, double y){
		return true;
	}
	
	private boolean isInBoardPane(double x, double y){
		// board pane's node seems to be scaling as entities move outside the previous bounds
		boolean xInPane = x > myScene.getX() && x < getUsableWidth(loadDoubleResource("BoardWidth")).doubleValue();
		boolean yInPane = y > myMenuBar.heightProperty().doubleValue() && y < getUsableHeight(loadDoubleResource("BoardHeight")).doubleValue()+myMenuBar.heightProperty().doubleValue();
		return (xInPane && yInPane);
	}
	
	public DummyCursor getDummyCursor(){
		return myDummyCursor;
	}
	
	public DoubleBinding getUsableWidth(double porportion){
		return myScene.widthProperty().multiply(porportion);
	}
	
	public DoubleBinding getUsableHeight(double porportion){
		return myScene.heightProperty().subtract(myMenuBar.heightProperty()).multiply(porportion);
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
