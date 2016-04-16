package authoring.frontend.display_elements.grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.Panel;

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

	public EditorGrid(IAuthoringView controller) {
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

		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		myGrid.add(myRulesPanel.getNode(), 0, 1);
		myGrid.add(myModifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);

	}

	protected void sendData(Map<String, String> map) {
		myController.writeData(map);
	}
	
	public Panel getAttributesPanel() {
		return myModifiableAttributesPanel;
	}
}
