package authoring.frontend.display_elements.grids.editor_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntityEditorGrid extends EditorGrid {

	public EntityEditorGrid(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntityEditorGridFactory(myController);

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

	}

}
