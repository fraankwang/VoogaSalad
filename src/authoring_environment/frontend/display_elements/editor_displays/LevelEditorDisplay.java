package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grids.editor_grids.LevelEditorGrid;
import authoring_environment.frontend.interfaces.IDisplayEntity;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorDisplay extends EditorDisplay {

	public LevelEditorDisplay(IController controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new LevelEditorGrid(myController);

	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
