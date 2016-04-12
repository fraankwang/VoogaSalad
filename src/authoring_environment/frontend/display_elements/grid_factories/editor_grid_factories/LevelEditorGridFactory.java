package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import javafx.scene.Node;

/**
 * 
 * @author Frank
 *
 */

public class LevelEditorGridFactory extends EditorGridFactory {

	public LevelEditorGridFactory(IController controller) {
		super(controller);
	}

	@Override
	public Panel createRulesPanel() {
		// TODO Auto-generated method stub
		return null;
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
		return new StandardButtonDashboard(myButtonDashboardButtonSize,myButtonDashboardButtonSize);
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
