package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import java.io.File;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableGameAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Frank
 *
 */

public class GameEditorGridFactory extends EditorGridFactory {

	public GameEditorGridFactory(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public Panel createRulesPanel() {
		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public ModifiableAttributesPanel createModifiableAttributesPanel() {
		ModifiableAttributesPanel panel = new ModifiableGameAttributesPanel(MAX_SIZE, MAX_SIZE);
		panel.initialize();
		return panel;
	}

	@Override
	public Panel createPrimaryDisplay() {
		EditorViewPanel editorView = new EditorViewPanel(50, 50);
		editorView.initialize();

		editorView.getPanelBar().addButton("Upload Image", e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				editorView.setImage(new Image(imageFile.toURI().toString()));
			}
		});
		return editorView;
	}

	@Override
	public Panel createButtonDashboard() {
		StandardButtonDashboard buttons = new StandardButtonDashboard(MAX_SIZE, MAX_SIZE);
		buttons.initialize();
		return buttons;
	}

}
