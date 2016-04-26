package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.LevelEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import javafx.stage.Stage;

/**
 * 
 * @author Frank
 *
 */

public class LevelEditorGrid extends EditorGrid {

	public LevelEditorGrid(IAuthoringView controller, Stage stage) {
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
		myGridFactory = new LevelEditorGridFactory(myController, this);

	}
	
	@Override
	public void setAttributesPanel(Map<String, String> info) {
		super.setAttributesPanel(info);
	}

}
