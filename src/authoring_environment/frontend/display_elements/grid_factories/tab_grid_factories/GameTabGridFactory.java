package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.GridViewPanel;
import authoring_environment.frontend.display_elements.panels.Panel;
<<<<<<< HEAD
import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableGameAttributesPanel;
=======
import authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableModeAttributesPanel;
>>>>>>> ef39e159ba5af9a5ac82c491e6ca089fd673d273
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;

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
		GridViewPanel gridView = new GridViewPanel(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE, myTabDisplay);
		gridView.initialize();
		return gridView;
	}

	@Override
	public Panel createButtonDashboard() {
		StandardButtonDashboard buttons = new StandardButtonDashboard(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
		buttons.initialize();
		return buttons;
	}

	@Override
	public Panel createUnmodifiableAttributesPanel(TabDisplay tabDisplay) {
		UnmodifiableAttributesPanel attributes = new UnmodifiableGameAttributesPanel(ARBITRARY_PANEL_SIZE,
				ARBITRARY_PANEL_SIZE, tabDisplay);
		attributes.initialize();
		return attributes;
	}

}
