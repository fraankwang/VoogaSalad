package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.TowersTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;

/**
 * 
 * @author Frank
 *
 */

public class TowersTabGrid extends TabGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new TowersTabGridFactory();
	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
