package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.editor_grids.GameEditorGrid;

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

}
