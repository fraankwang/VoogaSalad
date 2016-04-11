package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.TowersTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class TowersTabGrid extends TabGrid {

	public TowersTabGrid(ITabDisplay tab) {
		super(tab);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new TowersTabGridFactory(myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

}
