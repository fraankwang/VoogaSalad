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

	private void handleKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		myController.keyPressed(e.getCode().toString());
		e.consume();
	}

	/**
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

	public DummyCursor getDummyCursor() {
		return myDummyCursor;
	}

	public DoubleExpression getUsableBoardWidth() {
		return myScene.widthProperty().multiply(loadDoubleResource("BoardMaxWidth"));
	}

	public DoubleExpression getUsableBoardHeight() {
		return myScene.heightProperty().subtract(myMenuBar.heightProperty())
				.multiply(loadDoubleResource("BoardMaxHeight"));
	}

	public DoubleExpression getUsableShopWidth() {
		return myScene.widthProperty().subtract(getUsableBoardWidth());
	}

	public Stage getStage() {
		return myStage;
	}

	protected Main getMain() {
		return myController.getMain();
	}

	public BoardPane getBoardPane() {
		return myBoardPane;
	}

	public ShopPane getShopPane() {
		return myShopPane;
	}

	public StatusPane getStatusPane() {
		return myStatusPane;
	}

	public EngineController getEngineController() {
		return myController;
	}

	public GameCapture getGameCapture() {
		return myController.getGameCapture();
	}

	public BorderPane getBorderPane() {
		return myBorderPane;
	}

	public DoubleExpression getScalingFactor() {
		return scalingFactor;
	}
}
