package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.Panel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * All attribute panels have a map of attribute names to values, as well as a
 * list of attributes names.
 * 
 * @author Frank
 *
 */

public abstract class AttributesPanel extends Panel {

	protected String promptedString;
	protected static final int FONT_SIZE = 13;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4;

	protected List<String> myAttributes;
	protected Map<String, String> myAttributesMap;

	public AttributesPanel(int height, int width) {
		super(height, width);
	}

	/**
	 * Helper method for creating and repopulating Grids
	 * 
	 * @param names
	 * @param gridPane
	 */
	protected void addColumnNames(List<String> columnNames, GridPane gridPane) {
		for (int i = 0; i < columnNames.size(); i++) {
			Label text = new Label(columnNames.get(i));
			text.setFont(new Font(FONT_SIZE));
			gridPane.add(text, i, 0);
		}
	}

	protected String promptUserInput(String promptValue) {
		TextField textBox = new TextField();
		return promptUserInput(promptValue, textBox);
	}

	protected String promptUserInput(String promptValue, TextField tf) {
		Stage promptStage = new Stage();
		promptedString = "";
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter " + promptValue + ":");
		tf.setMaxWidth(200);
		tf.setPromptText(promptValue);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(tf);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> {
			promptStage.close();
			return;
		});

		tf.setOnAction(e -> {
			promptedString = tf.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			promptedString = tf.getText();
			promptStage.close();
		});
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, 300, 200);
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return promptedString;
	}

	public String promptUserInput(String promptValue, ComboBox<String> cb) {
		Stage promptStage = new Stage();
		promptedString = "";
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter " + promptValue + ":");
		cb.setMaxWidth(200);
		cb.setPromptText(promptValue);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(cb);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> {
			promptStage.close();
			return;
		});

		saveButton.setOnAction(e -> {
			promptedString = cb.getSelectionModel().getSelectedItem();
			promptStage.close();
		});
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, 300, 200);
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return promptedString;
	}
}
