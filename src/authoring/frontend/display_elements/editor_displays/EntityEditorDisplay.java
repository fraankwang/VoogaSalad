package authoring.frontend.display_elements.editor_displays;

import authoring.controller.IController;
import authoring.frontend.display_elements.grids.editor_grids.EntityEditorGrid;
import authoring.frontend.interfaces.IDisplayEntity;

/**
 * 
 * @author benchesnut
 *
 */

public class EntityEditorDisplay extends EditorDisplay {

	public EntityEditorDisplay(IController controller) {
		super(controller);
	}
	
	@Override
	public void initialize() {
		myGrid = new EntityEditorGrid(myController);
		myGrid.initialize();
	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}


}
