package authoring_environment.frontend.display_elements.grids;

import authoring_environment.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.scene.Node;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class Grid implements IGrid {

	protected abstract Node createPrimaryDisplay();
	
	protected abstract Node createButtonDashboard();
	
	protected abstract Node createLeftSubGrid();

	protected abstract Node createRightSubGrid();
	
	public abstract Node buildNode();

}
