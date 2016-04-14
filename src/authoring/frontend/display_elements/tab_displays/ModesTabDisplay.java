package authoring.frontend.display_elements.tab_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.ModeEditorDisplay;
import authoring.frontend.display_elements.grids.tab_grids.ModesTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabDisplay extends TabDisplay {

	public ModesTabDisplay(IAuthoringView controller) {
		super(controller);
		myController = controller;
	}

	@Override
	public void initialize() {
		myGrid = new ModesTabGrid(myController, this);
		myGrid.initialize();
		myEditorDisplay = new ModeEditorDisplay(myController);
		myEditorDisplay.initialize();
		
	}

}
