package authoring.frontend.display_elements.tab_displays;

import java.util.*;

import authoring.backend.data.ObservableList;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.LevelEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.LevelsTabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;

/**
 * The LevelsTabDisplay contains multiple tabs of a set of user-created levels
 * depending on which Mode is selected (sets of levels correspond to each Mode
 * created).
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelsTabDisplay extends TabDisplay {

	private ObservableList<AuthoringLevel> myLevelList;

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
		((GridViewPanel) myGrid.getPrimaryDisplay()).setPanelBarDescription("Levels");

	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) arg;

		((LevelsTabGrid) myGrid).updateLevelsPrimaryDisplay(data);
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();

		List<String> defaultAttributes = ((TabGrid) myGrid).getDefaultAttributes();

		for (String attribute : defaultAttributes) {
			map.put(attribute, null);
		}

		return map;
	}

	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}

	public Map<String, String> getLevels() {
		return ((LevelsTabGrid) myGrid).getLevels();
	}

	public String getName() {
		return "Levels";
	}

}
