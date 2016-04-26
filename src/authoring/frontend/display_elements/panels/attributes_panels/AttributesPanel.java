package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.*;

import authoring.frontend.display_elements.panels.Panel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Stage promptStage = new Stage();
		promptedString = "";
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter " + promptValue + ":");
		TextField textBox = new TextField();
		textBox.setMaxWidth(200);
		textBox.setPromptText("promptValue");
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(textBox);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> {
			promptStage.close();
			return;
		});
		
		textBox.setOnAction(e -> {
			promptedString = textBox.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			promptedString = textBox.getText();
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
