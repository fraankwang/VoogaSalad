package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.LevelEditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.display_elements.grids.tab_grids.LevelsTabGrid;
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
		myActiveGrid = new LevelsTabGrid(this);
		myGrid = myActiveGrid;
		myEditorDisplay = new LevelEditorDisplay();
		
		// Testing
	}

	@Override
	public Node buildNode() {
		return myLevelsTabPane;
	}
}
