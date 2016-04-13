package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.ModesTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabGrid extends TabGrid {

	public ModesTabGrid(IController controller, TabDisplay tabDisplay) {
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
		myGridFactory = new ModesTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		
	}

}
