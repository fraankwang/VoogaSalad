package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.LevelEditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * The LevelsTabDisplay contains multiple tabs of a set of user-created levels
 * depending on which Mode is selected (sets of levels correspond to each Mode
 * created).
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelsTabDisplay extends TabDisplay {

	private TabPane myLevelsTabPane;
	private Grid myActiveGrid;

	public LevelsTabDisplay() {
		myLevelsTabPane = new TabPane();
		myGrid = myActiveGrid;
		myEditorDisplay = new LevelEditorDisplay();

	}

	@Override
	public Node buildNode() {
		return myLevelsTabPane;
	}
}
