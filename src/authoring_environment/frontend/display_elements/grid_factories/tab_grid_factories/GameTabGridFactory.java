package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.GridViewPanel;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableGameAttributesPanel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * 
 * @author Frank
 *
 */

public class GameTabGridFactory extends TabGridFactory {

	public GameTabGridFactory(IController controller, ITabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		return new GridViewPanel(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
	}

	@Override
	public Panel createButtonDashboard() {
		return new SimpleButtonDashboard(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
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
	public Panel createUnmodifiableAttributesPanel(TabDisplay tabDisplay) {
		return new UnmodifiableGameAttributesPanel(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE, tabDisplay);
	}

}
