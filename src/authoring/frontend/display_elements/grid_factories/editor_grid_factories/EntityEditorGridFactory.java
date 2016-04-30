package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import java.io.File;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntityEditorGridFactory extends EditorGridFactory {

	private EditorGrid myEditorGrid;

	public EntityEditorGridFactory(IAuthoringView controller, EditorGrid grid) {
		super(controller);
		myEditorGrid = grid;
	}

	@Override
	public Panel createPrimaryDisplay() {
		EditorViewPanel editorView = new EditorViewPanel(800 * 0.7, 1200 * 0.7);
		editorView.initialize();
		editorView.setImage(new Image("question_mark.png")); // set default

		editorView.getPanelBar().addButton("Upload Image", e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				editorView.setImage(new Image(imageFile.toURI().toString()));
				((ModifiableAttributesPanel) myEditorGrid.getAttributesPanel())
						.updateImageComponent(imageFile.getName());
			}
		});
		return editorView;
	}

	@Override
	public RulesEditorPanel createRulesPanel() {
		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public ModifiableAttributesPanel createModifiableAttributesPanel() {
		ModifiableAttributesPanel attributes = new ModifiableEntityAttributesPanel(MAX_SIZE, MAX_SIZE, myController);
		attributes.initialize();
		return attributes;
	}

	@Override
	public ButtonDashboard createButtonDashboard() {
		EditorButtonDashboard buttons = new EditorButtonDashboard(MAX_SIZE, MAX_SIZE);
		buttons.initialize();
		return buttons;
	}

}
