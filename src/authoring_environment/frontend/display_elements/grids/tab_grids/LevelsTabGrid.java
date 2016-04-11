package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.LevelsTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGrid extends TabGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new LevelsTabGridFactory();

	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
