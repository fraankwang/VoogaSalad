package authoring.frontend.display_elements.grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.display_elements.grid_factories.GridFactory;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.interfaces.display_element_interfaces.IGrid;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	protected ButtonDashboard myButtonDashboard;

	protected String newPromptedString;

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
		column1.setPercentWidth(Constants.getInt("GRID_COLUMN_1"));
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(Constants.getInt("GRID_COLUMN_2"));
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(Constants.getInt("GRID_ROW_1"));
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(Constants.getInt("GRID_ROW_2"));
		myGrid.getColumnConstraints().addAll(column1, column2);
		myGrid.getRowConstraints().addAll(row1, row2);

	}

	/**
	 * Sets the Editor attribute panel information with information from the
	 * selected item just opened, or updates Unmodifiable attributes information
	 * with updated backend information.
	 * 
	 * @param info
	 */
	public abstract void setAttributesPanel(Map<String, String> info);

	/**
	 * Pulls various buttons from the grids and assigns hotkeys.
	 */
	public abstract void initializeHotKeys();

	/**
	 * Creates new window for user to input a String. This method is public to
	 * allow its TabDisplay wrapper to access it as well.
	 * 
	 * @return
	 */
	public String promptNewName(String name) {
		Stage promptStage = new Stage();
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter new " + name + ":");
		TextField textBox = new TextField();
		textBox.setMaxWidth(200);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(textBox);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button(Constants.getString("CANCEL_BUTTON"));
		Button saveButton = new Button(Constants.getString("SAVE_BUTTON"));
		cancelButton.setOnAction(e -> promptStage.close());
		textBox.setOnAction(e -> {
			newPromptedString = textBox.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			newPromptedString = textBox.getText();
			if (!newPromptedString.equals("")) {
				promptStage.close();
			}
		});
		buttonBox.getChildren().add(saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, Constants.getInt("PROMPT_NEW_WIDTH"),
				Constants.getInt("PROMPT_NEW_HEIGHT"));
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return newPromptedString;
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

}