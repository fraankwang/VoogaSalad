package authoring.frontend.display_elements.grids.editor_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.GameEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;

/**
 * 
 * @author Frank
 *
 */

public class GameEditorGrid extends EditorGrid {

	public GameEditorGrid(IAuthoringView controller) {
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
		// ((ButtonDashboard) myButtonDashboard).getSaveButton().setOnAction(
		// e -> sendData(((ModifiableGameAttributesPanel)
		// myModifiableAttributesPanel).saveAttributes()));

	}

}
