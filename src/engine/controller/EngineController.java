package engine.controller;

import java.io.IOException;
import java.util.List;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_features.ShopItem;
import engine.backend.game_object.GameStatistics;
import engine.backend.game_object.GameWorld;
import engine.backend.systems.EventManager;
import engine.backend.systems.SystemsController;
import engine.backend.systems.Events.EntityClickedEvent;
import engine.backend.systems.Events.EntityDroppedEvent;
import engine.backend.systems.Events.GameEvent;
import engine.backend.systems.Events.NextWaveEvent;
import engine.frontend.overall.EngineView;
import engine.frontend.overall.ResourceUser;
import engine.frontend.overall.StartView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import utility.GameCapture;

public class EngineController extends ResourceUser implements IEngineController {
	private Stage myStage;
	private Main myMain;
	private Timeline animation;

	private static final String RESOURCE_NAME = "stage";

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private boolean playing;

	private EventManager myEventManager;
	private GameWorld myGameWorld;
	private SystemsController mySystems;
	private InGameEntityFactory myEntityFactory;
	private testingClass myTestingClass;

	private EngineView myEngineView;
	private GameCapture myGameCapture;

	public EngineController(Stage s, Main m) {
		super(RESOURCE_NAME);
		myStage = s;
		myMain = m;
	}

	/**
	 * Initializes the animation and starts the
	 */
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / NUM_FRAMES_PER_SECOND), e -> step());
		animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);

		initStage();
		initStartView();
	}

	private void initStage() {
		myStage.setMinWidth(loadIntResource("StageMinWidth"));
		myStage.setMinHeight(loadIntResource("StageMinHeight"));
		myStage.setX(loadIntResource("StartX"));
		myStage.setY(loadIntResource("StartY"));
	}

	public void initStartView() {
		animation.stop();
		playing = false;
		myGameWorld = new GameWorld();
		myTestingClass = new testingClass();
		myGameWorld = myTestingClass.testFiring();
		
		GameStatistics stats = new GameStatistics(10, 10);
		myGameWorld.setGameStatistics(stats);
		myEventManager = new EventManager(this, myGameWorld);
		
		StartView myStartView = new StartView(this);
		Scene scene = myStartView.buildScene();
		myStage.setScene(scene);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyPressed(event.getCharacter());
            }
        });
		myStage.show();
	}

	/**
	 * Starts a game after the intial scene sets the stage and
	 */
	public void startGame(String selectedMode, Integer selectedLevel) {
		try {
			GameEvent e = new GameEvent(selectedMode, selectedLevel);
			myEventManager.handleGameStartEvent(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myEntityFactory = new InGameEntityFactory(myGameWorld.getGameStatistics(),
				myEventManager.getCurrentLevel().getAuthoredEntities());
		myEventManager.setEntityFactory(myEntityFactory);
		myEventManager.initializeRules();
		mySystems = new SystemsController(NUM_FRAMES_PER_SECOND, myEventManager);
		initEngineView();
	}

	/**
	 * Creates the engineView, starts the game by playing the animation
	 */
	private void initEngineView() {
		myEngineView = new EngineView(myStage, this);
		myStage.setScene(myEngineView.buildScene());
		myStage.show();
		setupGameCapture();
		animation.play();
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
		if (playing) {
			mySystems.iterateThroughSystems(myEventManager.getCurrentLevel());
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
		myEngineView.getShopPane().updateUpgrade(upgradelist);
	}

	public void attemptTower(double xLoc, double yLoc, String type) {
		EntityDroppedEvent event = new EntityDroppedEvent(xLoc / myEngineView.getScalingFactor().doubleValue(),
				yLoc / myEngineView.getScalingFactor().doubleValue(), type);
		myEventManager.handleEntityDropEvent(event);
	}

	public void keyPressed(String s){
		//TODO do something with this string
	}
	
	public void entityClicked(int myID) {
		EntityClickedEvent clickedEvent = new EntityClickedEvent(myID, myEngineView.getShopPane().getCurrentView());
		myEventManager.handleClickEvent(clickedEvent);
	}

	public void nextWaveClicked() {
		NextWaveEvent nextWaveEvent = new NextWaveEvent();
		myEventManager.handleNextWaveEvent(nextWaveEvent);
	}

	public void nextLevelClicked() {
		myEventManager.handleGoToNextLevelEvent();
	}

	public void switchModeClicked() {
		initStartView();
	}

	public void waveIsOver() {
		myEngineView.getStatusPane().getControlManager().nextWaveEnable();
	}

	public void levelIsOver(boolean won) {
		myEngineView.getStatusPane().getControlManager().nextLevelEnable(won);
	}

	public Main getMain() {
		return myMain;
	}

	public void setPlaying(boolean b) {
		playing = b;
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
