package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.grids.editor_grids.ModeEditorGrid;
import authoring.frontend.display_elements.panels.LevelGridViewPanel;

/**
 * The ModeEditorDisplay differs because it's PrimaryDisplay in the Grid is a
 * separate Panel that changes based on which mode is selected.
 * 
 * @author Frank, benchesnut
 *
 */
public class ModeEditorDisplay extends EditorDisplay {

	public ModeEditorDisplay(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new ModeEditorGrid(myController, myEditorStage);
		myGrid.initialize();
	}

	@Override
	public EditorGrid getEditorGrid() {
		return myGrid;
	}

	public void setPrimaryDisplay(LevelGridViewPanel currentGridViewPanel) {
		((ModeEditorGrid) myGrid).setPrimaryDisplay(currentGridViewPanel);

	}

}
