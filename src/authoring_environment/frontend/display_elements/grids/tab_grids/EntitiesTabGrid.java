package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.EntitiesTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabGrid extends TabGrid {

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntitiesTabGridFactory();
	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
