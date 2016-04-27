/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mode {

	private Map<Integer, Level> myLevels;
	private String myName;
	private ModeStatistics myModeStatistics;
	private int index;
	
	public Mode(String name) {
		myLevels = new HashMap<Integer, Level>();
		this.myName = name;
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

	public void addLevel(Level level) {
		myLevels.put(level.getIndex(), level);
	}

	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Mode) {
			Mode temp = (Mode) o;
			if (this.myName.equals(temp.myName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
