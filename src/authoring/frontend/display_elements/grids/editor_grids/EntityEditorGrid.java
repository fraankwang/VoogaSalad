package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntityEditorGrid extends EditorGrid {

	public EntityEditorGrid(IAuthoringView controller, Stage stage) {
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
		myGridFactory = new EntityEditorGridFactory(myController, this);

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		GridPane.setRowSpan(myPrimaryDisplay.getNode(), 2);
		myGrid.add(myModifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
		
	}
	
	@Override
	public void setAttributesPanel(Map<String, String> info) {
		if (info.get("DisplayComponent_Image") == null) {
			info.put("DisplayComponent_Image", "resources/images/question_mark.png");
		}
		((EditorViewPanel) myPrimaryDisplay).setImage(new Image(myController.getImageMap().get(info.get("DisplayComponent_Image"))));
		super.setAttributesPanel(info);
	}
	
	@Override
	public void initializeHotKeys() {
		super.initializeHotKeys();
		Button addComponentButton = ((ModifiableEntityAttributesPanel) myModifiableAttributesPanel).getAddComponentButton();
	
		addComponentButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						addComponentButton.fire();
					}
				});
	}
}
