package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	private static final int MODE_DESCRIPTION_HEIGHT = 50;
	private static final List<String> DEFAULT_MODE_OPTIONS = Arrays.asList("Easy", "Medium", "Hard", "God Mode", "Infinite Resources", "Custom");

	
	public ModifiableModeAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		rowConstraints.add(MODE_DESCRIPTION_HEIGHT);
		rowConstraints.add(100 - MODE_DESCRIPTION_HEIGHT);

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);

		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();
		myAttributes = (List<String>) Arrays.asList("Name", "Starting Lives Multiplier", "Starting Resources Multiplier");
		assembleEmptyInputRows();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}
	
	@Override
	protected void assembleEmptyInputRows() {
		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));

			if (currentAttribute.equals("Mode")) {
				ComboBox<String> cb = new ComboBox<String>();
				cb.setEditable(false);
				cb.setPromptText("Pick a Mode");
				cb.getItems().addAll(DEFAULT_MODE_OPTIONS);
				myAttributesMap.put(currentAttribute, cb.getSelectionModel().getSelectedItem());
				myInputMap.put(currentAttribute, cb);
				
			} else {
				TextField tf = new TextField();
				tf.setEditable(true);
				myAttributesMap.put(currentAttribute, tf.getText());
				myInputMap.put(currentAttribute, tf);
				
			}

			myAttributesGridPane.add(text, 0, i);

		}
	}

	@Override
	public void updateAttributes(Map<String, String> info) {
		myAttributesMap = info;
		refreshAttributes();
	}

	@Override
	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				Control inputMethod = myInputMap.get(myAttributes.get(i));

				if (inputMethod instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
					tf.setText(myAttributesMap.get(myAttributes.get(i)));
					tf.setEditable(true);
					myInputMap.replace(myAttributes.get(i), tf);

				} else if (inputMethod instanceof ComboBox<?>) {
					@SuppressWarnings("unchecked")
					ComboBox<String> cb = (ComboBox<String>) myInputMap.get(myAttributes.get(i));
					cb.setValue(myAttributesMap.get(myAttributes.get(i)));
					cb.setEditable(false);
					myInputMap.replace(myAttributes.get(i), cb);
				}

			}

		}
		refreshAttributeInputRows();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Mode");

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		System.out.println("*****4. ModifiableEntityAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}

	@Override
	public void updateImageComponent(String imageName) {
		// null because no image component?
	}

}
