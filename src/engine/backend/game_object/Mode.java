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
	private int myID;
	
	public Mode(int id) {
		levels = new ArrayList<Level>();
		this.myID = id;
	}
	
	public Mode(){
		levels = new ArrayList<Level>();
	}

	public List<Level> getLevels() {
		return levels;
	}
	
	public int getId(){
		return this.myID;
	}

	public void addLevel(Level level) {
		level.setModeID(myID);
		levels.add(level);
	}

	@Override
	public String toString() {
		return "Mode [levels=" + levels + "]";
	}

}
