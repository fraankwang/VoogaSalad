package authoring_environment.frontend.display_elements.grids.editor_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories.GameEditorGridFactory;
import authoring_environment.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class GameEditorGrid extends EditorGrid {

	public GameEditorGrid(IController controller) {
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
		myGridFactory = new GameEditorGridFactory(myController);

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

	}
	
}
