package authoring.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.TabGridFactory;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableEntityAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntitiesTabGridFactory extends TabGridFactory {

	public EntitiesTabGridFactory(IAuthoringView controller, ITabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		GridViewPanel gridView = new GridViewPanel(MAX_SIZE, MAX_SIZE, myTabDisplay);
		gridView.initialize();
		gridView.addImage(new ImageView("DrumpfVader.png"));
		return gridView;
	}

	@Override
	public Panel createButtonDashboard() {
		StandardButtonDashboard buttons = new StandardButtonDashboard(MAX_SIZE, MAX_SIZE);
		buttons.initialize();
		return buttons;
	}

	@Override
	public Panel createUnmodifiableAttributesPanel(TabDisplay tabDisplay) {
		UnmodifiableAttributesPanel attributes = new UnmodifiableEntityAttributesPanel(MAX_SIZE, MAX_SIZE, tabDisplay);
		attributes.initialize();
		return attributes;
	}

}
