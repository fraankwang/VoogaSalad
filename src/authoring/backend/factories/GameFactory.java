package authoring.backend.factories;

import backend.GameWorld;
import backend.Level;
import backend.Mode;
import backend.game_object.components.Component;
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;

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
	
	public GameWorld createGame(){
		setUpModes();
		setUpLevels();
		setUpEntities();
		setUpComponents();
		return myGame;
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
			Mode currentMode = myGame.getModeWithId(newLevel.getParentId());
			currentMode.addLevel(newLevel);
		}
	}
	
	private void setUpEntities(){
		for (Object info : myProperties.getMyEntities()){
			Entity newEntity = myEntityFactory.createEntity(info);
			Level currentLevel = myGame.getLevelWithId(newEntity.getLevelId());
			currentLevel.addToEntities(newEntity);
		}
	}
	
	private void setUpComponents(){
		for (Object info : myProperties.getMyComponents()){
			Component newComponent = myComponentFactory.createComponent(info);
			IEntity currentEntity = myGame.getEntityWithId(newComponent.getEntityId());
			currentEntity.addComponent(newComponent);
		}
	}
	
	
	
	
}
