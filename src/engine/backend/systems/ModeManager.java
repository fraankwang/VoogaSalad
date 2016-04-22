package engine.backend.systems;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import engine.backend.game_object.Mode;

public class ModeManager implements Observer{

	private List<Mode> myModes;
	private int modeIndex;
	
	public ModeManager(){

	}
	
	public void initialize(List<Mode> modes){
		myModes = modes;
		modeIndex = 0;
	}
	
	public Mode getCurrentMode(){
		return myModes.get(modeIndex);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public void setCurrentModeIndex(int currentMode) {
		// TODO Auto-generated method stub
		modeIndex = currentMode;
	}

}
