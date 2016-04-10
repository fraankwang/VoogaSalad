package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private TabPane myEntitiesTabPane;
	private Grid myActiveGrid;

	public EntitiesTabDisplay() {
		myEntitiesTabPane = new TabPane();
		myActiveGrid = new EntitiesTabGrid();
		myGrid = myActiveGrid;
		myEditorDisplay = new EntityEditorDisplay();

	}

	@Override
	public Node buildNode() {
		return myEntitiesTabPane;
	}
}
