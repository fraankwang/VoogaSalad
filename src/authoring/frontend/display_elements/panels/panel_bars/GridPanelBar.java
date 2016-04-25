package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.GridViewPanel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * The GridPanelBar contains a description as well as controls to change the
 * number of columns displaying the various sprites (or modes/levels).
 * 
 * @author Frank
 *
 */

public class GridPanelBar extends PanelBar {

	private Label myDescription;
	private HBox myGridBar;
	private Button myIncreaseColumnsButton;
	private Button myDecreaseColumnsButton;
	private GridViewPanel myGridView;

	public GridPanelBar(double height, double width, GridViewPanel grid) {
		super(height, width);
		myGridView = grid;
	}

	@Override
	protected void initializeComponents() {
		myDescription = new Label();
		myIncreaseColumnsButton = new Button("+");
		myDecreaseColumnsButton = new Button("-");
	}

	@Override
	protected void assembleComponents() {
		myGridBar = new HBox();
		myGridBar.setAlignment(Pos.CENTER_LEFT);
		myGridBar.setSpacing(10);
		Label numColumns = new Label("# of columns");
		myDecreaseColumnsButton.setOnAction(e -> myGridView.decreaseGridSize());
		myIncreaseColumnsButton.setOnAction(e -> myGridView.increaseGridSize());
		HBox.setHgrow(myDecreaseColumnsButton, Priority.ALWAYS);
		HBox.setHgrow(numColumns, Priority.ALWAYS);
		HBox.setHgrow(myIncreaseColumnsButton, Priority.ALWAYS);
		HBox columnSelector = new HBox(myDecreaseColumnsButton, numColumns, myIncreaseColumnsButton);
		myGridBar.getChildren().addAll(myDescription, columnSelector);
		columnSelector.setAlignment(Pos.CENTER_RIGHT);
		myNode = myGridBar;
	}
	
	public void setDescription(String description) {
		myDescription.setText("You are currently viewing your " + description);
	}

}
