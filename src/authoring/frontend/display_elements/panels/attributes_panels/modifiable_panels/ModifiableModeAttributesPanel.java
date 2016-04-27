package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	private static final int MODE_DESCRIPTION_HEIGHT = 15;

	public ModifiableModeAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(MODE_DESCRIPTION_HEIGHT);
		rowConstraints.add(100 - MODE_DESCRIPTION_HEIGHT);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		myRulesListView = createRulesListView();
		myRulesPane = new TitledPane("Rules", myRulesListView);

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);

		myAttributes = (List<String>) Arrays.asList("Easy", "Medium", "Hard", "Invincible");
		assembleEmptyInputRows();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myGridPane.add(myRulesPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	@Override
	protected void assembleEmptyInputRows() {
		myInputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

		ComboBox<String> cb = new ComboBox<String>();
		cb.setEditable(false);
		cb.setPromptText("Pick a Mode");

		for (int i = 0; i < myAttributes.size(); i++) {
			cb.getItems().add(myAttributes.get(i));
			// TODO: set on action to autofill some rules based on which mode
			// they pick
		}
		cb.getItems().add("Custom");

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));

		myAttributesMap.put("Mode", "");
		myInputMap.put("Mode", cb);

		myAttributesGridPane.add(text, 0, 0);
		myAttributesGridPane.add(myInputMap.get("Mode"), 1, 0);
	}

	@Override
	public void updateAttributes(Map<String, String> info) {
		myAttributesMap = info;
		refreshAttributes();
	}

	@Override // change to make refresh combo boxes instead
	@SuppressWarnings("unchecked")
	protected void refreshAttributes() {
		if (myInputMap != null) {
			String selectedMode = myAttributesMap.get("Mode");
			ComboBox<String> cb = (ComboBox<String>) myInputMap.get("Mode");
			cb.getSelectionModel().select(selectedMode);
			myInputMap.replace("Mode", cb);
		}

		refreshAttributeInputRows();
	}

	@Override
	protected void refreshAttributeInputRows() {
		myAttributesGridPane.getChildren().clear();

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));

		myAttributesGridPane.add(text, 0, 0);
		myAttributesGridPane.add(myInputMap.get("Mode"), 1, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Mode");
		myAttributesMap.replace("Mode",
				((ComboBox<String>) myInputMap.get("Mode")).getSelectionModel().getSelectedItem());

		System.out.println("*****4. ModifiableModeAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resetAttributes() {
		for (String s : myInputMap.keySet()) {
			ComboBox<String> inputArea = (ComboBox<String>) myInputMap.get(s);
			inputArea.getSelectionModel().clearSelection();
		}

	}

	@Override
	public void updateImageComponent(String imageName) {
		// null because no image component?
	}

}
