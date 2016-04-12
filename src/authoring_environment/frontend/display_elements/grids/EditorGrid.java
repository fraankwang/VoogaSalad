package authoring_environment.frontend.display_elements.grids;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;

/**
 * The EditorGrid superclass is a subset of Grid, which is in all the
 * EditorDisplays. The extra functionality that EditorGrids have is the creation
 * of an attributes panel (for which all the attributes displayed can be
 * modified).
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class EditorGrid extends Grid {

	protected Panel myRulesPanel;
	protected Panel myModifiableAttributesPanel;

	public EditorGrid(IController controller) {
		super(controller);
	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myRulesPanel = ((EditorGridFactory) myGridFactory).createRulesPanel();
		myModifiableAttributesPanel = ((EditorGridFactory) myGridFactory).createModifiableAttributesPanel();

	}
	
	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
//		myGrid.add(myPrimaryDisplay.buildNode(), 0, 0);
		myGrid.add(myRulesPanel.getNode(), 0, 1);
//		myGrid.add(myModifiableAttributesPanel.buildNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
		
	}
}
