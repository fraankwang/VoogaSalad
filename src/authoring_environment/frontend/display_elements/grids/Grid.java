package authoring_environment.frontend.display_elements.grids;

import authoring_environment.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.scene.Node;

/**
 * The Grid superclass is the object that populates all the EditorDisplays and
 * TabDisplays. Each of these displays contains four elements: the primary
 * display, the button dashboard, and the left and right wrapper subgrids. All
 * of these elements are contained within the myGrid variable, which is created
 * in the buildNode() method.
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

	/**
	 * Upon instantiation of a Grid, the grid components are initialized and
	 * assembled (both through the inheriting subclass' abstract creation
	 * methods).
	 */
	public Grid() {
		initializeGrid();
		assembleGridComponents();
	}

	/**
	 * Initializes the four primary components of the Grid.
	 */
	protected void initializeGrid() {
		myPrimaryDisplay = createPrimaryDisplay();
		myButtonDashboard = createButtonDashboard();
		myLeftSubGrid = createLeftSubGrid();
		myRightSubGrid = createRightSubGrid();
	}

	/**
	 * Pieces together the four primary components of the Grid within the Node
	 * myGrid.
	 */
	protected abstract void assembleGridComponents();

	/**
	 * @return instantiated and formatted myPrimaryDisplay
	 */
	protected abstract Node createPrimaryDisplay();

	/**
	 * @return instantiated and formatted myButtonDashboard
	 */
	protected abstract Node createButtonDashboard();

	/**
	 * @return instantiated and formatted myLeftSubGrid
	 */
	protected abstract Node createLeftSubGrid();

	/**
	 * @return instantiated and formatted myRightSubGrid
	 */
	protected abstract Node createRightSubGrid();

	/**
	 * @return assembled and formatted Grid
	 */
	public Node buildNode() {
		return myGrid;
	}

}
