package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.EntitiesTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntitiesTabGrid extends TabGrid {

	private Map<String, String> currentInfo = new TreeMap<String, String>();
	private String newName;
	private Map<String, String> myEntities;

	public EntitiesTabGrid(IAuthoringView controller, TabDisplay tab) {
		super(controller, tab);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
		myEntities = new TreeMap<String, String>();
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntitiesTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		((MainButtonDashboard) myButtonDashboard).getDuplicateButton().setOnAction(e -> duplicate(currentInfo));
		((MainButtonDashboard) myButtonDashboard).getDeleteButton().setOnAction(e -> delete(currentInfo));

	}

	@Override
	public void setAttributesPanel(Map<String, String> info) {
		myUnmodifiableAttributesPanel.setAttributes(info);
	}

	public void update(List<Map<String, String>> data, String genre) {
		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();
		myEntities.clear();

		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {
			if (info.get("Genre").equals(genre)) {
				if (!myEntities.containsKey((info.get("Name")))) {
					Image image = new Image(info.get("DisplayComponent_Image"));
					ImageView iv = new ImageView(image);
					myEntities.put(info.get("Name"), info.get("DisplayComponent_Image"));
					iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
						public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
								Boolean newValue) {
							if (newValue) {
								info.remove("Type");
								setAttributesPanel(info);
								currentInfo = info;
							}
						}
					});
					gridView.addImage(iv);

				}
			}
		}
	}

	private void duplicate(Map<String, String> info) {
		Map<String, String> duplicateEntity = new TreeMap<String, String>();
		for (String s : info.keySet()) {
			duplicateEntity.put(s, info.get(s));
		}

		String newName = promptNewName();
		if (!newName.equals("")) {
			duplicateEntity.replace("Name", newName);
			myTabDisplay.openEditorDisplay(duplicateEntity);
		}
	}

	private void delete(Map<String, String> info) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Entity Warning");
		alert.setContentText("Are you sure you want to delete this Entity?");

		ButtonType buttonTypeOK = new ButtonType("OK");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypeOK) {
			myController.deleteData(info);
			currentInfo.clear();
			setAttributesPanel(currentInfo);
			alert.close();
		} else if (result.get() == buttonTypeCancel) {
			return;
		}

	}

	private String promptNewName() {
		Stage promptStage = new Stage();
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter new name:");
		TextField textBox = new TextField();
		textBox.setMaxWidth(200);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(textBox);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> {
			promptStage.close();
			newName = "cancel";
		});
		textBox.setOnAction(e -> {
			newName = textBox.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			newName = textBox.getText();
			promptStage.close();
		});
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, 300, 200);
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return newName;
	}

	public Map<String, String> getEntities() {
		return myEntities;
	}

}
