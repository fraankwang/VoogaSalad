package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.LevelsTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGrid extends TabGrid {

	public LevelsTabGrid(IController controller, ITabDisplay tabDisplay) {
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
		myGridFactory = new LevelsTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

	}

}
