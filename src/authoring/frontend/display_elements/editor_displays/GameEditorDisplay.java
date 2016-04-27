package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.grids.editor_grids.GameEditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class GameEditorDisplay extends EditorDisplay {

	public GameEditorDisplay(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new GameEditorGrid(myController, myEditorStage);
		myGrid.initialize();
	}

	@Override
	public EditorGrid getEditorGrid() {
		return myGrid;
	}

}
