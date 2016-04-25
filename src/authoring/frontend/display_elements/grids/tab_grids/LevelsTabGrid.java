package authoring.frontend.display_elements.grids.tab_grids;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.LevelsTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGrid extends TabGrid {

	private Map<String, String> currentInfo = new TreeMap<String, String>();
	private String newName;
	private List<String> myLevels;

	public LevelsTabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
		myLevels = new ArrayList<String>();
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new LevelsTabGridFactory(myController, myTabDisplay);
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

	public void update(List<Map<String, String>> data) {
		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();

		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {
			info.remove("EntityNames");
			info.remove("Path");

			ImageView iv = new ImageView(info.get("MapBackgroundImage"));
			iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
						Boolean newValue) {
					if (newValue) {
						setAttributesPanel(info);
						currentInfo = info;
					}
				}
			});
			gridView.addImage(iv);
		}

	}

	private void duplicate(Map<String, String> info) {
		Map<String, String> duplicateEntity = new TreeMap<String, String>();
		for (String s : info.keySet()) {
			duplicateEntity.put(s, info.get(s));
		}

		String newName = promptNewName();
		duplicateEntity.replace("Name", newName);
		myTabDisplay.openEditorDisplay(duplicateEntity);
	}

	private void delete(Map<String, String> info) {
		myController.deleteData(info);
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
		cancelButton.setOnAction(e -> promptStage.close());
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

	public List<String> getLevels() {
		return myLevels;
	}
}
