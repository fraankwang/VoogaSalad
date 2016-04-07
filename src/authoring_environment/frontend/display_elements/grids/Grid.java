package authoring_environment.frontend.display_elements.grids;

import authoring_environment.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.scene.Node;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class Grid implements IGrid {

	protected Node myGrid;
	protected Node myLeftSubGrid;
	protected Node myRightSubGrid;
	protected Node myPrimaryDisplay;
	protected Node myButtonDashboard;
	
	protected abstract Node createPrimaryDisplay();
	
	protected abstract Node createButtonDashboard();
	
	protected abstract Node createLeftSubGrid();

	protected abstract Node createRightSubGrid();
	
	public abstract Node buildNode();

}
