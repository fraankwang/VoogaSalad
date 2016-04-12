package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.ModeEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class ModeEditorGrid extends EditorGrid {

	public ModeEditorGrid(IController controller) {
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
		myGridFactory = new ModeEditorGridFactory(myController);

	}
	
	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myButtonDashboard.buildNode(), 1, 1);
		myGrid.add(myButtonDashboard.buildNode(), 0, 0);
	}
	
}
