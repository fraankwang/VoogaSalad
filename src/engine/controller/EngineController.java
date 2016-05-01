package engine.controller;

import java.io.File;
/**
 * @author austinwu
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import authoring.backend.Testing;
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
import engine.frontend.status.DrumpfHUDScreen;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private void initStage() {
		myStage.setMinWidth(loadIntResource("StageMinWidth"));
		myStage.setMinHeight(loadIntResource("StageMinHeight"));
		myStage.setX(loadIntResource("StartX"));
		myStage.setY(loadIntResource("StartY"));
	}

	public void initStartView(boolean firsttime) {
		myTestingClass = new testingClass();
		Testing test = new Testing();
		myGameWorld = test.test1();
		
        myEventManager = new EventManager(this, myGameWorld);
        startGame("mode1", 0);
				
//		StartView myStartView = new StartView(this, firsttime);
//		Scene scene = myStartView.buildScene();
//		myStage.setScene(scene);
//		myStage.show();
	}

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
	public void startGame(String selectedMode, Integer selectedLevel) {
		try {
			GameEvent e = new GameEvent(selectedMode, selectedLevel);
			myEventManager.handleGameStartEvent(e);
			myEntityFactory = new InGameEntityFactory(myEventManager.getCurrentLevel().getAuthoredEntities());
			myEventManager.setEntityFactory(myEntityFactory);
			myEventManager.initializeRules();
			mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
			initEngineView();
			manualRefresh();
		} catch (IOException e) {
			new DrumpfTowerException(exceptionLoader.getString(INITGAME));
		}
	}

	/**
	 * Creates the engineView, starts the game by playing the animation
	 */
	private void initEngineView() {
		myEngineView = new EngineView(myStage, this);
		myStage.setScene(myEngineView.buildScene());
		myStage.show();
		setupGameCapture();
		toggleStepping(false);
	}

	public Region setupHUD() {
		HUDController myHUD = new HUDController();
		myHUD.init(myEventManager.getCurrentGameStatistics(), new HUDValueFinder());
		AbstractHUDScreen myHUDScreen = myHUD.getView();
		return ((DrumpfHUDScreen) myHUDScreen).getBody();
	}

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

	public void step() {
		if (stepping) {
//			System.out.println("step");
			mySystems.iterateThroughSystems(myEventManager.getCurrentLevel(), true);
		}
	}

	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height,
			boolean show) {
		myEngineView.getBoardPane().updateEntity(xCoord, yCoord, image, id, width, height, show);
	}

	public void updateShop(List<ShopItem> shoplist) {
		myEngineView.getShopPane().updateShop(shoplist);
	}

	public void updateUpgrades(List<ShopItem> upgradelist) {
		myEngineView.getShopPane().updateUpgrades(upgradelist);
	}

	public void attemptTower(double xLoc, double yLoc, String type) {
		EntityDroppedEvent event = new EntityDroppedEvent(xLoc / myEngineView.getScalingFactor().doubleValue(),
				yLoc / myEngineView.getScalingFactor().doubleValue(), type);
		mySystems.sendUserInputEvent(event);
		System.out.println(event);
		if (!stepping) {
			manualRefresh();
		}
	}

	public void attemptUpgrade(int id, String type) {
		PowerUpDroppedEvent event = new PowerUpDroppedEvent(type, id);
		mySystems.sendUserInputEvent(event);
		System.out.println("My ID: " + id + "Upgrade type: " + type);
	}

	public void keyPressed(String s) {
		if (lastEntityClickedID != null) {
			IEvent keyPressedEvent = new KeyPressedEntityEvent(lastEntityClickedID, s);
			mySystems.sendUserInputEvent(keyPressedEvent);
		}
	}

	// methods we call to the backend:
	public void entityClicked(int myID) {
		lastEntityClickedID = myID;
		IEvent clickedEvent = new EntityClickedEvent(myID, myEngineView.getShopPane().getCurrentView());
		mySystems.sendUserInputEvent(clickedEvent);

	}

	public void nextWaveClicked() {
		System.out.println("CALLING NEXT WAVE EVENT");
		NextWaveEvent nextWaveEvent = new NextWaveEvent();
		myEventManager.handleNextWaveEvent(nextWaveEvent);
	}

	public void nextLevelClicked() {
		myEventManager.handleGoToNextLevelEvent();
		initEngineView();
	}

	public List<Integer> currentLevelsUnlocked(String mode) {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i : myGameWorld.getModes().get(mode).getLevels().keySet()) {
			if (i <= myEventManager.getCurrentGameStatistics().getHighestLevelUnlocked()) {
				list.add(i);
			}
		}
		return list;
	}

	public void switchModeClicked() {
		toggleStepping(false);
		initStartView(false);
	}

	// methods the backend will call:
	public void waveIsOver(double delaytime) {
		myEngineView.getStatusPane().getControlManager().nextWaveEnable(delaytime);
	}

	public void levelIsWon() {
		toggleStepping(false);
		myEngineView.getStatusPane().getControlManager().nextLevelEnable();
	}

	public void levelIsLost() {
		toggleStepping(false);
		EndView myEndView = new EndView(this);
		this.getStage().setScene(myEndView.buildScene());
	}

	public void manualRefresh() {
		mySystems.iterateThroughSystems(myEventManager.getCurrentLevel(), false);
	}

	public Main getMain() {
		return myMain;
	}

	public void toggleStepping() {
		toggleStepping(!stepping);
	}

	public void toggleStepping(boolean shouldStep) {
		stepping = shouldStep;
		myEngineView.getStatusPane().getControlManager().togglePlayButton(shouldStep);
	}

	public String getBackgroundImageFile() {
		return myEventManager.getCurrentLevel().getMap().getMapImage();
	}

	public GameWorld getGameWorld() {
		return myGameWorld;
	}

	public EventManager getEventManager() {
		return myEventManager;
	}

	public GameCapture getGameCapture() {
		return myGameCapture;
	}

	public EngineView getEngineView() {
		return myEngineView;
	}

	public Stage getStage() {
		return myStage;
	}

}
