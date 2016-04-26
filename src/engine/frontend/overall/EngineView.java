package engine.frontend.overall;

import java.util.ResourceBundle;

import engine.controller.EngineController;
import engine.frontend.board.BoardPane;
import engine.frontend.shop.ShopPane;
import engine.frontend.status.MenubarManager;
import engine.frontend.status.StatusPane;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
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
		myGameCapture = new GameCapture(this);
		
		myMenubarManager = new MenubarManager(this);
		myBoardPane = new BoardPane(this);
		myShopPane = new ShopPane(this);
		myStatusPane = new StatusPane(this);
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		myDummyCursor = new DummyCursor(this);
	}
	
	/**
	 * builds a Scene for the current view
	 * @return
	 */
	public Scene buildScene(){
		
		myBody = new BorderPane();
		myScene = new Scene(myBody, Color.WHITE);
		myMenuBar = myMenubarManager.buildMenuBar(myScene.widthProperty(), myScene.heightProperty().multiply(loadDoubleResource("MenuBarHeight")));
		myBody.setTop(myMenuBar);

		SimpleDoubleProperty mapHeight = new SimpleDoubleProperty(myController.getEventManager().getCurrentLevel().getMap().getMapHeight());
		SimpleDoubleProperty mapWidth = new SimpleDoubleProperty(myController.getEventManager().getCurrentLevel().getMap().getMapWidth());
		
		DoubleBinding scalingFactor;		
		if(mapHeight.get() > mapWidth.get()){
			DoubleBinding usableHeight = getUsableBoardHeight();
			scalingFactor = usableHeight.divide(mapHeight);
		} else {
			DoubleBinding usableWidth = getUsableBoardWidth();
			scalingFactor = usableWidth.divide(mapWidth);
		}
		DoubleExpression boardWidth = mapWidth.multiply(scalingFactor);
		DoubleExpression boardHeight = mapHeight.multiply(scalingFactor);
		myBody.setLeft(myBoardPane.buildNode(boardWidth, boardHeight));
		myBody.setRight(myShopPane.buildNode(myScene.widthProperty().subtract(boardWidth), boardHeight));
		myBody.setBottom(myStatusPane.buildNode(myScene.widthProperty(), myScene.heightProperty().subtract(boardHeight).subtract(myMenuBar.heightProperty())));
		
		myScene.setOnDragExited(e -> handleEndMouseRelease(e));
		myBody.getChildren().add(myDummyCursor.getNode());
		myScene.setCursor(Cursor.DEFAULT);
		myScene.setOnDragOver(e -> handleDrop(e));
		myScene.setOnDragDropped(e -> handleEndMouseRelease(e));
		//myScene.setOnDrag
		return myScene;
	}
	
	private void setupBindings(){
		
	}

	private void handleDrop(DragEvent e){
		e.acceptTransferModes(TransferMode.ANY);
		if (e.getGestureSource() != myScene &&  e.getDragboard().hasString()) {
			myDummyCursor.updateLocation(e.getSceneX(), e.getSceneY());
			//System.out.println(e.getSceneX());
        }	
		if( myScene.getCursor() != Cursor.NONE){
			myScene.setCursor(Cursor.NONE);
		}
		e.consume();
	}
	
	private void handleEndMouseRelease(DragEvent e) {

		if(e.getGestureSource() != myScene){
			if( isInBoardPane( e.getScreenX(), e.getScreenY() ) && e.getDragboard().hasString()){
				myBoardPane.attemptTower(e.getSceneX(), e.getSceneY(), e.getDragboard().getString());
			}
		}
		this.getStage().getScene().setCursor(Cursor.DEFAULT);
		myDummyCursor.changePic(null);

	}
	
	private boolean isInBoardPane(double x, double y){
		// board pane's node seems to be scaling as entities move outside the previous bounds
		boolean xInPane = x > myScene.getX() && x < getUsableBoardWidth().doubleValue();
		boolean yInPane = y > myMenuBar.heightProperty().doubleValue() && y < getUsableBoardHeight().doubleValue()+myMenuBar.heightProperty().doubleValue();
		return (xInPane && yInPane);
	}
	
	public DummyCursor getDummyCursor(){
		return myDummyCursor;
	}
	
	public DoubleBinding getUsableBoardWidth(){
		return myScene.widthProperty().multiply(loadDoubleResource("BoardMaxWidth"));
	}
	
	public DoubleBinding getUsableBoardHeight(){
		return myScene.heightProperty().multiply(loadDoubleResource("BoardMaxHeight"));
	}
	
	public DoubleBinding getUsableShopWidth(){
		return myScene.widthProperty().subtract(getUsableBoardWidth());	
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
