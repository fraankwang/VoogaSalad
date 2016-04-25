package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
//import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
//import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableLevelAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	// private TitledPane myRulesTitledPane;
	private GridPane myGridPane;
	// private ListView<String> myRulesListView;

	private ScrollPane myScrollPane;

	public UnmodifiableLevelAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
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

		List<String> levelAttributes = (List<String>) Arrays.asList("LevelTimer", "MapHeight", "MapBackgroundImage",
				"MapWidth", "Name", "WaveDelayTimer");
		myAttributesGridPane = createAttributesGridPane(levelAttributes);
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

		System.out.println(
				"*****7. UnmodifiableLevelAttributesPanel: Levels display refreshed with updated myAttributesMap");
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

		//TODO: add waves, entities per wave, number of entities each, wave -> path ID
		refreshRows();
		myGridPane.getChildren().clear();
		assembleComponents();
	}
	
	@Override
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("*****6: UnmodifiableAttrPanel: updated output info from updated backend");
		System.out.println(updatedInfo);
		myAttributesMap.put("Type", "Level");
		myAttributesMap = updatedInfo;
		refreshDisplay();
	}

}
