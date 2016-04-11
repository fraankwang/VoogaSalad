package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.editor_displays.GameEditorDisplay;
import authoring_environment.frontend.display_elements.grids.tab_grids.GameTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class GameTabDisplay extends TabDisplay {

	public GameTabDisplay(IController controller) {
		super(controller);
		myController = controller;
	}

	@Override
	public void initialize() {
		myGrid = new GameTabGrid(myController, this);
		myEditorDisplay = new GameEditorDisplay(myController);
	}

}
