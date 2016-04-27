package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import java.io.File;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.LevelEditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableLevelAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import authoring.frontend.editor_features.ImageImporter;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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

	@Override
	public RulesEditorPanel createRulesPanel() {
		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public Panel createPrimaryDisplay() {
		LevelEditorViewPanel editorView = new LevelEditorViewPanel(800 * 0.7, 1200 * 0.7);
		editorView.initialize();
		editorView.setImage(new Image("images/question_mark.png")); // set default
																// image as
																// question
																// mark or
																// something

		editorView.getPanelBar().addButton("Upload Map Image", e -> {
			String newImage = myController.getAuthoringViewManager().getImageChooser().openChooser();
			System.out.println(newImage);
			editorView.setImage(new Image(newImage));
			((ModifiableAttributesPanel) myEditorGrid.getAttributesPanel())
					.updateImageComponent(newImage);
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