package engine.frontend.status;

/**
 * @author austinwu
 */
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.SubScene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import utility.hud.AbstractHUDScreen;
import utility.hud.Property;
import utility.hud.ValueChange;

public class DrumpfHUDScreen extends AbstractHUDScreen {
	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_WIDTH = 1000;

	private Map<String, Property> status;
	Map<String, Integer> valueToRowMap;
	Map<Integer, String> rowToValueMap;
	private SubScene myScene;
	ObservableList<String> keys;
	ObservableList<String> values;

	private HBox container;

	/**
	 * Instantiates HUD utility
	 * 
	 * @param width
	 *            - width of HUD Screen
	 * @param height
	 *            - height of HUD Screen
	 * @param status
	 *            - Map relating string keys (statistic name) to properties
	 *            (statistic value)
	 * @param rowToValueMap
	 *            - Map indicating which Keys should be located in which index
	 *            of the HUD
	 */
	public DrumpfHUDScreen(double width, double height, Map<String, Property> status,
			Map<Integer, String> rowToValueMap) {
		this.status = status;
		this.rowToValueMap = rowToValueMap;
		this.valueToRowMap = new HashMap<String, Integer>();
		for (int i = 0; i < rowToValueMap.size(); i++) {
			valueToRowMap.put(rowToValueMap.get(i), i);
		}
		init();
	}

	/**
	 * Overloaded HUD instantiation - uses default width and length parameters
	 * 
	 * @param status
	 *            - Map relating string keys to
	 * @param rowToValueMap
	 *            - Map indicating which Keys should be located in which index
	 *            of the HUD
	 */
	public DrumpfHUDScreen(Map<String, Property> status, Map<Integer, String> rowToValueMap) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, status, rowToValueMap);
	}

	/**
	 * Overloaded HUD instantiation - creates rowToValueMap in default order of
	 * status key indexing
	 * 
	 * @param width
	 *            - width of HUD screen
	 * @param height
	 *            - height of HUD screen
	 * @param status
	 *            - Map relating string keys (statistic name) to properties
	 *            (statistic value)
	 */
	public DrumpfHUDScreen(double width, double height, Map<String, Property> status) {
		this(width, height, status, new HashMap<Integer, String>());
		int i = 0;
		for (String value : status.keySet()) {
			rowToValueMap.put(i, value);
			valueToRowMap.put(value, i);
			i++;
		}
	}

	/**
	 * Overloaded HUD instantiation - uses default width and height while
	 * creating rowToValueMap in default order of status key indexing
	 * 
	 * @param status
	 *            - Map relating string keys (statistic name) to properties
	 *            (statistic value)
	 */
	public DrumpfHUDScreen(Map<String, Property> status) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, status);
	}

	/**
	 * Initializes the HUD
	 */
	public void init() {
		keys = FXCollections.observableArrayList();
		values = FXCollections.observableArrayList();
		ListView<String> keyView = new ListView<>(keys);
		ListView<String> valueView = new ListView<>(values);

		container = new HBox();
		container.getChildren().addAll(keyView, valueView);

		keyView.minWidthProperty().bind(container.widthProperty().divide(container.getChildren().size()));
		keyView.maxWidthProperty().bind(container.widthProperty().divide(container.getChildren().size()));
		keyView.minHeightProperty().bind(container.heightProperty());
		keyView.maxHeightProperty().bind(container.heightProperty());

		valueView.minWidthProperty().bind(container.widthProperty().divide(container.getChildren().size()));
		valueView.maxWidthProperty().bind(container.widthProperty().divide(container.getChildren().size()));
		valueView.minHeightProperty().bind(container.heightProperty());
		valueView.maxHeightProperty().bind(container.heightProperty());

		for (int i = 0; i < status.size(); i++) {
			keys.add(rowToValueMap.get(i));
			values.add(status.get(rowToValueMap.get(i)).toString());
		}
	}

	/**
	 * Not returning SubScene, so this will always be null
	 */
	public SubScene getScene() {
		return myScene;
	}

	/**
	 * Returns the HBox containing the two listViews used in the HUD
	 * 
	 * @return Region - HBox holding listView of Variable Names and Variable
	 *         Values
	 */
	public Region getBody() {
		return container;
	}

	/**
	 * Handles swapping variables' indices in HUD
	 */
	public void handleChange(ValueChange change) {
		int rownum = valueToRowMap.get(change.getFieldName());
		values.set(rownum, change.getNewValue().toString());
	}
}
