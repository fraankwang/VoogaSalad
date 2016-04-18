package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.ModesTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.tab_displays.TabDisplay;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabGrid extends TabGrid {

	public ModesTabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
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
	
	@Override
	public void setAttributesPanel(List<Map<String, String>> info) {
		
	}

}
