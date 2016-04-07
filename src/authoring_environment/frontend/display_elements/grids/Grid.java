package authoring_environment.frontend.display_elements.grids;

import authoring_environment.frontend.display_elements.grid_factories.GridFactory;
import authoring_environment.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.scene.Node;

/**
 * The Grid superclass is the object that populates all the EditorDisplays and
 * TabDisplays. Each of these displays contains four elements: the primary
 * display, the button dashboard, and the left and right wrapper subgrids. All
 * of these elements are contained within the myGrid variable, which is created
 * in the buildNode() method.
 * 
 * @author Frank
 *
 */

public abstract class Grid implements IGrid {

	protected GridFactory myGridFactory;
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
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
	}

	/**
	 * Initializes GridFactory so that Grid components can be build
	 */
	protected abstract void initializeGridFactory();

	/**
	 * Initializes the four primary components of the Grid.
	 */
	protected void initializeGrid() {
		myPrimaryDisplay = myGridFactory.createPrimaryDisplay();
		myButtonDashboard = myGridFactory.createButtonDashboard();
		myLeftSubGrid = myGridFactory.createLeftSubGrid();
		myRightSubGrid = myGridFactory.createRightSubGrid();
	}

	/**
	 * Pieces together the four primary components of the Grid within the Node
	 * myGrid.
	 */
	protected abstract void assembleGridComponents();

	/**
	 * @return assembled and formatted Grid
	 */
	public Node buildNode() {
		return myGrid;
	}

}
