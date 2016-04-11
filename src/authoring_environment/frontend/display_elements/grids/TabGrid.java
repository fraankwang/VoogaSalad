package authoring_environment.frontend.display_elements.grids;

import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * The TabGrid superclass is a subclass of Grid, which is in all the TabDisplays.
 * The extra functionality that TabGrids have is the creation of an attributes
 * panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank
 *
 */

public abstract class TabGrid extends Grid {

	protected Node myUnmodifiableAttributesPanel;
	protected ITabDisplay myTabDisplay;
	
	public TabGrid(ITabDisplay tab) {
		myTabDisplay = tab;
	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myUnmodifiableAttributesPanel = ((TabGridFactory) myGridFactory).createUnmodifiableAttributesPanel();
	}
	
}
