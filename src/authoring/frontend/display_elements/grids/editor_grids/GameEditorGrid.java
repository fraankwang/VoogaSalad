package authoring.frontend.display_elements.grids.editor_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.GameEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import javafx.stage.Stage;

/**
 * 
 * @author Frank
 *
 */

public class GameEditorGrid extends EditorGrid {

	public GameEditorGrid(IAuthoringView controller, Stage stage) {
		super(controller, stage);
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

}
