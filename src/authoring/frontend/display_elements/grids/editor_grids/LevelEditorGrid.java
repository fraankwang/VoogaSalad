package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.LevelEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.LevelEditorViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Frank, benchesnut
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
		((LevelEditorViewPanel) myPrimaryDisplay).setPaths(info.get("Paths"));
		info.remove("Paths");
		super.setAttributesPanel(info);
		if (info.get("MapBackgroundImage") == null) {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image("question_mark.png"));
		} else {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image(info.get("MapBackgroundImage")));
		}
	}
	
	@Override
	public Map<String, String> saveAttributes() {
		Map<String, String> info = myModifiableAttributesPanel.saveAttributes();
		info.put("Paths", ((LevelEditorViewPanel) myPrimaryDisplay).getPathIDs());
		return info;
	}

}
