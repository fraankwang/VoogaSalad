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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableLevelAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private GridPane mySpawnEntitiesGridPane;

	private static final int SPAWN_ENTITIES_COLUMN_1 = 60;
	private static final int SPAWN_ENTITIES_COLUMN_2 = 10;
	private static final int SPAWN_ENTITIES_COLUMN_3 = 10;
	private static final int SPAWN_ENTITIES_COLUMN_4 = 10;
	private static final int SPAWN_ENTITIES_COLUMN_5 = 10;
	
	private List<String> columnNames = (List<String>) Arrays.asList("Name", "PathID", "WaveNumber", "Number", "Rate");


	public UnmodifiableLevelAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {

		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		int attributesHeight = (100 - BUTTON_HEIGHT_PERCENTAGE) / 2;
		int spawnEntitiesHeight = (100 - BUTTON_HEIGHT_PERCENTAGE) / 2;
		rowConstraints.add(attributesHeight);
		rowConstraints.add(spawnEntitiesHeight);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);

		List<String> levelAttributes = (List<String>) Arrays.asList("Name", "MapBackgroundImage", "LevelTimer",
				"WaveDelayTimer", "MapWidth", "MapHeight");

		myAttributesGridPane = createAttributesGridPane(levelAttributes);
		myOpenEditorButton = createOpenEditorButton();
		mySpawnEntitiesGridPane = createSpawnEntitiesGridPane();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);
	}

	/**
	 * Assembles initial Grid set up for displaying Spawn Entities
	 * 
	 * @return
	 */
	private GridPane createSpawnEntitiesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_1);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_2);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_3);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_4);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_5);

		mySpawnEntitiesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		mySpawnEntitiesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);

		addColumnNames(columnNames, mySpawnEntitiesGridPane);

		return mySpawnEntitiesGridPane;
	}

	/**
	 * Helper method for creating and repopulating Grids
	 * 
	 * @param names
	 * @param gridPane
	 */
	private void addColumnNames(List<String> names, GridPane gridPane) {
		for (int i = 0; i < names.size(); i++) {
			Text text = new Text(names.get(i));
			text.setFont(new Font(FONT_SIZE));
			mySpawnEntitiesGridPane.add(text, i, 0);
		}
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myAttributesGridPane, 0, 1);
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

		// get SpawnEntities from myAttributesMap and parse to Map<String,
		// String>
		// loop through map keyset and add numbers to correct columns

		mySpawnEntitiesGridPane.getChildren().clear();
		addColumnNames()

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
