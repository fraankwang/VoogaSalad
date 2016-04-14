package authoring.frontend.display_elements.tab_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.LevelEditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.display_elements.grids.tab_grids.LevelsTabGrid;
import javafx.scene.Node;
import javafx.scene.control.Tab;
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

	public LevelsTabDisplay(IAuthoringView controller) {
		super(controller);
		myController = controller;
	}

	@Override
	public void initialize() {
		myLevelsTabPane = new TabPane();
		myEditorDisplay = new LevelEditorDisplay(myController);
		myEditorDisplay.initialize();
		myActiveGrid = new LevelsTabGrid(myController, this);
		myActiveGrid.initialize();
		Tab activeTab = new Tab();
		activeTab.setContent(myActiveGrid.getNode());
		myLevelsTabPane.getTabs().add(activeTab);
		myGrid = myActiveGrid;
		// Testing
	}

	@Override
	public Node getNode() {
		return myLevelsTabPane;
	}

}
