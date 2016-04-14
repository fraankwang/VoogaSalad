/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Mode {

	private String myName;
	private Set<String> levelNames;
	private List<Level> levels;
	
	public Mode(String name, Set<String> levelNames) {
		this.levels = new ArrayList<Level>();
		this.levelNames = levelNames;
		this.myName = name;
	}

	public List<Level> getLevels() {
		return levels;
	}
	
	public Set<String> getLevelNames() {
		return levelNames;
	}
	
	public String getName(){
		return this.myName;
	}

	public void addLevel(Level level) {
		levels.add(level);
	}
	
	public void addLevelName(String name) {
		levelNames.add(name);
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
