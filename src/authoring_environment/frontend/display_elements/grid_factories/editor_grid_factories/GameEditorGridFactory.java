package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.RulesEditorPanel;
import authoring_environment.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring_environment.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableGameAttributesPanel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;

/**
 * 
 * @author Frank
 *
 */

public class GameEditorGridFactory extends EditorGridFactory {

	public GameEditorGridFactory(IController controller) {
		super(controller);
	}

	@Override
	public Panel createRulesPanel() {
		RulesEditorPanel editorPanel = new RulesEditorPanel(PANEL_SIZE, PANEL_SIZE);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public Panel createModifiableAttributesPanel() {
		ModifiableAttributesPanel panel = new ModifiableGameAttributesPanel(PANEL_SIZE, PANEL_SIZE);
		panel.initialize();
		return panel;
	}

	@Override
	public Panel createPrimaryDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createButtonDashboard() {
		StandardButtonDashboard buttons = new StandardButtonDashboard(PANEL_SIZE, PANEL_SIZE);
		buttons.initialize();
		return buttons;
	}

}
