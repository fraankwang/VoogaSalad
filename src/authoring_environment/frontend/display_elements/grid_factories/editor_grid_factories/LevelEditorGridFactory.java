package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.LevelEditorViewPanel;
import authoring_environment.frontend.display_elements.panels.Panel;
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
		LevelEditorViewPanel levelEditor = new LevelEditorViewPanel(50,50);
		return levelEditor;
	}

	@Override
	public Panel createButtonDashboard() {
		// TODO Auto-generated method stub
		return null;
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
