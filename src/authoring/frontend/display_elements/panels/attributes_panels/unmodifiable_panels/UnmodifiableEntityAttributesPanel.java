package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableEntityAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;

	public UnmodifiableEntityAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);

		List<String> entityAttributes = (List<String>) Arrays.asList("Genre", "Name");
		myAttributesGridPane = createAttributesGridPane(entityAttributes);
		myOpenEditorButton = createOpenEditorButton();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myScrollPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane.getChildren().clear();

		System.out.println("*****6. UnmodifiableEntityAttributesPanel: display refreshed with updated myAttributesMap");
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
}
