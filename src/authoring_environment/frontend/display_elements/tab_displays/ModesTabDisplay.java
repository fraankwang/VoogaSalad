package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.ModeEditorDisplay;
import authoring_environment.frontend.display_elements.grids.tab_grids.ModesTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabDisplay extends TabDisplay {

	public ModesTabDisplay() {
		myGrid = new ModesTabGrid();
		myEditorDisplay = new ModeEditorDisplay();
	}

}
