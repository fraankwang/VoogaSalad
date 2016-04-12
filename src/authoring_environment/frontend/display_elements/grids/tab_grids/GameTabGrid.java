package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.GameTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class GameTabGrid extends TabGrid {

	public GameTabGrid(IController controller, ITabDisplay tabDisplay) {
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
		//superclass method creates SimpleButtonDashboard at position 1,1
		
		myGrid.add(myButtonDashboard.buildNode(), 0, 0);
		//overridden method creates additional simple button dashboard at 0,0
	}
}
