package authoring.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.LevelGridViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableModeAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;

/**
 * 
 * @author Frank
 *
 */

public class ModeEditorGridFactory extends EditorGridFactory {

	public ModeEditorGridFactory(IAuthoringView controller) {
		super(controller);

	}

	@Override
	public RulesEditorPanel createRulesPanel() {
		RulesEditorPanel editorPanel = new RulesEditorPanel(MAX_SIZE, MAX_SIZE, myController);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public Panel createPrimaryDisplay() {
		LevelGridViewPanel levelView = new LevelGridViewPanel(50, 50, null);
		levelView.initialize();
		return levelView;
	}

	@Override
	public ModifiableAttributesPanel createModifiableAttributesPanel() {
		ModifiableAttributesPanel attributes = new ModifiableModeAttributesPanel(MAX_SIZE, MAX_SIZE, myController);
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
