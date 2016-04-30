package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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

public abstract class ModifiableAttributesPanel extends AttributesPanel {

	protected BorderPane myWrapper;
	protected GridPane myGridPane;
	protected GridPane myAttributesGridPane;
	protected ScrollPane myScrollPane;
	protected TitledPane myRulesPane;
	protected ListView<String> myRulesListView;
	protected IAuthoringView myController;
	
	protected Map<String, Control> myInputMap;

	protected static final int RULES_HEIGHT_PERCENTAGE = 30;
	protected static final int ATTRIBUTES_HEIGHT = 50;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	private static final int DEFAULT_ATTRIBUTES_HEIGHT = 600;
	private static final int COLUMN_1_PERCENTAGE = 50;
	private static final int COLUMN_2_PERCENTAGE = 50;

	public ModifiableAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width);
		myController = controller;
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myAttributes = new ArrayList<String>();
		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();
		myAttributesGridPane = createAttributesGridPane();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);
		myScrollPane.setFitToWidth(false);

		assembleEmptyInputRows();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myScrollPane, 0, 0);
		// myGridPane.add(myRulesPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	
	protected GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributesGridPane.setPrefSize(ATTRIBUTES_PANEL_WIDTH, DEFAULT_ATTRIBUTES_HEIGHT);
		return myAttributesGridPane;

	}

	
	/**
	 * Populates myAttributesMap and myInputMap using given myAttributes.
	 */
	protected void assembleEmptyInputRows() {
		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setEditable(true);

			myAttributesMap.put(currentAttribute, tf.getText());
			myAttributesGridPane.add(text, 0, i);

		}
	}

	
	/**
	 * Assumes myAttributesMap is correctly populated. Iterates through
	 * myInputMap to replace the input areas for each component (in
	 * myAttributes) with the existing value in myAttributesMap. After
	 * myInputMap is updated, this method calls refreshInputRows.
	 */
	protected abstract void refreshAttributes();

	
	/**
	 * Assumes myAttributes and myInputMap are up to date with all necessary
	 * components. Clears myAttributesGridPane and re-populates it using
	 * myAttributes and the mapped Control in myInputMap.
	 */
	protected void refreshAttributeInputRows() {

		myAttributesGridPane.getChildren().clear();

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			if (!currentAttribute.equals("Type") && !currentAttribute.equals("SpawnEntities")){
				Text text = new Text(currentAttribute);
				text.setFont(new Font(FONT_SIZE));
				
				myAttributesGridPane.add(text, 0, i);
				myAttributesGridPane.add(myInputMap.get(currentAttribute), 1, i);
				
			}
		}

	}

	
	/**
	 * Update image display based on attribute image name.
	 * @param image
	 */
	public abstract void updateImageComponent(String image);
	
	
	/**
	 * Creates confirmation before allowing user to reset all input values.
	 * 
	 * @return
	 */
	public boolean createResetAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Confirmation");
		alert.setHeaderText("Select Option");
		alert.setContentText("Are you sure you want to reset?");
		alert.setWidth(700);

		ButtonType confirmContinue = new ButtonType("Restart");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(confirmContinue, cancel);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == confirmContinue) {
			resetAttributes();
			return false;
		}
		return false;
	}

	/**
	 * Iterates through and parses every input Control in myInputMap to clear
	 * the input area.
	 */
	public void resetAttributes() {
		for (String s : myInputMap.keySet()) {
			Control input = myInputMap.get(s);
			if (input instanceof TextField) {
				TextField tf = (TextField) input;
				tf.clear();
			}

			if (input instanceof ComboBox<?>) {
				@SuppressWarnings("unchecked")
				ComboBox<String> cb = (ComboBox<String>) input;
				cb.getSelectionModel().clearSelection();
			}
		}
	}

	/**
	 * Iterates through everything in myInputMap to parse the type of Control
	 * and to replace the corresponding attribute value in myAttributesMap with
	 * the user input. Type is specified by subclasses and placed in
	 * myAttributesMap as well.
	 * 
	 * @return Map of fully populated attributes entered by user, along with
	 *         Type.
	 */
	public abstract Map<String, String> saveAttributes();

	/**
	 * Populates myAttributesMap and myAttributes from updated backend in @param
	 * info. myInputMap is cleared and repopulated in the subclasses' extensions
	 * of this method.
	 * 
	 * @param info
	 */
	public void updateAttributes(Map<String, String> info) {
		myAttributesMap = info;
		myAttributes = new ArrayList<String>();
		myAttributes.addAll(myAttributesMap.keySet());
		System.out.println(
				"*****3. ModifiableAttrPanel: myAttributesMap set with given unmodifiableattributespanel outputs:");
		System.out.println(myAttributesMap);
		myInputMap.clear();

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
