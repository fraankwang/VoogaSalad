package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grids.editor_grids.ModeEditorGrid;
import authoring_environment.frontend.interfaces.IDisplayEntity;

public class ModeEditorDisplay extends EditorDisplay {

	public ModeEditorDisplay(IController controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new ModeEditorGrid(myController);
		
	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
