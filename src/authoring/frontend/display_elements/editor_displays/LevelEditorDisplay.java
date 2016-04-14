package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.editor_grids.LevelEditorGrid;
import authoring.frontend.interfaces.IDisplayEntity;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorDisplay extends EditorDisplay {

	public LevelEditorDisplay(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new LevelEditorGrid(myController);
		myGrid.initialize();
	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
