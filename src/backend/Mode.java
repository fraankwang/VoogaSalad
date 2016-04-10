/**
 * 
 * @author mario_oliver93
 *
 */
package backend;

import java.util.ArrayList;
import java.util.List;

public class Mode {

	private List<Level> levels;
	private int myId;
	
	public Mode(int id) {
		levels = new ArrayList<Level>();
		this.myId = id;
	}

	public List<Level> getLevels() {
		return levels;
	}
	
	public int getId(){
		return this.myId;
	}

	public void addLevel(Level level) {
		levels.add(level);
	}

	@Override
	public String toString() {
		return "Mode [levels=" + levels + "]";
	}

}
