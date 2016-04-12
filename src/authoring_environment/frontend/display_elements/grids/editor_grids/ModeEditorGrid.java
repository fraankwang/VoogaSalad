package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.ModeEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class ModeEditorGrid extends EditorGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new ModeEditorGridFactory();
	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
