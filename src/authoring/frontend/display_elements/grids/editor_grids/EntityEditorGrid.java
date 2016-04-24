package authoring.frontend.display_elements.grids.editor_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;

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
	public void initializeHotKeys() {
		super.initializeHotKeys();
		Button addComponentButton = ((ModifiableEntityAttributesPanel) myModifiableAttributesPanel).getAddComponentButton();
	
		addComponentButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN),
				new Runnable() {
					@Override
					public void run() {
						addComponentButton.fire();
					}
				});
	}
}
