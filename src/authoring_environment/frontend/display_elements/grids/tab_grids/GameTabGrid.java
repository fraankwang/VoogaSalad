package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.GameTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class GameTabGrid extends TabGrid {

	public GameTabGrid(IController controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new GameTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myButtonDashboard.buildNode(), 1, 1);
		myGrid.add(myUnmodifiableAttributesPanel.buildNode(), 1, 0);

	}
}
