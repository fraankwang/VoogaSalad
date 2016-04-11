package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.TowerEditorDisplay;
import authoring_environment.frontend.display_elements.grids.tab_grids.TowersTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class TowersTabDisplay extends TabDisplay {

	public TowersTabDisplay() {
		myGrid = new TowersTabGrid(this);
		myEditorDisplay = new TowerEditorDisplay();
	}

}
