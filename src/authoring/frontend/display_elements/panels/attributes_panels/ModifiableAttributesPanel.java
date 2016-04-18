package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.Panel;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public abstract class ModifiableAttributesPanel extends Panel {

	protected static final int FONT_SIZE = 21;
	protected BorderPane myWrapper;
	protected GridPane myGridPane;
	protected GridPane myAttributesGridPane;
	protected TitledPane myRulesPane;
	protected ListView<String> myRulesListView;

	protected Map<String, Control> myInputMap;
	protected Map<String, String> myAttributesMap;
	protected List<String> myAttributes;

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
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		myRulesListView = createRulesListView();
		myRulesPane = new TitledPane("Rules", myRulesListView);

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setGridLinesVisible(true);
		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myGridPane.add(myRulesPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		assembleRows();
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

	protected void assembleRows() {
		myInputMap = new HashMap<String, Control>();

		for (int i = 0; i < myAttributes.size(); i++) {
			Text text = new Text(myAttributes.get(i));
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setText("hi");
			tf.setEditable(true);
			
			myInputMap.put(myAttributes.get(i), tf);
			myAttributesGridPane.add(text, 0, i);
		}

	}

	public Map<String, String> saveAttributes() {
		myAttributesMap = new HashMap<String, String>();
		for (String s : myInputMap.keySet()) { 
			myAttributesMap.put(s, ((TextField) myInputMap.get(s)).getText());
		}

		return myAttributesMap;
	}

	public void setAttributes(Map<String, String> info) {
		myAttributesMap = info;

		for (String s : myInputMap.keySet()) {
			TextField tf = new TextField(myAttributesMap.get(s));
			tf.setText(myAttributesMap.get(s));
			tf.setEditable(true);
			myInputMap.replace(s, tf);
		}

		refreshAttributesGrid();
	}

	protected void refreshAttributesGrid() {
		for (int i = 0; i < myAttributes.size(); i++) {
			TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
			tf.setEditable(true);
			myInputMap.put(myAttributes.get(i), tf);
			myAttributesGridPane.add(tf, 1, i);
		}
	}
	
	public void resetAttributes() {
		System.out.println("reset");
		for (String s : myInputMap.keySet()) {
			TextField inputArea = (TextField) myInputMap.get(s);
			inputArea.clear();
		}

	}

}
