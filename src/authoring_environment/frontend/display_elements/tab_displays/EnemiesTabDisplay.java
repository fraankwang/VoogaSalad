package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.editor_displays.EnemyEditorDisplay;
import authoring_environment.frontend.display_elements.grids.tab_grids.EnemiesTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class EnemiesTabDisplay extends TabDisplay {

	public EnemiesTabDisplay() {
		myGrid = new EnemiesTabGrid(this);
		myEditorDisplay = new EnemyEditorDisplay();
	}

}
