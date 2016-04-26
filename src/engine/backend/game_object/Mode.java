/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.Map;

public class Mode {

	private Map<Integer, Level> myLevels;
	private String myName;
	private ModeStatistics myModeStatistics;
	private int index;
		
	public Map<Integer, Level> getLevels() {
		return myLevels;
	}

	public ModeStatistics getModeStatistics(){
		return myModeStatistics;
	}
	
	public String getName(){
		return this.myName;
	}

	public void addLevel(Level level) {
		myLevels.put(level.getIndex(), level);
	}
	
	@Override
	public String toString() {
		return "Mode [levels=" + myLevels + "]";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
