package backend;

import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameAuthenticationEnvironment {

	SystemsController systems;
	EntityFactoryClass entityFactory;
	GameObject trumpGame;
	FrontEndGameAuthorizationEnvironment frontMockData;

	public GameAuthenticationEnvironment() {
		systems = new SystemsController();
		entityFactory = new EntityFactoryClass();
		frontMockData = new FrontEndGameAuthorizationEnvironment();
	}

	private void addDrumpfImage(Group myRoot) {
		// adding arbitrary donald drumpf darth vader character
		ImageView myPlayer = new ImageView(new Image(getClass().getResourceAsStream("/DrumpfVader.png")));
		myPlayer.setFitWidth(80);
		myPlayer.setFitHeight(100);
		myPlayer.setX(200);
		myPlayer.setY(200);
		myRoot.getChildren().add(myPlayer);
	}

	/**
	 * Create game loop
	 * 
	 * @param numFramesPerSecond
	 * @return
	 */
	public KeyFrame startGameLoop(int numFramesPerSecond) {
		return new KeyFrame(Duration.millis(1000 / numFramesPerSecond), e -> step(trumpGame));
	}

	private void step(GameObject game) {
		systems.iterateThroughSystems(game);
	}

	public Scene init(Stage primaryStage, int width, int height) {
		Group myRoot = new Group();
		addDrumpfImage(myRoot);
		Scene myScene = new Scene(myRoot, width, height, Color.WHITE);
		return myScene;
	}

	public GameObject createGameObject() {
		return new GameObject();
	}

	public void setGameObjectWithFrontEndInfo(FrontEndGameAuthorizationEnvironment mockData) {
		trumpGame = new GameObject();
		trumpGame.initializeGameObject(mockData.modesWanted, mockData.levelsWanted);
		// can add extra layer iterating through each level so we add entity to the right level
		// assuming we only want to add to level one right now
		for (int eachSprite = 0; eachSprite < mockData.level1SpritesComponentWanted.length; eachSprite++) {
			Entity entity = entityFactory.makeEntity(trumpGame, mockData.level1SpritesComponentWanted[eachSprite]);
			// this is the line that assumes we are on mode one and level one
			trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
					.get(trumpGame.getGameStats().getCurrentLevel()).addToEntities(entity);
		}
		trumpGame.printWhatIHave();
	}

}
