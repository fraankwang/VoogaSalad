package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * The RulesEditorPanel contains two scrollable views of "if" and "then"
 * conditions, which together create a rule.
 * 
 * @author Frank
 *
 */

public class RulesEditorPanel extends Panel {

	private final int COLUMN_1_WIDTH_PERCENTAGE = 10;
	private final int COLUMN_2_WIDTH_PERCENTAGE = 15;
	private final int COLUMN_3_WIDTH_PERCENTAGE = 30;
	private final int COLUMN_4_WIDTH_PERCENTAGE = 15;
	private final int COLUMN_5_WIDTH_PERCENTAGE = 30;
	private EditorButtonDashboard mySimpleButtonDashboard;
	private Button myAddNewIfButton;
	private Button myAddNewThenButton;
	private ListView<String> myIfStatements;
	private ListView<String> myThenStatements;

	public RulesEditorPanel(double height, double width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		mySimpleButtonDashboard = new EditorButtonDashboard(MAX_SIZE, MAX_SIZE);
		mySimpleButtonDashboard.initialize();
		myAddNewIfButton = new Button("Add New If");
		myAddNewThenButton = new Button("Add New Then");
		myIfStatements = new ListView<String>();
		myThenStatements = new ListView<String>();

	}

	@Override
	protected void assembleComponents() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_2_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_3_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_4_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_5_WIDTH_PERCENTAGE);

		GridPane grid = createGridWrapper(rowConstraints, columnConstraints);

		VBox ifbox = createVBoxWrapper("If Statements", myAddNewIfButton);
		VBox thenbox = createVBoxWrapper("Then Statements", myAddNewThenButton);

		grid.add(mySimpleButtonDashboard.getNode(), 0, 0);
		grid.add(ifbox, 1, 0);
		grid.add(myIfStatements, 2, 0);
		grid.add(thenbox, 3, 0);
		grid.add(myThenStatements, 4, 0);
		myNode = grid;

	}

	/**
	 * Creates a VBox wrapper containing a Label and Button.
	 * 
	 * @param labelText
	 * @param button
	 * @return
	 */
	private VBox createVBoxWrapper(String labelText, Button button) {
		VBox vb = new VBox();
		Label label = new Label(labelText);
		label.setPrefSize(MAX_SIZE, MAX_SIZE);
		button.setPrefSize(MAX_SIZE, MAX_SIZE);
		vb.getChildren().addAll(label, button);
		return vb;

	}

}
