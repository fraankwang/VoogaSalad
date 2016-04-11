package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author benchesnut
 *
 */

public class EntityEditorGrid extends EditorGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntityEditorGridFactory();
	}

}
