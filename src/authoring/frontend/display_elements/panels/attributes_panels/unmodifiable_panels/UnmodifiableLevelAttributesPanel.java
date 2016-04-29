package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import authoring.parser.GlobalParser;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

	private static final int SPAWN_ENTITIES_COLUMN_1 = 18;
	private static final int SPAWN_ENTITIES_COLUMN_2 = 50;
	private static final int SPAWN_ENTITIES_COLUMN_3 = 18;
	private static final int SPAWN_ENTITIES_COLUMN_4 = 23;
	private static final int SPAWN_ENTITIES_COLUMN_5 = 15;

	private static final List<String> SPAWN_ENTITIES_COLUMN_NAMES = (List<String>) Arrays.asList("PathID", "Name", "Wave", "Number",
			"Rate");

	public UnmodifiableLevelAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
		myDefaultAttributes = Arrays.asList("Name", "MapBackgroundImage", "LevelTimer",
				"WaveDelayTimer", "MapWidth", "MapHeight");
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
		myGridPane.setMaxWidth(MAX_SIZE);

		myAttributesGridPane = createAttributesGridPane(myDefaultAttributes);
		myOpenEditorButton = createOpenEditorButton();
		mySpawnEntitiesGridPane = createSpawnEntitiesGridPane();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(mySpawnEntitiesGridPane);
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
		addColumnNames(SPAWN_ENTITIES_COLUMN_NAMES, mySpawnEntitiesGridPane);

		return mySpawnEntitiesGridPane;
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myAttributesGridPane, 0, 1);
		myGridPane.add(myScrollPane, 0, 2);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane.getChildren().clear();

		System.out.println(
				"*****7. UnmodifiableLevelAttributesPanel: Levels display refreshed with updated myAttributesMap");
		System.out.println(myAttributesMap);

		populateSpawnEntitiesGridPane(mySpawnEntitiesGridPane,
				(TreeMap<String, String[]>) GlobalParser.spawnParse(myAttributesMap.get("SpawnEntities")));

		for (String currentAttribute : myAttributesMap.keySet()) {
			if (myOutputMap.containsKey(currentAttribute)) {
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

	/**
	 * Helper method that takes a GridPane and parses Spawn Entities to fill it.
	 * 
	 * @param gridPane
	 * @param map
	 */
	private void populateSpawnEntitiesGridPane(GridPane gridPane, TreeMap<String, String[]> map) {
		gridPane.getChildren().clear();
		addColumnNames(SPAWN_ENTITIES_COLUMN_NAMES, gridPane);

		int row = 1;
		for (String pathID : map.keySet()) {
			Text ID = new Text(pathID);
			ID.setFont(new Font(FONT_SIZE));

			gridPane.add(ID, 0, row);
			String[] spawnObjects = map.get(pathID);
			for (String spawn : spawnObjects) {
				String[] components = spawn.split("\\.");

				int column = 1;
				for (String component : components) {
					Label text = new Label(component);
					text.setFont(new Font(FONT_SIZE));
					gridPane.add(text, column, row);
					column++;
				}

				row++;
			}

			row++;
		}
		row = 1;

	}

	@Override
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("*****6: UnmodifiableAttrPanel: updated output info from updated backend");
		System.out.println(updatedInfo);
		myAttributesMap = updatedInfo;
		refreshDisplay();
	}

}
