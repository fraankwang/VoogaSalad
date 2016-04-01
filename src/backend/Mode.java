package backend;

import java.util.ArrayList;
import java.util.List;

public class Mode {

	private List<Level> levels = null;

	public Mode() {
		levels = new ArrayList<Level>();
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void addToLevelsList(Level level) {
		levels.add(level);
	}

	@Override
	public String toString() {
		return "Mode [levels=" + levels + "]";
	}

}
