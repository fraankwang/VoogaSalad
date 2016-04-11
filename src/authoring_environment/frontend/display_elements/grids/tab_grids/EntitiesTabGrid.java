package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories.EntitiesTabGridFactory;
import authoring_environment.frontend.display_elements.grids.TabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabGrid extends TabGrid {

	public EntitiesTabGrid(ITabDisplay tab) {
		super(tab);
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntitiesTabGridFactory(myTabDisplay);
	}

	@Override
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
	    
	    myGrid.add(myPrimaryDisplay, 0, 0);
	    myGrid.add(myRightSubGrid, 1, 0);
	    myGrid.add(myLeftSubGrid, 0, 1);
	    myGrid.add(myButtonDashboard, 1, 1);
	}

}
