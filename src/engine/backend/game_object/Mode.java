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
	
	/**
	 * Authoring Environment Constructor.
	 */
	public Mode(String myName, ModeStatistics modeStatistics, Map<Integer, Level> levels) {
		this.myName = myName;
		this.myModeStatistics = modeStatistics;		
		this.myLevels = levels;
	}
	
	public Mode(String myName) {
		this.myName = myName;
	}
		
	public Map<Integer, Level> getLevels() {
		return myLevels;
	}

	public ModeStatistics getModeStatistics(){
		return myModeStatistics;
	}
	
	public String getName(){
		return this.myName;
	}
	
	@Override
	public String toString() {
		return "Mode [levels=" + myLevels + "]";
	}

}
