package authoring_environment.frontend.display_elements.grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.layout.GridPane;

/**
 * The TabGrid superclass is a subclass of Grid, which is in all the
 * TabDisplays. The extra functionality that TabGrids have is the creation of an
 * attributes panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank
 *
 */

public abstract class TabGrid extends Grid {

	protected Panel myUnmodifiableAttributesPanel;
	protected TabDisplay myTabDisplay;

	public TabGrid(IController controller, TabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;

	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myUnmodifiableAttributesPanel = ((TabGridFactory) myGridFactory).createUnmodifiableAttributesPanel(myTabDisplay);
	}
	
	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		GridPane.setRowSpan(myPrimaryDisplay.getNode(), 2);
//		myGrid.add(myRightSubGrid, 1, 0);
		myGrid.add(myUnmodifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
	}

}
