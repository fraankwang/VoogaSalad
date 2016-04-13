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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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
	//scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	
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
	
	/**
	 * Creates GridPane with set row and column constraints.
	 * 
	 * @return
	 */
	protected GridPane createGridWrapper(List<Integer> rowConstraints, List<Integer> columnConstraints) {
		GridPane grid = new GridPane();

		for (Integer i : rowConstraints) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(i);
			grid.getRowConstraints().add(row);
		}
		
		for (Integer i : columnConstraints) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(i);
			grid.getColumnConstraints().add(column);
		}
		
		return grid;
	}

	private GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(50);
		columnConstraints.add(50);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);

		myAttributesGridPane.setPrefSize(600, 600);
		return myAttributesGridPane;

	}
	
	/**
	 * Creates a TitledPane given name and Node
	 * 
	 * @return
	 */
	protected TitledPane createTitledPane(String name, Node node) {
		TitledPane tp = new TitledPane(name, node);
		tp.setPrefSize(600, 600);
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
	
