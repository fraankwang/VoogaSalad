package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.Node;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabGridFactory extends TabGridFactory {

	public ModesTabGridFactory(IController controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createButtonDashboard() {
		// TODO Auto-generated method stub
		return new SimpleButtonDashboard(50,50);
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

	@Override
	public Panel createUnmodifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
