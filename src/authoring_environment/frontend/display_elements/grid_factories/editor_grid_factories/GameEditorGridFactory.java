package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.RulesEditorPanel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import javafx.scene.Node;

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
		RulesEditorPanel editorPanel = new RulesEditorPanel(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
		editorPanel.initialize();
		return editorPanel;
	}

	@Override
	public Panel createModifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createPrimaryDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createButtonDashboard() {
		StandardButtonDashboard buttons = new StandardButtonDashboard(ARBITRARY_PANEL_SIZE,ARBITRARY_PANEL_SIZE);
		buttons.initialize();
		return buttons;
	}

	@Override
	public Node createLeftSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node createRightSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

}