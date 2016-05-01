package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.LevelEditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableLevelAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import javafx.scene.image.Image;

/**
 * 
 * @author Frank
 *
 */

public class LevelEditorGridFactory extends EditorGridFactory {

	private EditorGrid myEditorGrid;

	public LevelEditorGridFactory(IAuthoringView controller, EditorGrid grid) {
		super(controller);
		myEditorGrid = grid;
	}

	/**
	 * Rules are created and defined within the Level Editor. This task is
	 * encapsulated by the RulesEditorPanel.
	 * 
	 * @param attributes
	 * @return
	 */
	public RulesEditorPanel createRulesPanel(ModifiableAttributesPanel attributes) {
		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE, myController, attributes);
		editorPanel.initialize();
		return editorPanel;
	}

	/**
	 * The PrimaryDisplay for the Level Editor is a LevelEditorViewPanel, which
	 * contains additional functionalities to create Paths.
	 */
	@Override
	public Panel createPrimaryDisplay() {
		LevelEditorViewPanel editorView = new LevelEditorViewPanel(Constants.getDouble("LEVEL_EDITOR_GRID_SIZE"), Constants.getDouble("LEVEL_EDITOR_GRID_SIZE"));
		editorView.initialize();
		editorView.setImage(new Image("resources/images/question_mark.png"));
		editorView.setDescription("Level");

		editorView.getPanelBar().addButton(Constants.getString("UPLOAD_IMAGE_BUTTON"), e -> {
			String newImage = myController.getAuthoringViewManager().getObjectChooser().openChooser();
			editorView.setImage(new Image(newImage));
			((ModifiableLevelAttributesPanel) myEditorGrid.getAttributesPanel()).updateImageComponent(newImage);
		});

		return editorView;

	}

	@Override
	public ModifiableAttributesPanel createModifiableAttributesPanel() {
		ModifiableAttributesPanel attributes = new ModifiableLevelAttributesPanel(MAX_SIZE, MAX_SIZE, myController);
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