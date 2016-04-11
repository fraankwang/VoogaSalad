package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grids.editor_grids.GameEditorGrid;
import authoring_environment.frontend.interfaces.IDisplayEntity;

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

	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
