package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The RulesEditorPanel contains two scrollable views of "if" and "then"
 * conditions, which together create a rule.
 * 
 * @author Frank
 *
 */

public class RulesEditorPanel extends Panel {

	private final int COLUMN_1_WIDTH_PERCENTAGE = 10;
	private final int COLUMN_2_WIDTH_PERCENTAGE = 15;
	private final int COLUMN_3_WIDTH_PERCENTAGE = 30;
	private final int COLUMN_4_WIDTH_PERCENTAGE = 15;
	private final int COLUMN_5_WIDTH_PERCENTAGE = 30;
	private EditorButtonDashboard mySimpleButtonDashboard;
	private Button myAddNewIfButton;
	private Button myAddNewThenButton;
	private ListView<String> myIfStatements;
	private ListView<String> myThenStatements;
	private Stage myRulesStage;

	public RulesEditorPanel(double height, double width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		mySimpleButtonDashboard = new EditorButtonDashboard(MAX_SIZE, MAX_SIZE);
		mySimpleButtonDashboard.initialize();
		myRulesStage = new Stage();
		myAddNewIfButton = new Button("Add New If");
		myAddNewIfButton.setOnAction(e -> openStatementCreator(openIfScene()));
		myAddNewThenButton = new Button("Add New Then");
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

		VBox ifbox = createVBoxWrapper("If Statements", myAddNewIfButton);
		VBox thenbox = createVBoxWrapper("Then Statements", myAddNewThenButton);

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
		Scene ifScene = new Scene(ifStatementBuilder, 400, 400, Color.WHITE);
		HBox selectEventBox = new HBox();
		HBox selectEntitiesBox = new HBox();
		Button saveButton = new Button("Create Event");
		saveButton.setOnAction(e -> {
			//do something
			myRulesStage.close();
		});
		
		Text eventText = new Text("Choose the type of event:");
		Text entityText = new Text();
		
		ComboBox<String> eventChooser = new ComboBox<String>();
		ComboBox<String> entityChooser = new ComboBox<String>();
		ComboBox<String> entityChooser2 = new ComboBox<String>();
		
		eventChooser.getItems().addAll("CollisionEvent", "CriticalHealthEvent", "CriticalPositionEvent", "EntityClickedEvent");
		eventChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (selectEntitiesBox.getChildren().contains(entityChooser2)) {
					selectEntitiesBox.getChildren().remove(entityChooser2);
				}
				if (newValue.equals("CollisionEvent")) {
					entityText.setText("Choose the entities for this event:");
					selectEntitiesBox.getChildren().add(entityChooser2);
				}
				else {
					entityText.setText("Choose the entity for this event:");
				}
				ifStatementBuilder.getChildren().addAll(selectEntitiesBox, saveButton);
			}
			
		});
		
		entityChooser.getItems().addAll("bill", "bob", "joe");
		entityChooser2.getItems().addAll("bill", "bob", "joe");
		
		selectEventBox.getChildren().addAll(eventText, eventChooser);
		selectEntitiesBox.getChildren().addAll(entityText, entityChooser);
		ifStatementBuilder.getChildren().add(selectEventBox);
		return ifScene;
	}

	private Scene openThenScene() {
		VBox thenStatementBuilder = new VBox();
		Scene thenScene = new Scene(thenStatementBuilder, 400, 400, Color.WHITE);
		HBox selectEventBox = new HBox();
		HBox selectEntitiesBox = new HBox();
		Button saveButton = new Button("Create Event");
		saveButton.setOnAction(e -> {
			//do something
			myRulesStage.close();
		});
		
		Text eventText = new Text("Choose the type of event:");
		Text entityText = new Text();
		
		ComboBox<String> eventChooser = new ComboBox<String>();
		ComboBox<String> entityChooser = new ComboBox<String>();
		ComboBox<String> entityChooser2 = new ComboBox<String>();
		
		eventChooser.getItems().addAll("CollisionEvent", "CriticalHealthEvent", "CriticalPositionEvent", "EntityClickedEvent");
		eventChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (selectEntitiesBox.getChildren().contains(entityChooser2)) {
					selectEntitiesBox.getChildren().remove(entityChooser2);
				}
				if (newValue.equals("CollisionEvent")) {
					entityText.setText("Choose the entities for this event:");
					selectEntitiesBox.getChildren().add(entityChooser2);
				}
				else {
					entityText.setText("Choose the entity for this event:");
				}
				thenStatementBuilder.getChildren().addAll(selectEntitiesBox, saveButton);
			}
			
		});
		
		entityChooser.getItems().addAll("bill", "bob", "joe");
		entityChooser2.getItems().addAll("bill", "bob", "joe");
		
		selectEventBox.getChildren().addAll(eventText, eventChooser);
		selectEntitiesBox.getChildren().addAll(entityText, entityChooser);
		thenStatementBuilder.getChildren().add(selectEventBox);
		return thenScene;
	}

}
