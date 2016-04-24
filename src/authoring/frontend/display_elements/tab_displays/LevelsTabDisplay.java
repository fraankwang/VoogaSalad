package authoring.frontend.display_elements.tab_displays;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import authoring.backend.data.ObservableList;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.LevelEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.LevelsTabGrid;
import engine.backend.game_object.Level;

/**
 * The LevelsTabDisplay contains multiple tabs of a set of user-created levels
 * depending on which Mode is selected (sets of levels correspond to each Mode
 * created).
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelsTabDisplay extends TabDisplay {

	private ObservableList<Level> myLevelList;

	public LevelsTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
		myLevelList = myController.getLevelList();
		myLevelList.addObserver(this);
	}

	@Override
	public void initialize() {
		myEditorDisplay = new LevelEditorDisplay(myController);
		myEditorDisplay.initialize();
		myGrid = new LevelsTabGrid(myController, this);
		myGrid.initialize();

	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) arg;

		((LevelsTabGrid) myGrid).update(data);
	}

	@Override
	public String getName() {
		return "Levels";
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Name", null);
		map.put("MapImage", null);
		map.put("LevelTimer", null);
		map.put("WaveDelayTimer", null);
		map.put("MapWidth", null);
		map.put("MapHeight", null);
		System.out.println("*****1. LevelsTabDisplay: got default Levels attributes");
		System.out.println(map);

		return map;
	}
	
	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}

}
