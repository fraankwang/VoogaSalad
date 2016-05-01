package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;
import javafx.scene.image.Image;
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

	/**
	 * The default Image displayed in the EditorViewPanel is a question mark.
	 * The button within the EditorViewPanel allows for editing the Image in the
	 * PrimaryDisplay and in the ModifiableAttributesPanel.
	 */
	@Override
	public Panel createPrimaryDisplay() {
		EditorViewPanel editorView = new EditorViewPanel(800 * 0.7, 1200 * 0.7);
		editorView.initialize();

		editorView.setImage(new Image("resources/images/question_mark.png")); // set
																				// defaul
		editorView.setDescription("Entity");

		editorView.getPanelBar().addButton("Upload Image", e -> {
			String newImage = myController.getAuthoringViewManager().getObjectChooser().openChooser();
			editorView.setImage(new Image(newImage));
			((ModifiableEntityAttributesPanel) myEditorGrid.getAttributesPanel()).updateImageComponent(newImage);
		});
		return editorView;
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
