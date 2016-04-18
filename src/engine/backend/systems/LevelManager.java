package engine.backend.systems;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import engine.backend.game_object.Level;

public class LevelManager extends Observable implements Observer{
	
	private List<Level> myLevels;
	private int levelIndex;

	public LevelManager(){
		
	}
	
	public void initialize(List<Level> levels){
		myLevels = levels;
		levelIndex = 0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public Level getCurrentLevel(){
		return myLevels.get(levelIndex);
	}

	public void setCurrentLevelIndex(int currentLevel) {
		// TODO Auto-generated method stub
		levelIndex = currentLevel;
	}
	
	
}
