package authoring_environment.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.List;
import authoring_environment.frontend.display_elements.panels.Panel;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public abstract class ModifiableAttributesPanel extends Panel {

	protected BorderPane myWrapper;
	protected GridPane myGridPane;
	protected ScrollPane myScrollPane;
	protected GridPane myAttributesGridPane;
	protected TitledPane myRulesPane;
	protected ListView<String> myRulesListView;

	protected final int RULES_HEIGHT_PERCENTAGE = 30;
	protected final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	private final int DEFAULT_ATTRIBUTES_HEIGHT = 600;
	private final int COLUMN_1_PERCENTAGE = 50;
	private final int COLUMN_2_PERCENTAGE = 50;
	
	public ModifiableAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(100 - RULES_HEIGHT_PERCENTAGE);
		rowConstraints.add(RULES_HEIGHT_PERCENTAGE);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);

		myRulesListView = createRulesListView();
		myRulesPane = createTitledPane("Rules", myRulesListView);

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setGridLinesVisible(true);
		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myScrollPane, 0, 0);
		myGridPane.add(myRulesPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	private GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributesGridPane.setPrefSize(MAX_SIZE, DEFAULT_ATTRIBUTES_HEIGHT);
		return myAttributesGridPane;

	}

	/**
	 * Creates a TitledPane given name and Node
	 * 
	 * @return
	 */
	protected TitledPane createTitledPane(String name, Node node) {
		TitledPane tp = new TitledPane(name, node);
		tp.setPrefSize(MAX_SIZE, MAX_SIZE);
		return tp;

	}

	private ListView<String> createRulesListView() {
		ListView<String> lv = new ListView<String>();
		lv.setCellFactory(TextFieldListCell.forListView());
		ContextMenu cm = new ContextMenu();
		cm.getItems().add(new MenuItem("context menu text"));
		lv.setContextMenu(cm);
		lv.setEditable(true);
		lv.getItems().add("hello!");
		lv.getItems().add("helloooo");

		return lv;
	}
}
