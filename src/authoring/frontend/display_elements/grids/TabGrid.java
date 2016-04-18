package authoring.frontend.display_elements.grids;

import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.TabGridFactory;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.layout.GridPane;

/**
 * The TabGrid superclass is a subclass of Grid, which is in all the
 * TabDisplays. The extra functionality that TabGrids have is the creation of an
 * attributes panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank
 *
 */

public abstract class TabGrid extends Grid {

	protected UnmodifiableAttributesPanel myUnmodifiableAttributesPanel;
	protected TabDisplay myTabDisplay;

	public TabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;

	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myUnmodifiableAttributesPanel = ((TabGridFactory) myGridFactory).createUnmodifiableAttributesPanel(myTabDisplay);
	}
	
	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		GridPane.setRowSpan(myPrimaryDisplay.getNode(), 2);
		myGrid.add(myUnmodifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
	}
	
	public void setAttributesPanel(List<Map<String, String>> info) {
		myUnmodifiableAttributesPanel.setAttributes(info);
	}
	
	public Map<String, String> getAttributesMap() {
		return myUnmodifiableAttributesPanel.getAttributesMap();
	}

}
