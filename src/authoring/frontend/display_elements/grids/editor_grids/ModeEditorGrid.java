package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.ModeEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Frank
 *
 */

public class ModeEditorGrid extends EditorGrid {

	public ModeEditorGrid(IAuthoringView controller, Stage stage) {
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
		myGridFactory = new ModeEditorGridFactory(myController);

	}

	@Override
	public void setAttributesPanel(Map<String, String> info) {
		super.setAttributesPanel(info);
		if (info.get("Name") == null) {
			Text text = new Text("Please Select a Mode");
			text.setFont(new Font(70));
			
			WritableImage snapshot = text.snapshot(new SnapshotParameters(), null);
			((EditorViewPanel) myPrimaryDisplay).setImage(snapshot);

		} else {
			Label text = new Label(info.get("Name"));
			text.setFont(new Font(70));

			WritableImage snapshot = text.snapshot(new SnapshotParameters(), null);
			((EditorViewPanel) myPrimaryDisplay).setImage(snapshot);
		}
	}
	
}
