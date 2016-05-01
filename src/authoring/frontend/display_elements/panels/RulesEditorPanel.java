package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.configuration.EntityComponents;
import authoring.frontend.configuration.LabelCell;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableLevelAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import authoring.frontend.editor_features.ObjectChooser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The RulesEditorPanel contains two scrollable views of "if" and "then"
 * conditions, which together create a rule.
 * 
 * @author Frank
 *
 */

public class RulesEditorPanel extends Panel {

	public static final int COLUMN_1_WIDTH_PERCENTAGE = Constants.getInt("RULES_COLUMN_1");
	public static final int COLUMN_2_WIDTH_PERCENTAGE = Constants.getInt("RULES_COLUMN_2");
	public static final int COLUMN_3_WIDTH_PERCENTAGE = Constants.getInt("RULES_COLUMN_3");
	public static final int COLUMN_4_WIDTH_PERCENTAGE = Constants.getInt("RULES_COLUMN_4");
	public static final int COLUMN_5_WIDTH_PERCENTAGE = Constants.getInt("RULES_COLUMN_5");
	public static final List<String> MODIFIABLE_LEVEL_ATTRIBUTES = (List<String>) Arrays.asList("NumLives",
			"CurrentResources");
	public static final List<String> POSSIBLE_EVENTS = (List<String>) Arrays.asList("CollisionEvent",
			"CriticalHealthEvent", "DeathEvent", "EntityClickedEvent", "KeyPressedEvent", "EndOfPathEvent");
	public static final List<String> UNMODIFIABLE_ENTITY_ATTRIBUTES = (List<String>) Arrays.asList("Type", "Name",
			"Genre");

	private EditorButtonDashboard mySimpleButtonDashboard;
	private Button myAddNewIfButton;
	private Button myAddNewThenButton;
	private ListView<String> myIfStatements;
	private ListView<String> myThenStatements;
	private Stage myRulesStage;
	private IAuthoringView myController;
	private ModifiableAttributesPanel myAttributes;
	private ObservableList<String> myRules;

	public RulesEditorPanel(double height, double width, IAuthoringView controller,
			ModifiableAttributesPanel attributes) {
		super(height, width);
		myController = controller;
		myAttributes = attributes;
	}

	@Override
	protected void initializeComponents() {
		mySimpleButtonDashboard = new EditorButtonDashboard(MAX_SIZE, MAX_SIZE);
		mySimpleButtonDashboard.initialize();
		myRules = FXCollections.observableArrayList();
		myRulesStage = new Stage();
		((ModifiableLevelAttributesPanel) myAttributes).updateRulesView(myRules);
		myAddNewIfButton = new Button(Constants.getString("NEW_IF_BUTTON"));
		myAddNewIfButton.setOnAction(e -> openStatementCreator(openIfScene()));
		myAddNewThenButton = new Button(Constants.getString("NEW_THEN_BUTTON"));
		myAddNewThenButton.setOnAction(e -> openStatementCreator(openThenScene()));
		myIfStatements = new ListView<String>();
		myThenStatements = new ListView<String>();

	}

	@Override
	protected void assembleComponents() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_2_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_3_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_4_WIDTH_PERCENTAGE);
		columnConstraints.add(COLUMN_5_WIDTH_PERCENTAGE);

		GridPane grid = createGridWrapper(rowConstraints, columnConstraints);

		VBox ifbox = createVBoxWrapper(Constants.getString("IF_LABEL"), myAddNewIfButton);
		VBox thenbox = createVBoxWrapper(Constants.getString("THEN_LABEL"), myAddNewThenButton);
		mySimpleButtonDashboard.getSaveButton().setOnAction(e -> {
			StringBuilder sb = new StringBuilder();
			myIfStatements.getItems().forEach(event -> sb.append(event + "+"));
			sb.replace(sb.length() - 1, sb.length(), ":");
			myThenStatements.getItems().forEach(action -> sb.append(action + "+"));
			sb.deleteCharAt(sb.length() - 1);
			myRules.add(sb.toString());

			myIfStatements.getItems().clear();
			myThenStatements.getItems().clear();
		});

		mySimpleButtonDashboard.getResetButton().setOnAction(e -> {
			myIfStatements.getItems().clear();
			myThenStatements.getItems().clear();
		});

		grid.add(mySimpleButtonDashboard.getNode(), 0, 0);
		grid.add(ifbox, 1, 0);
		grid.add(myIfStatements, 2, 0);
		grid.add(thenbox, 3, 0);
		grid.add(myThenStatements, 4, 0);
		myNode = grid;

	}

	/**
	 * Creates a VBox wrapper containing a Label and Button.
	 * 
	 * @param labelText
	 * @param button
	 * @return
	 */
	private VBox createVBoxWrapper(String labelText, Button button) {
		VBox vb = new VBox();
		Label label = new Label(labelText);
		label.setPrefSize(MAX_SIZE, MAX_SIZE);
		button.setPrefSize(MAX_SIZE, MAX_SIZE);
		vb.getChildren().addAll(label, button);
		return vb;

	}

	private String openStatementCreator(Scene s) {
		myRulesStage.setScene(s);
		myRulesStage.showAndWait();
		return null;
	}

	private Scene openIfScene() {
		VBox ifStatementBuilder = new VBox();
		Scene ifScene = new Scene(ifStatementBuilder, Constants.getInt("IF_SCENE_HEIGHT"), Constants.getInt("IF_SCENE_WIDTH"), Color.WHITE);

		HBox selectEventBox = new HBox();
		Text eventText = new Text("Choose the type of event:");
		HBox enterKeyBox = new HBox();
		Text keyText = new Text("Enter key:");
		HBox selectEntitiesBox = new HBox();
		Text entityText = new Text();

		ComboBox<String> eventChooser = new ComboBox<String>();

		ComboBox<Label> entityChooser = new ComboBox<Label>();
		entityChooser.setCellFactory(listview -> new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
		entityChooser.setPrefWidth(Constants.getInt("ENTITY_CHOOSER_WIDTH"));
		entityChooser.setButtonCell(new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));

		ComboBox<Label> entityChooser2 = new ComboBox<Label>();
		entityChooser2.setCellFactory(listview -> new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
		entityChooser2.setButtonCell(new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
		entityChooser2.setPrefWidth(Constants.getInt("ENTITY_CHOOSER_WIDTH"));
				


		Button keyField = new Button("Click and press desired key");

		keyField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				keyField.setText(event.getCode().getName());
				event.consume();

			}
		});

		Button saveButton = new Button("Create Event");

		saveButton.setOnAction(e -> {
			StringBuilder sb = new StringBuilder();
			sb.append(entityChooser.getSelectionModel().getSelectedItem().getText() + "~");
			if (entityChooser2.isVisible()) {
				sb.append(entityChooser2.getSelectionModel().getSelectedItem().getText() + "~");
			}
			sb.append(eventChooser.getSelectionModel().getSelectedItem());
			if (ifStatementBuilder.getChildren().contains(enterKeyBox)) {
				sb.append("~" + keyField.getText());
			}
			myIfStatements.getItems().add(sb.toString());
			myRulesStage.close();
		});

		eventChooser.getItems().addAll(POSSIBLE_EVENTS);
		eventChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ifStatementBuilder.getChildren().clear();
				ifStatementBuilder.getChildren().add(selectEventBox);

				if (newValue == null)
					return;

				entityChooser2.setVisible(false);
				if (newValue.equals("CollisionEvent")) {
					entityText.setText("Choose the entities for this event:");
					entityChooser2.setVisible(true);
				} else {
					entityText.setText("Choose the entity for this event:");
				}

				if (newValue.equals("KeyPressedEvent")) {
					ifStatementBuilder.getChildren().add(enterKeyBox);
				}
				ifStatementBuilder.getChildren().addAll(selectEntitiesBox, saveButton);
			}

		});

		// link the list of level entities to these combo boxes
		entityChooser.getItems()
				.addAll(createLabelList(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities()));
		entityChooser2.getItems()
				.addAll(createLabelList(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities()));

		selectEventBox.getChildren().addAll(eventText, eventChooser);
		enterKeyBox.getChildren().addAll(keyText, keyField);
		selectEntitiesBox.getChildren().addAll(entityText, entityChooser, entityChooser2);
		ifStatementBuilder.getChildren().add(selectEventBox);
		return ifScene;
	}

	private Scene openThenScene() {
		Map<String, String> myImageMap = myController.getAuthoringViewManager().getObjectChooser().getMap();
		VBox thenStatementBuilder = new VBox();
		Scene thenScene = new Scene(thenStatementBuilder, Constants.getInt("THEN_SCENE_WIDTH"), Constants.getInt("THEN_SCENE_HEIGHT"), Color.WHITE);
		HBox selectTypeBox = new HBox();
		Text typeText = new Text("Select the type of the affected object:");
		HBox selectEntityBox = new HBox();
		Text entityText = new Text("Select the affected entity:");
		HBox selectAttributeBox = new HBox();
		Text attributeText = new Text("Select the attribute to update:");
		HBox selectNewValueBox = new HBox();
		Text newValueText = new Text("Enter the new value:");
		HBox selectLevelValueToModifyBox = new HBox();
		Text levelValueText = new Text("Select the level value to change:");
		HBox enterDeltaValueBox = new HBox();
		Text deltaValueText = new Text("Enter the delta value:");

		ComboBox<String> typeChooser = new ComboBox<String>();
		typeChooser.getItems().addAll("Level", "Entity");
		ComboBox<Label> entityChooser = new ComboBox<Label>();
		entityChooser.getItems()
				.addAll(createLabelList(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities()));
		entityChooser.setCellFactory(listview -> new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
		entityChooser.setButtonCell(new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
		entityChooser.setPrefWidth(Constants.getInt("ENTITY_CHOOSER_WIDTH"));

		ComboBox<String> attributeChooser = new ComboBox<String>();
		ComboBox<Label> newValueChooser = new ComboBox<Label>();
		ComboBox<String> levelValueChooser = new ComboBox<String>();
		levelValueChooser.getItems().addAll(MODIFIABLE_LEVEL_ATTRIBUTES);
		TextField deltaValueField = new TextField();

		Button saveButton = new Button(Constants.getString("CREATE_ACTION_BUTTON"));
		saveButton.setOnAction(e -> {
			StringBuilder sb = new StringBuilder();
			sb.append(typeChooser.getSelectionModel().getSelectedItem() + "~");
			if (typeChooser.getSelectionModel().getSelectedItem().equals("Entity")) {
				sb.append(entityChooser.getSelectionModel().getSelectedItem().getText() + "~");
				List<String> attribute = Arrays.asList(attributeChooser.getSelectionModel().getSelectedItem().split("Component_"));
				attribute.forEach(a -> sb.append(a + "~"));

			} else {
				sb.append(levelValueChooser.getSelectionModel().getSelectedItem() + "~");
			}
			if (thenStatementBuilder.getChildren().contains(selectNewValueBox)) {
				sb.append(newValueChooser.getSelectionModel().getSelectedItem().getText());

			} else {
				sb.append(deltaValueField.getText());
			}
			myThenStatements.getItems().add(sb.toString());
			myRulesStage.close();
		});

		typeChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				thenStatementBuilder.getChildren().clear();
				thenStatementBuilder.getChildren().add(selectTypeBox);
				if (newValue.equals("Level")) {
					levelValueChooser.getSelectionModel().clearSelection();
					thenStatementBuilder.getChildren().add(selectLevelValueToModifyBox);
				} else {
					entityChooser.getSelectionModel().clearSelection();
					thenStatementBuilder.getChildren().add(selectEntityBox);
				}
			}

		});

		entityChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {

			@Override
			public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
				thenStatementBuilder.getChildren().clear();
				thenStatementBuilder.getChildren().addAll(selectTypeBox, selectEntityBox);

				if (newValue == null)
					return;

				attributeChooser.getSelectionModel().clearSelection();
				attributeChooser.getItems().clear();
				attributeChooser.getItems().addAll(myController.getEntities().get(newValue.getText()).keySet());
				attributeChooser.getItems().removeAll(UNMODIFIABLE_ENTITY_ATTRIBUTES);
				thenStatementBuilder.getChildren().add(selectAttributeBox);
			}

		});

		attributeChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				thenStatementBuilder.getChildren().clear();
				thenStatementBuilder.getChildren().addAll(selectTypeBox, selectEntityBox, selectAttributeBox);

				if (newValue == null)
					return;

				newValueChooser.getSelectionModel().clearSelection();
				newValueChooser.getItems().clear();
				deltaValueField.clear();

				if (EntityComponents.getVariableType(newValue).equals("Numerical")) {
					thenStatementBuilder.getChildren().add(enterDeltaValueBox);
				} else {
					thenStatementBuilder.getChildren().add(selectNewValueBox);
				}

				if (EntityComponents.getVariableType(newValue).equals("Boolean")) {
					newValueChooser.setCellFactory(new Callback<ListView<Label>, ListCell<Label>>() {
						public ListCell<Label> call(ListView<Label> p) {
							return new ListCell<Label>() {

								@Override
								protected void updateItem(Label item, boolean empty) {
									super.updateItem(item, empty);

									if (item == null || empty) {
										setGraphic(null);
									} else {
										setText(item.getText());
										setStyle("-fx-text-fill: black;");
									}
								}
							};
						}
					});

					newValueChooser.getItems().addAll(new Label("true"), new Label("false"));
				} else if (EntityComponents.getVariableType(newValue).equals("Image")) {
					newValueChooser.getItems().addAll(
							createLabelList(myController.getAuthoringViewManager().getObjectChooser().getMap()));
					newValueChooser.setCellFactory(listview -> new LabelCell(myImageMap, myController));
					newValueChooser.setButtonCell(new LabelCell(myImageMap, myController));
					newValueChooser.setPrefWidth(Constants.getInt("NEW_VALUE_WIDTH"));

				} else if (EntityComponents.getVariableType(newValue).equals("Entity")) {
					// add all of the level entities
					newValueChooser.getItems().addAll(
							createLabelList(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities()));
					newValueChooser.setCellFactory(listview -> new LabelCell(
							((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));
					newValueChooser.setButtonCell(new LabelCell(((ModifiableLevelAttributesPanel) myAttributes).getLevelEntities(), myController));

					newValueChooser.setPrefWidth(Constants.getInt("NEW_VALUE_WIDTH"));

				}

				thenStatementBuilder.getChildren().add(saveButton);
			}

		});

		levelValueChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				thenStatementBuilder.getChildren().clear();
				thenStatementBuilder.getChildren().addAll(selectTypeBox, selectLevelValueToModifyBox);

				if (newValue == null)
					return;

				deltaValueField.clear();
				thenStatementBuilder.getChildren().addAll(enterDeltaValueBox, saveButton);
			}

		});

		selectTypeBox.getChildren().addAll(typeText, typeChooser);
		selectEntityBox.getChildren().addAll(entityText, entityChooser);
		selectAttributeBox.getChildren().addAll(attributeText, attributeChooser);
		selectLevelValueToModifyBox.getChildren().addAll(levelValueText, levelValueChooser);
		selectNewValueBox.getChildren().addAll(newValueText, newValueChooser);
		enterDeltaValueBox.getChildren().addAll(deltaValueText, deltaValueField);

		thenStatementBuilder.getChildren().add(selectTypeBox);
		return thenScene;
	}

	private List<Label> createLabelList(Map<String, String> map) {
		ObjectChooser entityChooser = new ObjectChooser(myController);
		entityChooser.initialize();
		entityChooser.addAll(map);
		return entityChooser.getList();
	}

	public String getRules() {
		StringBuilder sb = new StringBuilder();
		myRules.forEach(rule -> sb.append(rule + " "));
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public void setRules(String rulesString) {
		myRules.clear();
		if (rulesString != null) {
			List<String> rules = Arrays.asList(rulesString.split(" "));
			myRules.addAll(rules);
		}
	}

	public void reset() {
		myIfStatements.getItems().clear();
		myThenStatements.getItems().clear();
	}

}
