package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.frontend.display_elements.grids.editor_grids.EntityEditorGrid;
import authoring_environment.frontend.interfaces.IDisplayEntity;

/**
 * 
 * @author benchesnut
 *
 */

public class EntityEditorDisplay extends EditorDisplay {

	public EntityEditorDisplay() {
		myGrid = new EntityEditorGrid();

	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
