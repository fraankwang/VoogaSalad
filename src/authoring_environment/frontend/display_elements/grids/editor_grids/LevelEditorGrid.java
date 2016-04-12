package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.LevelEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class LevelEditorGrid extends EditorGrid {

	public LevelEditorGrid(IController controller) {
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
		myGridFactory = new LevelEditorGridFactory(myController);

	}
	
	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myButtonDashboard.buildNode(), 1, 1);
	}

}
