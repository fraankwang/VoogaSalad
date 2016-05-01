package authoring.frontend.display_elements.grids;

import java.util.*;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.TabGridFactory;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;

/**
 * The TabGrid superclass is a subclass of Grid, which is in all the
 * TabDisplays. The extra functionality that TabGrids have is the creation of an
 * attributes panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank
 *
 */

public abstract class TabGrid extends Grid {

	protected UnmodifiableAttributesPanel myUnmodifiableAttributesPanel;
	protected TabDisplay myTabDisplay;

	protected Map<String, String> currentInfo;

	public TabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;
		currentInfo = new TreeMap<String, String>();
	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myUnmodifiableAttributesPanel = ((TabGridFactory) myGridFactory)
				.createUnmodifiableAttributesPanel(myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		GridPane.setRowSpan(myPrimaryDisplay.getNode(), 2);
		myGrid.add(myUnmodifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
	}

	public void setAttributesPanel(Map<String, String> info) {
		myUnmodifiableAttributesPanel.setAttributes(info);
	}

	/**
	 * Sets the focused property of the ImageView given. Updates currentInfo as
	 * well as the current LevelGridViewPanel, both of which are utilized when
	 * the Open Editor button is pressed.
	 * 
	 * @param iv
	 * @param info
	 */
	protected abstract void linkImage(ImageView iv, Map<String, String> info);
	
	
	/**
	 * Takes current information exactly, replaces the name, and opens the
	 * editor display with the new name.
	 * 
	 * @param info
	 */
	protected void duplicate(Map<String, String> info) {
		Map<String, String> duplicateEntity = new TreeMap<String, String>();
		for (String s : info.keySet()) {
			duplicateEntity.put(s, info.get(s));
		}

		String newName = promptNewName("duplicate name");
		if (!newName.equals("")) {
			duplicateEntity.replace("Name", newName);
			duplicateEntity.remove("Type");
			myTabDisplay.openEditorDisplay(duplicateEntity);
		}
	}

	/**
	 * Takes current info and sends it to the backend to be deleted. After
	 * deleting, this method clears the current info and updates the attributes
	 * panel.
	 * 
	 * @param info
	 * @param type
	 */
	protected void delete(Map<String, String> info, String type) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete " + type + " Warning");
		alert.setContentText("Are you sure you want to delete this " + type);

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

	public Map<String, String> getAttributesMap() {
		return myUnmodifiableAttributesPanel.getAttributesMap();
	}

	public List<String> getDefaultAttributes() {
		return myUnmodifiableAttributesPanel.getDefaultAttributes();
	}

	/**
	 * Assigns hotkeys for the different buttons.
	 */
	public void initializeHotKeys() {
		Button editorButton = myUnmodifiableAttributesPanel.getEditorButton();
		Button duplicateButton = ((MainButtonDashboard) myButtonDashboard).getDuplicateButton();
		Button deleteButton = ((MainButtonDashboard) myButtonDashboard).getDeleteButton();
		Button addNewButton = ((GridViewPanel) myPrimaryDisplay).getMyAddNewButton();

		editorButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						editorButton.fire();
					}
				});

		duplicateButton.getScene().getAccelerators()
				.put(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN), new Runnable() {
					@Override
					public void run() {
						duplicateButton.fire();
					}
				});

		deleteButton.getScene().getAccelerators()
				.put(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.SHORTCUT_DOWN), new Runnable() {
					@Override
					public void run() {
						deleteButton.fire();
					}
				});

		addNewButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						addNewButton.fire();
					}
				});
	}

}
