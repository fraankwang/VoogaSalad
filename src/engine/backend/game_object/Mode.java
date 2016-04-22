/**
 * 
 * @author mario_oliver93
 *
 */
package engine.backend.game_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mode {

	private String myName;
	private Set<String> levelNames;
	private List<Level> levels;
	private Map<String, String> modeInfo;
	private ModeStatistics myModeStatistics;
	
	public Mode(String name, Set<String> levelNames) {
		this.levels = new ArrayList<Level>();
		this.levelNames = levelNames;
		this.myName = name;
		this.modeInfo = new HashMap<String, String>();
		initializeInfo();
	}
	
	public void initializeInfo() {
		modeInfo.put("Type", "Mode");
		modeInfo.put("Name", myName);
		modeInfo.put("LevelNames", stringLevelNames());
	}

	public List<Level> getLevels() {
		return levels;
	}
	
	private String stringLevelNames() {
		StringBuilder sb = new StringBuilder();
		for (String level : levelNames) {
			sb.append(level);
			sb.append(", ");
		}
		return sb.toString();
	}
	
	public Set<String> getLevelNames() {
		return levelNames;
	}
	
	public Map<String, String> getInfo() {
		return modeInfo;
	}

	public ModeStatistics getModeStatistics(){
		return myModeStatistics;
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
