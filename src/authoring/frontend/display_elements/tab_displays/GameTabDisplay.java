package authoring.frontend.display_elements.tab_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.GameEditorDisplay;
import authoring.frontend.display_elements.grids.tab_grids.GameTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class GameTabDisplay extends TabDisplay {

	public GameTabDisplay(IAuthoringView controller) {
		super(controller);
		myController = controller;
	}

	@Override
	public void initialize() {
		myGrid = new GameTabGrid(myController, this);
		myGrid.initialize();
		myEditorDisplay = new GameEditorDisplay(myController);
		myEditorDisplay.initialize();
	}

}
