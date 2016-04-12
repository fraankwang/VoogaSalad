package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.GridViewPanel;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class EntitiesTabGridFactory extends TabGridFactory {

	public EntitiesTabGridFactory(IController controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public Panel createPrimaryDisplay() {
		ImageView entityImage = new ImageView("DrumpfVader.png");	// set default image as question mark or something
//		return entityImage;
		return new GridViewPanel(50, 50);
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

	@Override
	public Panel createUnmodifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
