package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The RulesEditorPanel contains two scrollable views of "if" and "then"
 * conditions, which together create a rule.
 * 
 * @author Frank
 *
 */

public class RulesEditorPanel extends Panel {

	private final int ARBITRARY_PANEL_SIZE = 300;
	private final int COLUMN_1_WIDTH_PCT = 10;
	private final int COLUMN_2_WIDTH_PCT = 90;
	private SimpleButtonDashboard mySimpleButtonDashboard;
	private Node myIfNode;
	private Node myThenNode;
	private Button myAddNewIfButton;
	private Button myAddNewThenButton;
	private ListView<String> myIfStatements;
	private ListView<String> myThenStatements;

	public RulesEditorPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		mySimpleButtonDashboard = new SimpleButtonDashboard(ARBITRARY_PANEL_SIZE, ARBITRARY_PANEL_SIZE);
		myAddNewIfButton = new Button("Add New If");
		myAddNewThenButton = new Button("Add New Then");
		myIfStatements = new ListView<String>();
		myThenStatements = new ListView<String>();
		myIfNode = new GridPane();
		myThenNode = new GridPane();
	}

	@Override
	protected void assembleComponents() {
		GridPane grid = createGridWrapper(COLUMN_1_WIDTH_PCT, COLUMN_2_WIDTH_PCT);
		VBox ifbox = createVBoxWrapper("If Statements", myAddNewIfButton);
		VBox thenbox = createVBoxWrapper("Then Statements", myAddNewThenButton);

		HBox ifthen = new HBox(createSubNode(myIfNode, ifbox, myIfStatements),
				createSubNode(myThenNode, thenbox, myThenStatements));

		grid.add(ifthen, 1, 0);
		grid.add(mySimpleButtonDashboard.buildNode(), 0, 0);
		myNode = grid;
	}

	/**
	 * Creates GridPane with set column constraints.
	 * 
	 * @param percentwidth1
	 * @param percentwidth2
	 * @return
	 */
	private GridPane createGridWrapper(int percentwidth1, int percentwidth2) {
		GridPane grid = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(percentwidth1);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(percentwidth2);
		grid.getColumnConstraints().addAll(column1, column2);
		return grid;
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
		// formatting here
		vb.getChildren().addAll(label, button);
		return vb;
	}

	/**
	 * Creates a sub-GridPane that pairs up the VBox containing the label and
	 * button with the listview.
	 * 
	 * @param node
	 * @param vbox
	 * @param listview
	 * @return
	 */
	private Node createSubNode(Node node, VBox vbox, ListView<String> listview) {
		((GridPane) node).add(vbox, 0, 0);
		((GridPane) node).add(listview, 1, 0);
		return node;
	}

}
