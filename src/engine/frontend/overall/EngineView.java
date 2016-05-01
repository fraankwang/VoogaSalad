package engine.frontend.overall;
/**
 * @author austinwu
 */

import engine.controller.EngineController;
import engine.frontend.board.BoardPane;
import engine.frontend.shop.ShopPane;
import engine.frontend.status.MenubarManager;
import engine.frontend.status.StatusPane;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import utility.gamecapture.GameCapture;

public class EngineView extends ResourceUser {

	/*
	 * reorganize/javadoc code LAST things that go off map go "under" the
	 * borders
	 */
	public static final String RESOURCE_NAME = "engine_window";

	private Stage myStage;
	private Scene myScene;

	private EngineController myController;
	private MenubarManager myMenubarManager;

	private BorderPane myBorderPane;
	private MenuBar myMenuBar;
	private BoardPane myBoardPane;
	private ShopPane myShopPane;
	private StatusPane myStatusPane;
	private DummyCursor myDummyCursor;

	private DoubleProperty scalingFactor;

	/**
	 * Initiates EngineView
	 * @param s - Stage engine view is initialized in
	 * @param c - engine controller that controls the various view components
	 */
	public EngineView(Stage s, EngineController c) {
		super(RESOURCE_NAME);
		myStage = s;
		myController = c;
		myMenubarManager = new MenubarManager(this);
		myBoardPane = new BoardPane(this);
		myShopPane = new ShopPane(this);
		myStatusPane = new StatusPane(this);

		myDummyCursor = new DummyCursor(this);
		scalingFactor = new SimpleDoubleProperty(1);
	}

	/**
	 * builds a Scene for the current view
	 * 
	 * @return
	 */
	public Scene buildScene() {
		myBorderPane = new BorderPane();
		myScene = new Scene(myBorderPane, Color.WHITE);
		myMenuBar = myMenubarManager.buildMenuBar();
		myBorderPane.setTop(myMenuBar);

		SimpleDoubleProperty mapHeight = new SimpleDoubleProperty(
				myController.getEventManager().getCurrentLevel().getMap().getMapHeight());
		SimpleDoubleProperty mapWidth = new SimpleDoubleProperty(
				myController.getEventManager().getCurrentLevel().getMap().getMapWidth());

		scalingFactor
				.bind(Bindings.min(getUsableBoardHeight().divide(mapHeight), getUsableBoardWidth().divide(mapWidth)));
		DoubleExpression boardWidth = mapWidth.multiply(scalingFactor);
		DoubleExpression boardHeight = mapHeight.multiply(scalingFactor);
		myBorderPane.setLeft(myBoardPane.buildNode(boardWidth, boardHeight));
		myBorderPane.setRight(myShopPane.buildNode(myScene.widthProperty().subtract(boardWidth), boardHeight));
		myBorderPane.setBottom(myStatusPane.buildNode(myScene.widthProperty(),
				myScene.heightProperty().subtract(boardHeight).subtract(myMenuBar.heightProperty())));

		myBorderPane.getChildren().add(myDummyCursor.buildNode());
		myScene.setCursor(Cursor.DEFAULT);
		myScene.setOnDragOver(e -> handleMove(e));
		myScene.setOnDragDropped(e -> handleEndMouseRelease(e));
		myScene.setOnKeyPressed(e -> handleKeyPress(e));
		return myScene;
	}

	/**
	 * Handles key event to pass to backend
	 * @param e - KeyEvent
	 */
	private void handleKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		myController.keyPressed(e.getCode().toString());
		e.consume();
	}

	/**
	 * Handles cursor moving across boardPane to have drag and drop cursor follow correctly
	 * @param e - DragEvent created by dragging a tower onto the screen
	 * Handles the changes in mouse position while the mouse is pressed, allows 
	 * Handles the changes in mouse position while the mouse is pressed, allows
	 * the image to track the cursor
	 */
	private void handleMove(DragEvent e) {
		e.acceptTransferModes(TransferMode.ANY);
		if (e.getGestureSource() != myScene && e.getDragboard().hasString()) {
			myDummyCursor.updateLocation(e.getSceneX(), e.getSceneY());
		}
		if (myScene.getCursor() != Cursor.NONE) {
			myScene.setCursor(Cursor.NONE);
		}
	}

	/**
	 * Called a dragged shop item is dropped on the scene
	 * 
	 * @param e
	 */
	private void handleEndMouseRelease(DragEvent e) {
		if (e.getGestureSource() != myScene) {
			if(isInBoardPane(e.getX(), e.getY()) && e.getDragboard().hasString()) {
				if(!e.getDragboard().getString().contains("SpeedPowerUp") && !e.getDragboard().getString().contains("RangePowerUp")){
					myBoardPane.attemptTower(e.getX(), e.getY(), e.getDragboard().getString().split("&")[0], Double.parseDouble(e.getDragboard().getString().split("&")[1]));
				}			
			}
		}
		this.getStage().getScene().setCursor(Cursor.DEFAULT);
		myDummyCursor.changePic(null);

	}


	private boolean isInBoardPane(double x, double y) {
		boolean xInPane = x > myScene.getX() && x < getUsableBoardWidth().doubleValue();
		boolean yInPane = y > myMenuBar.heightProperty().doubleValue()
				&& y < getUsableBoardHeight().doubleValue() + myMenuBar.heightProperty().doubleValue();
		return (xInPane && yInPane);
	}

	/**
	 * Gets dummy cursor used to mimic drag and drop
	 * @return - returns cursor node
	 */
	public DummyCursor getDummyCursor() {
		return myDummyCursor;
	}

	/**
	 * Gets usable board width from property file
	 * @return 
	 */
	public DoubleExpression getUsableBoardWidth() {
		return myScene.widthProperty().multiply(loadDoubleResource("BoardMaxWidth"));
	}

	/**
	 * Gets usable board height from property file
	 * @return
	 */
	public DoubleExpression getUsableBoardHeight() {
		return myScene.heightProperty().subtract(myMenuBar.heightProperty())
				.multiply(loadDoubleResource("BoardMaxHeight"));
	}

	/**
	 * Gets usable shop width from property file
	 * @return
	 */
	public DoubleExpression getUsableShopWidth() {
		return myScene.widthProperty().subtract(getUsableBoardWidth());
	}

	/**
	 * Returns stage EngineView was instantiated on
	 * @return
	 */
	public Stage getStage() {
		return myStage;
	}

	/**
	 * 
	 * @return
	 */
	protected Main getMain() {
		return myController.getMain();
	}

	/**
	 * Returns boardPane portion of EngineView
	 * @return
	 */
	public BoardPane getBoardPane() {
		return myBoardPane;
	}

	/**
	 * Returns ShopPane portion of EngineView
	 * @return
	 */
	public ShopPane getShopPane() {
		return myShopPane;
	}

	/**
	 * Returns StatusPane portion of EngineView
	 * @return
	 */
	public StatusPane getStatusPane() {
		return myStatusPane;
	}

	/**
	 * Returns EngineController used to link EngineView with Engine
	 * @return
	 */
	public EngineController getEngineController() {
		return myController;
	}

	/**
	 * Returns game capture object contained in EngineView's scene
	 * @return
	 */
	public GameCapture getGameCapture() {
		return myController.getGameCapture();
	}

	/**
	 * Returns BorderPane contained in EngineView
	 * @return
	 */
	public BorderPane getBorderPane() {
		return myBorderPane;
	}

	/**
	 * Returns DoubleExpression representing scaling size of scene in relation to engine
	 * @return
	 */
	public DoubleExpression getScalingFactor() {
		return scalingFactor;
	}
}
