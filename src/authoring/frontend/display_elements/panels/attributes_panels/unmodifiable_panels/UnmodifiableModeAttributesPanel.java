package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Frank
 *
 */

public class UnmodifiableModeAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private Button myOpenEditorButton;
	
	public UnmodifiableModeAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
		myDefaultAttributes = Arrays.asList("Name", "Starting Lives Multiplier", "Starting Resources Multiplier");
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE);

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(MAX_SIZE);
		
		myAttributesGridPane = createAttributesGridPane(myDefaultAttributes);
		myOpenEditorButton = createOpenEditorButton();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myAttributesGridPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane.getChildren().clear();

		System.out.println("UnmodifiableModeAttributesPanel: Modes display refreshed with updated myAttributesMap");
		System.out.println(myAttributesMap);

		for (String currentAttribute : myAttributesMap.keySet()) {

			if (myOutputMap.keySet().contains(currentAttribute)) {
				TextField tf = (TextField) myOutputMap.get(currentAttribute);
				tf.setText(myAttributesMap.get(currentAttribute));
				tf.setEditable(false);
				myOutputMap.replace(currentAttribute, tf);

			}

			else {
				TextField tf = new TextField();
				tf.setText(myAttributesMap.get(currentAttribute));
				tf.setEditable(false);
				myOutputMap.put(currentAttribute, tf);
			}

		}

		refreshRows();
		myGridPane.getChildren().clear();
		assembleComponents();
	}

	@Override
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("*****6: UnmodifiableAttrPanel: updated output info from updated backend");
		System.out.println(updatedInfo);
		myAttributesMap = updatedInfo;
		refreshDisplay();
	}

}
