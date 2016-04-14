package authoring.frontend.display_elements.editor_displays;

import authoring.controller.IController;
import authoring.frontend.display_elements.grids.editor_grids.GameEditorGrid;
import authoring.frontend.interfaces.IDisplayEntity;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class GameEditorDisplay extends EditorDisplay {

	public GameEditorDisplay(IController controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new GameEditorGrid(myController);
		myGrid.initialize();
	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
