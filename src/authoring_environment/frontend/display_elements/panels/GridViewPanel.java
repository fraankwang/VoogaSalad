package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring_environment.frontend.display_elements.panels.panel_bars.PanelBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * The GridViewPanel is the primary display within many of the TabDisplays. The
 * GridView contains a scrollable GridPane that shows all the existing game
 * sprites (or levels/modes) created. The PanelBar allows  
 * 
 * @author Frank
 *
 */

public class GridViewPanel extends Panel {

	private final static int DEFAULT_NUM_GRID_COLUMNS = 2;
	
	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private PanelBar myPanelBar;
	private Button myAddNewButton;
	private int numColumns;

	public GridViewPanel(int height, int width) {
		super(height, width);
		numColumns = DEFAULT_NUM_GRID_COLUMNS;
	}

	@Override
	protected void initializeComponents() {
		myGridPane = new GridPane();
	    myScrollPane = new ScrollPane();
	    myPanelBar = new GridPanelBar(50,50, this);
	}

	private void sizeGrid(int num) {
		for (int i = 0; i < num; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(100/num);
			myGridPane.getColumnConstraints().add(column);
		}
	}
	
	public void decreaseGridSize() {
		numColumns--;
		sizeGrid(numColumns);
	}
	
	public void increaseGridSize() {
		numColumns++;
		sizeGrid(numColumns);
	}
	
	@Override
	protected void assembleComponents() {
		VBox vbox = new VBox();
		myGridPane.setGridLinesVisible(true);
	    sizeGrid(numColumns);
	    myScrollPane.setContent(myGridPane);
	    VBox.setVgrow(myGridPane, Priority.ALWAYS);
		vbox.getChildren().addAll(myPanelBar.buildNode(), myScrollPane);
		myNode = vbox;
	}

}
