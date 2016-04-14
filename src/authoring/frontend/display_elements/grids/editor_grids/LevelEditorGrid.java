package authoring.frontend.display_elements.grids.editor_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.LevelEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class LevelEditorGrid extends EditorGrid {

	public LevelEditorGrid(IAuthoringView controller) {
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
		
	}

}
