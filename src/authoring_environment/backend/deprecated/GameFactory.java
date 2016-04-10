package authoring_environment.backend.deprecated;

import backend.GameWorld;
import backend.Level;
import backend.Mode;

public class GameFactory {
	private EntityFactory myEntityFactory;
	private ComponentFactory myComponentFactory;
	private LevelFactory myLevelFactory;
	private ModeFactory myModeFactory;
	private GameWorld myGame;
	private GameProperties myProperties;
	
	public GameFactory() {
		myGame = new GameWorld();
		myProperties = new GameProperties();
		myModeFactory = new ModeFactory();
		myComponentFactory = new ComponentFactory();
		myEntityFactory = new EntityFactory();
	}
	
	private void setUpModes(){
		for (Object info : myProperties.getMyModes()){
			Mode newMode = myModeFactory.createMode(info);
			myGame.addMode(newMode);
		}
	}
	
	private void setUpLevels(){
		for (Object info: myProperties.getMyLevels()){
			Level newLevel = myLevelFactory.createLevel(info);
			Mode currentMode = myGame.getMode(newLevel.getParentId());
			currentMode.addLevel(newLevel);
		}
	}
	
	
	
	
	
	
}
