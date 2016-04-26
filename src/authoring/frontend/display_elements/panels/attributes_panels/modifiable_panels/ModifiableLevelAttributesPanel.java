package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.SpawnEntityRow;
import authoring.frontend.editor_features.LocalImage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableLevelAttributesPanel extends ModifiableAttributesPanel {

	private Button myAddSpawnButton;
	private ComboBox<String> myEntitySelector;
	private ComboBox<String> myWaveSelector;
	private String mySpawnEntitiesCompressed;
	private TreeMap<String, ImageView> myEntities;
	private TreeMap<String, SpawnEntityRow> mySpawnEntitiesInputMap;
	private GridPane mySpawnEntitiesGridPane;
	private static final List<String> COLUMN_NAMES = (List<String>) Arrays.asList("Path #", "Name", "#", "Wave",
			"Rate");
	private static final List<String> LEVEL_ATTRIBUTES = (List<String>) Arrays.asList("Name", "MapBackgroundImage", "LevelTimer",
			"WaveDelayTimer", "MapWidth", "MapHeight");

	private List<String> myWaves;
	private int myMaxWave;
	private String promptedPath;

	private static final int SPAWN_ENTITIES_COLUMN_1 = 5;
	private static final int SPAWN_ENTITIES_COLUMN_2 = 13;
	private static final int SPAWN_ENTITIES_COLUMN_3 = 3;
	private static final int SPAWN_ENTITIES_COLUMN_4 = 4;
	private static final int SPAWN_ENTITIES_COLUMN_5 = 3;
	private static final int SPAWN_ENTITIES_COLUMN_6 = 5;
	private static final int SPAWN_ENTITIES_COLUMN_7 = 5;
	private static final int SPAWN_ENTITIES_COLUMN_8 = 4;

	public ModifiableLevelAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
		myEntities = new TreeMap<String, ImageView>();
		myWaves = new ArrayList<String>();
		myWaves.add("New");
		myWaves.add("1");
		myMaxWave = 1;
		mySpawnEntitiesInputMap = new TreeMap<String, SpawnEntityRow>();
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(ATTRIBUTES_HEIGHT - 5);
		rowConstraints.add(ATTRIBUTES_HEIGHT + 15);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH + 15);

		myAttributes = new ArrayList<String>();
		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();
		myAttributesGridPane = createAttributesGridPane();
		mySpawnEntitiesGridPane = assembleEmptySpawnEntitiesGridPane();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(mySpawnEntitiesGridPane);
		myScrollPane.setFitToWidth(false);

		myAddSpawnButton = new Button("Add Spawn");
		myEntitySelector = new ComboBox<String>();
		myWaveSelector = new ComboBox<String>();

		assembleEmptyInputRows();
	}

	@Override
	protected void assembleComponents() {
		myAttributesGridPane.add(myEntitySelector, 0, myAttributes.size());
		myAttributesGridPane.add(myWaveSelector, 1, myAttributes.size());
		myAttributesGridPane.add(myAddSpawnButton, 0, myAttributes.size() + 1);
		myGridPane.add(myAttributesGridPane, 0, 0);
		myGridPane.add(myScrollPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	@Override
	protected void assembleEmptyInputRows() {
		super.assembleEmptyInputRows();
		createAddEntityOptions();
	}

	private GridPane assembleEmptySpawnEntitiesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_1);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_2);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_3);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_4);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_5);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_6);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_7);
		columnConstraints.add(SPAWN_ENTITIES_COLUMN_8);

		mySpawnEntitiesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		mySpawnEntitiesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);

		addColumnNames(COLUMN_NAMES, mySpawnEntitiesGridPane);

		return mySpawnEntitiesGridPane;
	}

	private void createAddEntityOptions() {
		myAddSpawnButton.setPrefSize(MAX_SIZE, MAX_SIZE);
		myAddSpawnButton.setFont(new Font(20));
		myAddSpawnButton.setAlignment(Pos.BASELINE_LEFT);
		GridPane.setColumnSpan(myAddSpawnButton, 2);

		myEntitySelector.setPrefSize(MAX_SIZE, MAX_SIZE);
		myEntitySelector.getItems().addAll(myEntities.keySet());
		myEntitySelector.setPromptText("Select Entity");

		myWaveSelector.setPrefSize(MAX_SIZE, MAX_SIZE);
		myWaveSelector.getItems().addAll(myWaves);
		myWaveSelector.setPromptText("Select Wave");

		myAddSpawnButton.setOnAction(e -> {
			String selected = myEntitySelector.getSelectionModel().getSelectedItem();
			ImageView selectedImage = myEntities.get(selected);
			String wave = myWaveSelector.getSelectionModel().getSelectedItem();
			if (wave.equals("New")) {
				myMaxWave++;
				myWaves.add(Integer.toString(myMaxWave));
				wave = Integer.toString(myMaxWave);
				myWaveSelector.getItems().clear();
				myWaveSelector.getItems().addAll(myWaves);
			}
			String pathID = promptPathID();
			if (!pathID.equals("")) {
				String tag = selected + ":" + wave + ":" + pathID;
				String URL = ((LocalImage) selectedImage.getImage()).getURL();
				ImageView newImageView = new ImageView(new LocalImage(URL));
				SpawnEntityRow row = new SpawnEntityRow(tag, selected, newImageView, wave, pathID);
				
				row.getMyDeleteButton().setOnAction(f -> {
					if (row.getMyWaveOrder().getText().equals(Integer.toString(myMaxWave))) {
						myWaves.remove(myMaxWave);
						myMaxWave--;
					};
					
					mySpawnEntitiesInputMap.remove(tag);
					refreshAttributeInputRows();
				});
				
				mySpawnEntitiesInputMap.put(tag, row);
				refreshAttributeInputRows();

			}
		});

	}

	@Override
	protected void addColumnNames(List<String> columnNames, GridPane gridPane) {
		for (int i = 0; i < columnNames.size(); i++) {
			Label text = new Label(columnNames.get(i));
			text.setFont(new Font(FONT_SIZE));

			if (i <= 1) {
				gridPane.add(text, i, 0);

			} else if (i > 2) {
				gridPane.add(text, i + 2, 0);
			}

			else if (i == 2) {
				gridPane.add(text, i + 1, 0);
				GridPane.setColumnSpan(text, 3);
			}
		}
	}

	private String promptPathID() {
		Stage promptStage = new Stage();
		promptedPath = "";
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter Path Number:");
		TextField textBox = new TextField();
		textBox.setMaxWidth(200);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(textBox);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> promptStage.close());
		textBox.setOnAction(e -> {
			promptedPath = textBox.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			promptedPath = textBox.getText();
			promptStage.close();
		});
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, 300, 200);
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return promptedPath;
	}

	@Override
	public void updateImageComponent(String image) {
		myAttributesMap.replace("MapBackgroundImage", image);
		TextField tf = (TextField) myInputMap.get("MapBackgroundImage");
		tf.setText(image);
		tf.setEditable(false);
		myInputMap.replace("MapBackgroundImage", tf);
		refreshAttributeInputRows();
	}

	@Override
	public void updateAttributes(Map<String, String> info) {
		super.updateAttributes(info);
		myAttributes.remove("SpawnEntities");
		myInputMap = new TreeMap<String, Control>();
	
		for (String attribute : LEVEL_ATTRIBUTES) {
			TextField tf = new TextField();
			myInputMap.put(attribute, tf);

		}

		if (myAttributesMap.get("SpawnEntities") != null) {
			updateSpawnEntitiesData(myAttributesMap.get("SpawnEntities"));
		}

		setMyEntities(myController.getEntities());
		refreshAttributes();
	}

	/**
	 * Updates SpawnEntityInputMap with updated values.
	 * 
	 * @param map
	 */
	private void updateSpawnEntitiesData(String compressed) {
		mySpawnEntitiesInputMap = new TreeMap<String, SpawnEntityRow>();

		String[] pathIDs = compressed.split(",");
		for (String pathID : pathIDs) {
			String[] spawnObjects = pathID.split(" ");
			for (String spawn : spawnObjects) {
				String[] components = spawn.split(".");
				String name = components[0];
				ImageView image = myEntities.get(name);
				String wave = components[1];
				String number = components[2];
				String rate = components[3];
				String tag = pathID + ":" + name + ":" + wave;
				mySpawnEntitiesInputMap.put(tag, new SpawnEntityRow(tag, pathID, name, image, number, wave, rate));

			}
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Level");

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		// mySpawnEntitiesCompressed = compressSpawnEntitie();
		// myAttributesMap.replace("SpawnEntities", mySpawnEntitiesCompressed);
		System.out.println("*****4. ModifiableLevelAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}

	// private void compressSpawnEntities() {
	// StringBuilder sb = new StringBuilder();
	//
	// for (Integer i : mySpawnEntitiesInputMap.keySet()) {
	// SpawnEntityRow info = mySpawnEntitiesInputMap.get(i);
	// sb.append(info.getMyPathID() + ":");
	// sb.append(info.getMyName().getText() + "." +
	// info.getMyWaveOrder().getText() + "." + info.getMyNumber() + "." +
	// info.getMyRate().getText());
	// sb.append(" ");
	// }
	// }

	protected void refreshAttributes() {

		myAttributes.remove("SpawnEntities");

		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				if (myInputMap.get(myAttributes.get(i)) instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
					tf.setText(myAttributesMap.get(myAttributes.get(i)));
					tf.setEditable(true);
					myInputMap.replace(myAttributes.get(i), tf);
				} else if (myInputMap.get(myAttributes.get(i)) instanceof ComboBox<?>) {
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

	@Override
	protected void refreshAttributeInputRows() {
		super.refreshAttributeInputRows();
		createAddEntityOptions();
		refreshSpawnEntityDisplay();
	}

	private void refreshSpawnEntityDisplay() {
		mySpawnEntitiesGridPane.getChildren().clear();
		addColumnNames(COLUMN_NAMES, mySpawnEntitiesGridPane);

		if (mySpawnEntitiesInputMap != null) {
			int i = 1;
			for (String tag : mySpawnEntitiesInputMap.keySet()) {
				SpawnEntityRow info = mySpawnEntitiesInputMap.get(tag);
				mySpawnEntitiesGridPane.add(info.getMyPathID(), 0, i);
				mySpawnEntitiesGridPane.add(info.getMyName(), 1, i);
				mySpawnEntitiesGridPane.add(info.getMyDecreaseButton(), 2, i);
				mySpawnEntitiesGridPane.add(info.getMyNumber(), 3, i);
				mySpawnEntitiesGridPane.add(info.getMyIncreaseButton(), 4, i);
				mySpawnEntitiesGridPane.add(info.getMyWaveOrder(), 5, i);
				mySpawnEntitiesGridPane.add(info.getMyRate(), 6, i);
				mySpawnEntitiesGridPane.add(info.getMyDeleteButton(), 7, i);
				i++;
			}

		}

	}

	public void setMyEntities(Map<String, ImageView> entities) {
		myEntities = (TreeMap<String, ImageView>) entities;
		myEntitySelector.getItems().clear();
		myEntitySelector.getItems().setAll(entities.keySet());
	}

}
