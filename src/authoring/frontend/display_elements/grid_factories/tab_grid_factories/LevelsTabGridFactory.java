package authoring.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.TabGridFactory;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableLevelAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.image.ImageView;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGridFactory extends TabGridFactory {

	public LevelsTabGridFactory(IAuthoringView controller, ITabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		GridViewPanel gridView = new GridViewPanel(MAX_SIZE, MAX_SIZE, myTabDisplay);
		gridView.initialize();
		gridView.addImage(new ImageView("tower_defense_map_1.png"));
		return gridView;
	}

	@Override
	public UnmodifiableAttributesPanel createUnmodifiableAttributesPanel(TabDisplay tabDisplay) {
		UnmodifiableAttributesPanel attributes = new UnmodifiableLevelAttributesPanel(MAX_SIZE, MAX_SIZE, tabDisplay);
		attributes.initialize();
		return attributes;
	}

	@Override
	public ButtonDashboard createButtonDashboard() {
		MainButtonDashboard buttons = new MainButtonDashboard(MAX_SIZE, MAX_SIZE);
		buttons.initialize();
		return buttons;
	}

}
