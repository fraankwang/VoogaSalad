package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	private static final int MODE_DESCRIPTION_HEIGHT = 50;
	private static final List<String> DEFAULT_MODE_ATTRIBUTES = Arrays.asList("Name", "InitialLives", "InitialResources");
	
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
		myAttributes = DEFAULT_MODE_ATTRIBUTES;
		assembleEmptyInputRows();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}
	

	@Override
	public void updateAttributes(Map<String, String> info) {
		myAttributesMap = info;
		myAttributes.remove("Levels");
		
		for (String attribute : DEFAULT_MODE_ATTRIBUTES) {
			TextField tf = new TextField();
			myInputMap.put(attribute, tf);
		}
		
		updateLevels();
		
		System.out.println(
				"*****3. ModifiableModeAttrPanel: updated myAttributesMap and myAttributes set with given unmodifiableattributespanel outputs:");
		System.out.println(myAttributesMap);
		refreshAttributes();
	}

	private void updateLevels() {
		System.out.println("levels updated");
	}

	@Override
	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
				tf.setText(myAttributesMap.get(myAttributes.get(i)));
				tf.setEditable(true);
				myInputMap.replace(myAttributes.get(i), tf);

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

}
