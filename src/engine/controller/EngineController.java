package engine.controller;

/**
 * @author austinwu
 */
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import authoring.backend.Testing;
import backend.xml_converting.GameWorldToXMLWriter;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_features.HUDValueFinder;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.GameWorld;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.backend.systems.Events.GameEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.KeyPressedEntityEvent;
import engine.backend.systems.Events.NextWaveEvent;
import engine.backend.systems.Events.PowerUpDroppedEvent;
import engine.frontend.overall.EndView;
import engine.frontend.overall.EngineView;
import engine.frontend.overall.ResourceUser;
import engine.frontend.overall.StartView;
import engine.frontend.status.DrumpfHUDScreen;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import utility.gamecapture.GameCapture;
import utility.hud.AbstractHUDScreen;
import utility.hud.HUDController;

public class EngineController extends ResourceUser implements IEngineController {
	private Stage myStage;
	private Main myMain;
	private Timeline animation;
	private ExceptionLoader exceptionLoader;

	private static final String RESOURCE_NAME = "stage";
	private static final String INITGAME = "StartingGameEvent";

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private boolean stepping;

	private EventManager myEventManager;
	private GameWorld myGameWorld;
	private SystemsController mySystems;
	private InGameEntityFactory myEntityFactory;
	private Integer lastEntityClickedID;
	private EngineView myEngineView;
	private GameCapture myGameCapture;

	private testingClass myTestingClass;

	/**
	 * Instantiates engine controller
	 * 
	 * @param s
	 *            - initial stage
	 * @param m
	 *            - initial main
	 */
	public EngineController(Stage s, Main m) {
		super(RESOURCE_NAME);
		myStage = s;
		myMain = m;
		exceptionLoader = new ExceptionLoader();
	}

	/**
	 * Initializes the animation and starts the
	 */
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
		animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

		initStage();
		initStartView(true);
	}

	/**
	 * Initializes the stage size
	 */
	private void initStage() {
		myStage.setMinWidth(loadIntResource("StageMinWidth"));
		myStage.setMinHeight(loadIntResource("StageMinHeight"));
		myStage.setX(loadIntResource("StartX"));
		myStage.setY(loadIntResource("StartY"));
	}

	/**
	 * Initializes the Start Splash screen
	 * 
	 * @param firsttime
	 *            - boolean if this is the first time the splash screen has been
	 *            shown
	 */
	public void initStartView(boolean firsttime) {
		StartView myStartView = new StartView(this, firsttime);
		Scene scene = myStartView.buildScene();
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Initialize a game world from XML
	 * 
	 * @param file
	 *            - XML file holding java world
	 */
	public void initGameWorld(File file) {
		GameWorldToXMLWriter christine = new GameWorldToXMLWriter();
		try {
			myGameWorld = (GameWorld) christine.xMLToObject(christine.documentToString(file));
			myEventManager = new EventManager(this, myGameWorld);
		} catch (IOException e) {
			// TODO Auto-generated catch block bad xml file error once its
			// thrown
			e.printStackTrace();
		}
	}

	/**
	 * Starts a game after the intial scene sets the stage and
	 */
	public void startGame(String selectedMode, Integer selectedLevel, boolean firsttime) {
		try {
			GameEvent e = new GameEvent(selectedMode, selectedLevel);
			myEventManager.handleGameStartEvent(e);
			myEntityFactory = new InGameEntityFactory(myEventManager.getCurrentLevel().getAuthoredEntities());
			myEventManager.setEntityFactory(myEntityFactory);
			myEventManager.initializeRules();
			mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
			initEngineView(firsttime);
			manualRefresh();
		} catch (IOException e) {
			new DrumpfTowerException(exceptionLoader.getString(INITGAME));
		}
	}

	/**
	 * Creates the engineView, starts the game by playing the animation
	 */
	private void initEngineView(boolean firsttime) {
		if (firsttime) {
			myEngineView = new EngineView(myStage, this);
		}
		myStage.setScene(myEngineView.buildScene());
		myStage.show();
		setupGameCapture();
		toggleStepping(true);
	}

	/**
	 * Initializes the HUD
	 * 
	 * @return
	 */
	public Region setupHUD() {
		HUDController myHUD = new HUDController();
		myHUD.init(myEventManager.getCurrentGameStatistics(), new HUDValueFinder());
		AbstractHUDScreen myHUDScreen = myHUD.getView();
		return ((DrumpfHUDScreen) myHUDScreen).getBody();
	}

	/**
	 * Initializes the game capture
	 */
	private void setupGameCapture() {
		myGameCapture = new GameCapture(loadIntResource("StartX"), loadIntResource("StartY"),
				loadIntResource("StageMinWidth"), loadIntResource("StageMinHeight"));

		myStage.xProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureX(newValue.intValue());
			}
		});

		myStage.yProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureY(newValue.intValue());
			}
		});

		myStage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureWidth(newValue.intValue());
			}
		});

		myStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureHeight(newValue.intValue());
			}
		});
	}

	/**
	 * Makes engine systems iterate through actions dictating the game
	 */
	public void step() {
		if (stepping) {
			mySystems.iterateThroughSystems(myEventManager.getCurrentLevel(), true);
		}
	}

	/**
	 * Updates displayed entities in boardPane when called by engine
	 * 
	 * @param xCoord
	 *            - x position image should be moved to
	 * @param yCoord
	 *            - y position image should be moved to
	 * @param image
	 *            - Image string location
	 * @param id
	 *            - unique ID of image to be displayed
	 * @param width
	 *            - width of image to be displayed
	 * @param height
	 *            - height of image to be displayed
	 */
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height,
			boolean show) {
		myEngineView.getBoardPane().updateEntity(xCoord, yCoord, image, id, width, height, show);
	}

	/**
	 * Updates displayed shopObjects in ShopPane when called by engine
	 */
	public void updateShop(List<ShopItem> shoplist) {
		myEngineView.getShopPane().updateShop(shoplist);
	}

	/**
	 * Updates displayed upgradeObjects in ShopPane when called by engine
	 * 
	 * @param upgradelist
	 *            - list of upgrades to be updated
	 */
	public void updateUpgrades(List<ShopItem> upgradelist) {
		myEngineView.getShopPane().updateUpgrades(upgradelist);
	}

	/**
	 * Wrapper method to connect engine with boardPane's attemptTower event
	 * 
	 * @param xLoc
	 *            - int - x location of tower to be added
	 * @param yLoc
	 *            - int - y location of tower to be added
	 * @param type
	 *            - String - type of tower to be added
	 */
	public void attemptTower(double xLoc, double yLoc, String type, double cost) {

		EntityDroppedEvent event = new EntityDroppedEvent(xLoc / myEngineView.getScalingFactor().doubleValue(),
				yLoc / myEngineView.getScalingFactor().doubleValue(), type, cost);
		mySystems.sendUserInputEvent(event);
		if (!stepping) {
			manualRefresh();
		}
	}

	/**
	 * Wrapper method to connect engine with Entity's attempt to upgrade
	 * 
	 * @param id
	 *            - unique ID of entity to be upgrade
	 * @param type
	 *            - type of upgrade to attempt
	 */
	public void attemptUpgrade(int id, String type) {
		PowerUpDroppedEvent event = new PowerUpDroppedEvent(type, id);
		mySystems.sendUserInputEvent(event);
		System.out.println("My ID: " + id + "Upgrade type: " + type);
	}

	/**
	 * Passes pressed keys to engine
	 * 
	 * @param s
	 *            - String enum representing keyPressed event
	 */
	public void keyPressed(String s) {
		if (lastEntityClickedID != null) {

			IEvent keyPressedEvent = new KeyPressedEntityEvent(lastEntityClickedID, s);
			mySystems.sendUserInputEvent(keyPressedEvent);
		}
	}

	/**
	 * Tells backend that an entity has been clicked
	 * 
	 * @param myID
	 *            - unique ID of entity clicked
	 */
	// methods we call to the backend:
	public void entityClicked(int myID) {
		lastEntityClickedID = myID;
		IEvent clickedEvent = new EntityClickedEvent(myID, myEngineView.getShopPane().getCurrentView());
		mySystems.sendUserInputEvent(clickedEvent);

	}

	/**
	 * Tells engine that the next wave button was clicked
	 */
	public void nextWaveClicked() {
		System.out.println("CALLING NEXT WAVE EVENT");
		NextWaveEvent nextWaveEvent = new NextWaveEvent();
		myEventManager.handleNextWaveEvent(nextWaveEvent);
	}

	/**
	 * Tells engine that the next level button was clicked
	 */
	public void nextLevelClicked() {
		myEventManager.handleGoToNextLevelEvent();
		initEngineView(false);
	}

	/**
	 * Returns a list of the current levels that are unlocked for a given mode
	 * 
	 * @param mode
	 * @return
	 */
	public List<Integer> currentLevelsUnlocked(String mode) {

		List<Integer> list = new ArrayList<Integer>();
		for (Integer i : myGameWorld.getModes().get(mode).getLevels().keySet()) {
			if (i <= myEventManager.getCurrentGameStatistics().getHighestLevelUnlocked()) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * Switches the mode
	 */
	public void switchModeClicked() {
		toggleStepping(false);
		initStartView(false);
	}

	/**
	 * Engine notifying view that a wave is over and next wave button can be
	 * enabled
	 */
	// methods the backend will call:
	public void waveIsOver(double delaytime) {
		myEngineView.getStatusPane().getControlManager().nextWaveEnable(delaytime);
	}

	/**
	 * Engine notifying view that a level has been won
	 */
	public void levelIsWon() {
		toggleStepping(false);

		myEngineView.getStatusPane().getControlManager().nextLevelEnable();
	}

	/**
	 * Engine notifying view that a level has been lost
	 */
	public void levelIsLost() {
		toggleStepping(false);
		EndView myEndView = new EndView(this);
		this.getStage().setScene(myEndView.buildScene());
	}

	/**
	 * Manually refreshes the system for debugging
	 */
	public void manualRefresh() {
		mySystems.iterateThroughSystems(myEventManager.getCurrentLevel(), false);
	}

	/**
	 * Gets the main class used to instantiate the project
	 * 
	 * @return
	 */
	public Main getMain() {
		return myMain;
	}

	/**
	 * Sets boolean value for whether the game is being played
	 * 
	 * @param b
	 */
	public void setPlaying(boolean b) {
		stepping = b;
	}

	/**
	 * Stops systems from stepping and toggles play/pause button
	 * 
	 * @param shouldStep
	 */
	public void toggleStepping(boolean shouldStep) {
		stepping = shouldStep;
		myEngineView.getStatusPane().getControlManager().togglePlayButton(shouldStep);
	}

	/**
	 * grabs the backgroundImage to be used as a map in BoardPane
	 * 
	 * @return
	 */
	public String getBackgroundImageFile() {
		return myEventManager.getCurrentLevel().getMap().getMapImage();
	}

	/**
	 * gets GameWorld created by parsing initial XML file
	 * 
	 * @return
	 */
	public GameWorld getGameWorld() {
		return myGameWorld;
	}

	/**
	 * Returns internal EventManager component
	 * 
	 * @return
	 */
	public EventManager getEventManager() {
		return myEventManager;
	}

	/**
	 * Returns internal GameCapture component
	 * 
	 * @return
	 */
	public GameCapture getGameCapture() {
		return myGameCapture;
	}

	/**
	 * Returns internal EngineView component
	 * 
	 * @return
	 */
	public EngineView getEngineView() {
		return myEngineView;
	}

	/**
	 * Returns Stage created
	 * 
	 * @return
	 */
	public Stage getStage() {
		return myStage;
	}

}
