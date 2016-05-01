package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.ObjectChooser;
import authoring.frontend.editor_features.SpawnEntityRow;
import authoring.parser.GlobalParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableLevelAttributesPanel extends ModifiableAttributesPanel {

	private ObjectChooser myEntitySelector;
	private ComboBox<String> myWaveSelector;
	private String mySpawnEntitiesCompressed;
	private TreeMap<String, String> myPossibleEntities;
	private TreeMap<String, SpawnEntityRow> mySpawnEntitiesInputMap;
	private GridPane mySpawnEntitiesGridPane;
	private List<String> myWaves;
	private int myMaxWave;
	private Button myAddSpawnButton;
	private Button myAddTowerButton;
	private TitledPane mySpawnPane;
	private TitledPane myTowersPane;
	private TitledPane myRulesPane;
	private ListView<Label> myTowersListView;
	private ListView<String> myRulesListView;
	private Accordion myAccordion;
	private Map<String, String> myTowers;
	private ScrollPane myScrollPane;
	private Map<String, String> myLevelEntities;

	private static final List<String> COLUMN_NAMES = (List<String>) Arrays.asList("Path #", "Name", "#", "Wave",
			"Rate");
	private static final List<String> DEFAULT_LEVEL_ATTRIBUTES = (List<String>) Arrays.asList("Name",
			"MapBackgroundImage", "WaveDelayTimer", "MapWidth", "MapHeight");
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
		myPossibleEntities = new TreeMap<String, String>();
		myTowers = new TreeMap<String, String>();
		myLevelEntities = new TreeMap<String, String>();
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
		myGridPane.setMaxWidth(MAX_SIZE);

		myAttributes = new ArrayList<String>();
		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();
		myAttributesGridPane = createAttributesGridPane();
		mySpawnEntitiesGridPane = assembleEmptySpawnEntitiesGridPane();

		myTowersListView = new ListView<Label>();
		myTowersListView.setMaxWidth(MAX_SIZE);
		myRulesListView = new ListView<String>();
		myRulesListView.setMaxWidth(MAX_SIZE);
		myScrollPane = new ScrollPane();
		myScrollPane.setContent(mySpawnEntitiesGridPane);

		mySpawnPane = new TitledPane("Waves", myScrollPane);
		myTowersPane = new TitledPane("Towers", myTowersListView);
		myRulesPane = new TitledPane("Rules", myRulesListView);
		mySpawnPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		myTowersPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		myRulesPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myAccordion = new Accordion();
		myAccordion.getPanes().addAll(mySpawnPane, myTowersPane, myRulesPane);

		assembleEmptyInputRows();
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myGridPane.add(myAccordion, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}

	@Override
	protected void assembleEmptyInputRows() {
		super.assembleEmptyInputRows();
		assembleAddSpawnOptions();

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
		mySpawnEntitiesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		addColumnNames(COLUMN_NAMES, mySpawnEntitiesGridPane);

		return mySpawnEntitiesGridPane;
	}

	private void assembleAddSpawnOptions() {
		myAddSpawnButton = new Button("Add Spawn");
		myAddTowerButton = new Button("Add Tower");

		myEntitySelector = new ObjectChooser(myController);
		myEntitySelector.initialize();
		myEntitySelector.addAll(myPossibleEntities);
		TextField entityTextField = new TextField("Select Entity");
		entityTextField.setOnMouseClicked(e -> entityTextField.setText(myEntitySelector.openChooser()));
		entityTextField.setEditable(false);

		myWaveSelector = new ComboBox<String>();

		myAddSpawnButton.setPrefSize(MAX_SIZE, MAX_SIZE);
		myAddSpawnButton.setFont(new Font(20));
		myAddSpawnButton.setAlignment(Pos.BASELINE_LEFT);

		myAddTowerButton.setPrefSize(MAX_SIZE, MAX_SIZE);
		myAddTowerButton.setFont(new Font(20));
		myAddTowerButton.setAlignment(Pos.BASELINE_LEFT);

		myWaveSelector.setPrefSize(100, 50);
		myWaveSelector.getItems().addAll(myWaves);
		myWaveSelector.setPromptText("Select Wave");

		myAddSpawnButton.setOnAction(e -> {

			String selected = promptUserInput("Entity", entityTextField);
			String selectedImagePath = myPossibleEntities.get(selected);
			String wave = promptUserInput("Wave", myWaveSelector);

			if (wave.equals("New")) {
				myMaxWave++;
				myWaves.add(Integer.toString(myMaxWave));
				wave = Integer.toString(myMaxWave);
				myWaveSelector.getItems().clear();
				myWaveSelector.getItems().addAll(myWaves);
			}

			String pathID = promptUserInput("Path Number");
			if (!pathID.equals("")) {
				String tag = pathID + ":" + selected + ":" + wave;
				ImageView newImageView = new ImageView(new Image(myController.getImageMap().get(selectedImagePath)));
				SpawnEntityRow row = new SpawnEntityRow(tag, selected, newImageView, wave, pathID);
				linkRow(row);

				if (!myLevelEntities.keySet().contains(selected)) {
					myLevelEntities.put(selected, selectedImagePath);
				}
			}
		});

		myAddTowerButton.setOnAction(e -> {
			String selected = promptUserInput("Entity", entityTextField);
			String selectedImagePath = myPossibleEntities.get(selected);
			if (!myTowers.containsKey(selected) && myController.getEntities().get(selected).keySet().contains("FiringComponent_Ammunition")) {
				myTowers.put(selected, selectedImagePath);
				ImageView towerView = new ImageView(new Image(myController.getImageMap().get(selectedImagePath)));
				towerView.setPreserveRatio(true);
				towerView.setFitHeight(100);
				towerView.setFitWidth(100);
				Label tower = new Label(selected);
				tower.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);
				tower.setGraphic(towerView);
				myTowersListView.getItems().add(tower);
				if (!myLevelEntities.keySet().contains(selected)) {
					myLevelEntities.put(selected, selectedImagePath);
				}
				
				String ammo = myController.getEntities().get(selected).get("FiringComponent_Ammunition");
				if (!myLevelEntities.keySet().contains(ammo)) {
					myLevelEntities.put(ammo, myController.getEntities().get(ammo).get("DisplayComponent_Image"));
				}
			}
		});

		myAttributesGridPane.add(myAddSpawnButton, 0, myAttributes.size() + 1);
		myAttributesGridPane.add(myAddTowerButton, 1, myAttributes.size() + 1);
	}

	private void linkRow(SpawnEntityRow row) {
		mySpawnEntitiesInputMap.put(row.getMyTag(), row);

		row.getMyDeleteButton().setOnAction(f -> {
			if (row.getMyWaveOrder().getText().equals(Integer.toString(myMaxWave))) {
				myWaves.remove(myMaxWave);
				myMaxWave--;
			}
			;

			mySpawnEntitiesInputMap.remove(row.getMyTag());
			refreshAttributeInputRows();
		});

		refreshAttributeInputRows();

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

	/**
	 * Update image display based on attribute image name.
	 * 
	 * @param imageView
	 */
	public void updateImageComponent(String imageName) {
		myAttributesMap.replace("MapBackgroundImage", imageName);
		TextField tf = (TextField) myInputMap.get("MapBackgroundImage");
		tf.setText(imageName);
		tf.setEditable(false);
		myInputMap.replace("MapBackgroundImage", tf);
		preserveMapRatio();
		refreshAttributeInputRows();
	}

	private void preserveMapRatio() {
		ImageView iv = ((EditorViewPanel) myController.getAuthoringViewManager().getTabBarElement()
				.getLevelsTabDisplay().getEditor().getEditorGrid().getPrimaryDisplay()).getImage();
		iv.setPreserveRatio(true);
		double mapRatio = (iv.getImage().getHeight() / iv.getImage().getWidth());
		myInputMap.replace("MapHeight", new TextField(Double.toString(iv.getImage().getHeight())));
		myInputMap.replace("MapWidth", new TextField(Double.toString(iv.getImage().getWidth())));
		iv.setFitHeight(iv.getImage().getHeight());
		iv.setFitWidth(iv.getImage().getWidth());

		((TextField) myInputMap.get("MapWidth")).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					if (myInputMap.get("MapWidth").isFocused()) {
						((TextField) myInputMap.get("MapHeight"))
								.setText(Double.toString(Double.parseDouble(newValue) * mapRatio));
					}
					iv.setFitWidth(Double.parseDouble(newValue));
				}
			}
		});

		((TextField) myInputMap.get("MapHeight")).textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					if (myInputMap.get("MapHeight").isFocused()) {
						((TextField) myInputMap.get("MapWidth"))
								.setText(Double.toString(Double.parseDouble(newValue) / mapRatio));
					}
					iv.setFitHeight(Double.parseDouble(newValue));
				}
			}
		});
	}

	@Override
	public void updateAttributes(Map<String, String> info) {
		super.updateAttributes(info);
		myAttributes.remove("SpawnEntities");
		parseLevelEntities(myAttributesMap.get("Entities"));
		myAttributesMap.remove("Entities");
		
		myInputMap = new TreeMap<String, Control>();

		for (String attribute : DEFAULT_LEVEL_ATTRIBUTES) {
			TextField tf = new TextField();
			myInputMap.put(attribute, tf);

		}

		if (myAttributesMap.get("SpawnEntities") != null) {
			updateSpawnEntitiesData(myAttributesMap.get("SpawnEntities"));
			myAttributesMap.remove("SpawnEntities");
		}

		setMyPossibleEntities(myController.getEntityImages());
		refreshAttributes();
	}

	private void parseLevelEntities(String entities) {
		myLevelEntities.clear();
		List<String> ents = Arrays.asList(entities.split(" "));
		ents.forEach(e -> myLevelEntities.put(e, myController.getImageMap().get(myPossibleEntities.get(e))));
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
			String path = pathID.split(":")[0];
			String[] spawnObjects = pathID.split(":")[1].split(("_"));
			for (String spawn : spawnObjects) {
				String[] components = spawn.split("\\.");
				String name = components[0];
				String imagePath = myPossibleEntities.get(name);
				ImageView newImage = new ImageView(new Image(myController.getImageMap().get(imagePath)));
				String wave = components[1];
				String number = components[2];
				String rate = components[3];
				String tag = pathID + ":" + name + ":" + wave;
				SpawnEntityRow row = new SpawnEntityRow(tag, path, name, newImage, number, wave, rate);
				linkRow(row);
				mySpawnEntitiesInputMap.put(tag, row);

			}
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.clear();
		myAttributesMap.put("Type", "Level");
		myAttributesMap.put("Entities", compressLevelEntities());

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.put(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.put(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		mySpawnEntitiesCompressed = GlobalParser.compressSpawnsInputs(mySpawnEntitiesInputMap);
		myAttributesMap.put("SpawnEntities", mySpawnEntitiesCompressed);

		System.out.println("*****4. ModifiableLevelAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}
		
	private String compressLevelEntities() {
		StringBuilder sb = new StringBuilder();
		myLevelEntities.keySet().forEach(e -> sb.append(e + " "));
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	protected void refreshAttributes() {
		preserveMapRatio();

		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				if (myInputMap.get(myAttributes.get(i)) instanceof TextField) {
					if (myAttributes.get(i).equals("MapBackgroundImage")) {
						if (myAttributesMap.get(myAttributes.get(i)) == null) {
							myInputMap.replace(myAttributes.get(i), new TextField("images/question_mark.png"));
							continue;
						}
					}
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
		assembleAddSpawnOptions();
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

	public void setMyPossibleEntities(Map<String, String> entities) {
		myPossibleEntities = (TreeMap<String, String>) entities;
		myEntitySelector.clear();
		myEntitySelector.addAll(myPossibleEntities);
	}

	public Map<String, String> getLevelEntities() {
		return myLevelEntities;
	}

	public void updateRulesView(ObservableList<String> rulesList) {
		myRulesListView.setItems(rulesList);
	}

}
