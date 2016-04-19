/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;

public class Mode {

	private List<Level> levels;
	private String myName;
	private ModeStatistics myModeStatistics;
	
	public Mode(String name) {
		levels = new ArrayList<Level>();
		this.myName = name;
	}

	public List<Level> getLevels() {
		return levels;
	}
	
	public ModeStatistics getModeStatistics(){
		return myModeStatistics;
	}
	
	public String getName(){
		return this.myName;
	}

	public void addLevel(Level level) {
		level.setModeName(myName);
		levels.add(level);
	}

	@Override
	public String toString() {
		return "Mode [levels=" + levels + "]";
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

}
