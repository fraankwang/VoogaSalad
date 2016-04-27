package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.grids.editor_grids.LevelEditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorDisplay extends EditorDisplay {

	public LevelEditorDisplay(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		myGrid = new LevelEditorGrid(myController, myEditorStage);
		myGrid.initialize();
	}

	@Override
	public EditorGrid getEditorGrid() {
		return myGrid;
	}

}
