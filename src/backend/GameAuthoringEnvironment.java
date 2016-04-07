/**
 * 
 * @author mario_oliver93
 *
 */

package backend;

import backend.game_object.entities.Entity;
import backend.game_object.entities.EntityFactoryClass;
import backend.systems.SystemsController;
import exception.DrumpfTowerException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameAuthoringEnvironment {

	private EntityFactoryClass entityFactory;
	private GameObject trumpGame;
	private FrontEndGameAuthoringEnvironment frontMockData;
	private FrontEndAccessController backendController;

	public GameAuthoringEnvironment() {
		entityFactory = new EntityFactoryClass();
		frontMockData = new FrontEndGameAuthoringEnvironment();
	}
	
	public void setBackendController(FrontEndAccessController backendController){
		this.backendController = backendController;
	}

	public GameObject createGameObject() {
		return new GameObject();
	}

	public GameObject setGameObjectWithMockData(FrontEndGameAuthoringEnvironment mockData) {
		trumpGame = createGameObject();
		trumpGame.initializeGameObject(mockData.modesWanted, mockData.levelsWanted);
		// can add extra layer iterating through each level so we add entity to
		// the right level
		// assuming we only want to add to level one right now
		for (int eachSprite = 0; eachSprite < mockData.level1SpritesComponentWanted.length; eachSprite++) {
			Entity entity;
			entity = entityFactory.makeEntity(trumpGame, mockData.level1SpritesComponentWanted[eachSprite]);
			// this is the line that assumes we are on mode one and level one
			trumpGame.getModes().get(trumpGame.getGameStats().getCurrentMode()).getLevels()
					.get(trumpGame.getGameStats().getCurrentLevel()).addToEntities(entity);
		}
		trumpGame.printWhatIHave();
		return trumpGame;
	}

}
