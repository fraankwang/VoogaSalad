package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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

public class UnmodifiableModeAttributesPanel extends UnmodifiableAttributesPanel {

	private static final int MODE_DESCRIPTION_HEIGHT = 5;
	private BorderPane myWrapper;
	private TitledPane myRulesTitledPane;
	private GridPane myGridPane;
	private Button myOpenEditorButton;
	private ListView<String> myListView;

	public UnmodifiableModeAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	/**
	 * 
	 * Change UI to show all levels/entities per Mode?
	 * 
	 */
	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(MODE_DESCRIPTION_HEIGHT);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE - MODE_DESCRIPTION_HEIGHT);

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myListView = createModeRulesListView();

		List<String> modeAttributes = (List<String>) Arrays.asList("Easy", "Medium", "Hard", "Invincible");
		myAttributesGridPane = createAttributesGridPane(modeAttributes);

		myRulesTitledPane = new TitledPane("Rules", myListView);
		myOpenEditorButton = createOpenEditorButton();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myAttributesGridPane, 0, 1);
		myGridPane.add(myRulesTitledPane, 0, 2);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	@Override

	protected void initializeMaps() {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));
		TextField tf = new TextField();
		tf.setEditable(false);

		myOutputMap.put("Mode", tf);
		myAttributesMap.put("Mode", tf.getText());
		
	}

	@Override
	protected GridPane assembleEmptyOutputRows(GridPane gridPane, List<String> attributes,
			Map<String, Control> outputMap) {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));
		
		gridPane.add(text, 0, 0);
		gridPane.add(outputMap.get("Mode"), 1, 0);
		
		return gridPane;

	}

	private ListView<String> createModeRulesListView() {
		ListView<String> lv = new ListView<String>();
		lv.setCellFactory(TextFieldListCell.forListView());
		ContextMenu cm = new ContextMenu();
		cm.getItems().add(new MenuItem("context menu text"));
		lv.setContextMenu(cm);

		lv.setEditable(true);

		lv.getItems().add("Easy");
		lv.getItems().add("Medium");
		lv.getItems().add("Hard");

		return lv;
	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane.getChildren().clear();

		System.out.println("Unm..ModeAttributesPanel: myAttributesMap after update: ");
		System.out.println(myAttributesMap);

		TextField tf = (TextField) myOutputMap.get("Mode");
		tf.setText(myAttributesMap.get("Mode"));
		tf.setEditable(false);

		myOutputMap.replace("Mode", tf);

		refreshRows();
		myGridPane.getChildren().clear();
		assembleComponents();
	}

	@Override
	protected void refreshRows() {
		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));
		myAttributesGridPane.add(text, 0, 0);
		myAttributesGridPane.add(myOutputMap.get("Mode"), 1, 0);
	}

	@Override
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("*****6: UnmodifiableAttrPanel: updated output info from updated backend");
		System.out.println(updatedInfo);
		myAttributesMap.put("Type", "Mode");
		myAttributesMap = updatedInfo;
		refreshDisplay();
	}
}
