package authoring.frontend.display_elements.grids;

import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.GridFactory;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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

	protected IAuthoringView myController;
	protected GridFactory myGridFactory;
	protected GridPane myGrid;
	protected Panel myPrimaryDisplay;
	protected Panel myButtonDashboard;

	/**
	 * Upon instantiation of a Grid, the grid components are initialized and
	 * assembled (both through the inheriting subclass' abstract creation
	 * methods).
	 */
	public Grid(IAuthoringView controller) {
		myController = controller;

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

	}

	/**
	 * Pieces together the four primary components of the Grid within the Node
	 * myGrid. myGrid's four main quadrants are assembled, but must be further
	 * extended within subclasses.
	 */
	protected void assembleGridComponents() {
		myGrid = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(70);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(30);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(70);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(30);
		myGrid.getColumnConstraints().addAll(column1, column2);
		myGrid.getRowConstraints().addAll(row1, row2);

	}

	/**
	 * @return assembled and formatted Grid
	 */
	public Node getNode() {
		return myGrid;
	}
	
	public Panel getPrimaryDisplay() {
		return myPrimaryDisplay;
	}
	
	public abstract void setAttributesPanel(List<Map<String, String>> info);
	
	
}