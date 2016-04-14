package authoring.frontend.display_elements.grids.editor_grids;

import authoring.controller.IController;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.GameEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;

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
