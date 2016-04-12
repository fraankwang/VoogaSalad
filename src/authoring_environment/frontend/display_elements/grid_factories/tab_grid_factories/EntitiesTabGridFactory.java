package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableEntityAttributesPanel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.Node;

public class EntitiesTabGridFactory extends TabGridFactory {

	public EntitiesTabGridFactory(IController controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		return null;
	}

	@Override
	public Panel createButtonDashboard() {
		return new StandardButtonDashboard(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
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
		return new UnmodifiableEntityAttributesPanel(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE, tabDisplay);
	}
}