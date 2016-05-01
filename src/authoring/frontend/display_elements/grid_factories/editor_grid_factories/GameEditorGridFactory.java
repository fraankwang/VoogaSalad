package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableGameAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import javafx.scene.image.Image;

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
	public RulesEditorPanel createRulesPanel(ModifiableAttributesPanel attributes) {
//		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE, myController, attributes);
//		editorPanel.initialize();
//		return editorPanel;
		return null;
	}

	@Override
	public ModifiableAttributesPanel createModifiableAttributesPanel() {
		ModifiableAttributesPanel attributes = new ModifiableGameAttributesPanel(MAX_SIZE, MAX_SIZE, myController);
		attributes.initialize();
		return attributes;
	}

	@Override
	public Panel createPrimaryDisplay() {
		EditorViewPanel editorView = new EditorViewPanel(50, 50);
		editorView.initialize();

		editorView.getPanelBar().addButton("Upload Image", e -> {
			String newImage = myController.getAuthoringViewManager().getObjectChooser().openChooser();
			System.out.println(newImage);
			editorView.setImage(new Image(newImage));
		});
		return editorView;
	}

	@Override
	public ButtonDashboard createButtonDashboard() {
		EditorButtonDashboard buttons = new EditorButtonDashboard(MAX_SIZE, MAX_SIZE);
		buttons.initialize();
		return buttons;
	}

}
