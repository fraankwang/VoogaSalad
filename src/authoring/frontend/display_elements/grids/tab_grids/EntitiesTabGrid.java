package authoring.frontend.display_elements.grids.tab_grids;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.EntitiesTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.tab_displays.TabDisplay;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabGrid extends TabGrid {

	public EntitiesTabGrid(IAuthoringView controller, TabDisplay tab) {
		super(controller, tab);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntitiesTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		// get save button from buttondashboard -> set on action to both gather
		// data from editorviewpanel and modifiableattributespanel to update globaldata
	}

}
