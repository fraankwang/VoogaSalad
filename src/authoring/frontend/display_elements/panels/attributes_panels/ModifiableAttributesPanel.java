package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import authoring.frontend.editor_features.ComponentSelector;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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

public abstract class ModifiableAttributesPanel extends AttributesPanel {

	protected BorderPane myWrapper;
	protected GridPane myGridPane;
	protected GridPane myAttributesGridPane;
	protected ScrollPane myScrollPane;
	protected TitledPane myRulesPane;
	protected ListView<String> myRulesListView;

	protected Map<String, Control> myInputMap;

	protected static final int RULES_HEIGHT_PERCENTAGE = 30;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	private static final int DEFAULT_ATTRIBUTES_HEIGHT = 600;
	private static final int COLUMN_1_PERCENTAGE = 50;
	private static final int COLUMN_2_PERCENTAGE = 50;

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
		myAttributesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);
		myAttributes = new ArrayList<String>();
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myScrollPane, 0, 0);
		myGridPane.add(myRulesPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	protected GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributesGridPane.setPrefSize(MAX_SIZE, DEFAULT_ATTRIBUTES_HEIGHT);
		return myAttributesGridPane;

	}

	protected void assembleInputRows() {
		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setEditable(false);

			myAttributesMap.put(currentAttribute, tf.getText());
			myAttributesGridPane.add(text, 0, i);

		}
		
		createAddComponentButton();
	}
	
	protected void refreshInputRows() {
		myAttributesGridPane.getChildren().clear();
		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));

			myAttributesGridPane.add(text, 0, i);
			myAttributesGridPane.add(myInputMap.get(currentAttribute), 1, i);
		}
		
		createAddComponentButton();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			}
			else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}
		System.out.println("*****3. ModifiableAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);
		return myAttributesMap;
	}

	public void setAttributes(Map<String, String> info) {
		myAttributesMap = info;
		myAttributes.clear();
		myAttributes.addAll(myAttributesMap.keySet());
		myInputMap.clear();
		ComponentSelector selector = new ComponentSelector();
		selector.initialize();
		myInputMap = selector.setComponents(myInputMap, myAttributes);
		System.out.println("*****2. ModifiableAttrPanel: myAttributesMap set with given unmodifiableattributespanel outputs:");
		System.out.println(myAttributesMap);
		refreshAttributes();

	}

	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				if (myInputMap.get(myAttributes.get(i)) instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
					tf.setText(myAttributesMap.get(myAttributes.get(i)));
					tf.setEditable(true);
					myInputMap.replace(myAttributes.get(i), tf);
				}
				else if (myInputMap.get(myAttributes.get(i)) instanceof ComboBox<?>) {
					@SuppressWarnings("unchecked")
					ComboBox<String> cb = (ComboBox<String>) myInputMap.get(myAttributes.get(i));
					cb.setValue(myAttributesMap.get(myAttributes.get(i)));
					cb.setEditable(false);
					myInputMap.replace(myAttributes.get(i), cb);
				}
				
			}

		}
		refreshInputRows();
	}
	
	public void createAddComponentButton() {
		Button addComponentButton = new Button("Add Component");
		addComponentButton.setFont(new Font(20));
		myAttributesGridPane.add(addComponentButton, 0, myAttributes.size());
		GridPane.setColumnSpan(addComponentButton, 2);
		
		addComponentButton.setOnAction(e -> {
			ComponentSelector selector = new ComponentSelector();
			selector.initialize();
			Map<String, Control> newComponents = selector.openSelector(myInputMap);
			for (String key: newComponents.keySet()) {
				if (!myAttributes.contains(key)) {
					myAttributes.add(key);
					myAttributesMap.put(key, null);
					myInputMap.put(key, newComponents.get(key));
					refreshInputRows();
				}
			}
		});
	}

	public boolean createResetAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Confirmation");
		alert.setHeaderText("Select Option");
		alert.setContentText(
				"Are you sure you want to reset? Press Confirm and Continue to resume editing from scratch.");
		alert.setWidth(700);

		ButtonType confirmQuit = new ButtonType("Yes - Exit");
		ButtonType confirmContinue = new ButtonType("Restart");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(confirmQuit, confirmContinue, cancel);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == confirmQuit) {
			resetAttributes();
			return true;
		} else if (result.get() == confirmContinue) {
			resetAttributes();
			return false;
		}
		return false;
	}

	public void resetAttributes() {
		for (String s : myInputMap.keySet()) {
			TextField inputArea = (TextField) myInputMap.get(s);
			inputArea.clear();
		}
	}

	protected ListView<String> createRulesListView() {
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
