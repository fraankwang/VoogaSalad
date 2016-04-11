package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableLevelAttributesPanel;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGridFactory extends TabGridFactory {

	public LevelsTabGridFactory(ITabDisplay tab) {
		super(tab);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node createPrimaryDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node createButtonDashboard() {
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
		return createUnmodifiableAttributesPanel();
	}

	@Override
	public Node createUnmodifiableAttributesPanel() {
		UnmodifiableLevelAttributesPanel attributesPanel = new UnmodifiableLevelAttributesPanel(100, 100);
		return attributesPanel.buildNode();
	}

}
