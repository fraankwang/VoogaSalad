package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.EnemyEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class EnemyEditorGrid extends EditorGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EnemyEditorGridFactory();
	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
