package authoring.frontend.display_elements.panels;

import java.util.*;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.editor_features.ObjectChooser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Frank
 *
 */

public class LevelGridViewPanel extends GridViewPanel {

	private Map<String, String> myPossibleLevels;
	private List<String> mySelectedLevels;
	private Button myDeleteButton;
	private ObjectChooser myChooser;

	private String myCurrentImage;

	public LevelGridViewPanel(double height, double width, TabDisplay tabDisplay, IAuthoringView controller) {
		super(height, width, tabDisplay, controller);
		myDeleteButton = new Button("Reset");
		mySelectedLevels = new ArrayList<String>();
		myPossibleLevels = new HashMap<String, String>();
		myChooser = new ObjectChooser();
		myChooser.initialize();
	}

	@Override
	protected void assembleComponents() {
		myAddNewButton.setText("Add New Level to Mode");
		myAddNewButton.setStyle(
				"-fx-wrap-text: true; -fx-background-insets: 0,1,2,3; -fx-background-radius: 3,2,2,2;-fx-padding: 12 30 12 30;-fx-text-fill: white;-fx-font-size: 30px;-fx-background-color:#515D7B,linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);");

		myAddNewButton.setPrefSize(300, 300);
		myAddNewButton.setOnAction(e -> {
			myChooser.clear();
			updatePossibleLevels(myController.getLevels());
			String chosen = myChooser.openChooser();
			mySelectedLevels.add(chosen);

			ImageView iv = new ImageView(new Image(myPossibleLevels.get(chosen)));
			iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
						Boolean newValue) {
					if (newValue) {
						iv.setOpacity(1);
						myCurrentImage = chosen;
						return;
					}
					iv.setOpacity(0.2);
				}
			});
			addImage(iv);
		});

		VBox vbox = new VBox();
		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);

		formatPanelBar();
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
		resetGrid();
	}

	/**
	 * Specifications for a delete button and formatting.
	 */
	private void formatPanelBar() {
		myPanelBar.setDescription("Levels for this Mode");
		myPanelBar.setFontSize(15);

		myDeleteButton = new Button("Delete");
		myDeleteButton.setOnAction(e -> {
			mySelectedLevels.remove(myCurrentImage);
			updateSelectedLevels(mySelectedLevels);
			myCurrentImage = null;
		});

		((GridPanelBar) myPanelBar).addButtonToBar(myDeleteButton);
	}

	/**
	 * Takes in all the names of the levels created by the user mapped to their
	 * image file paths.
	 * 
	 * @param possibleLevels
	 */
	public void updatePossibleLevels(Map<String, String> possibleLevels) {
		myPossibleLevels = possibleLevels;
		myChooser.addAll(possibleLevels);
		System.out.println(possibleLevels);
	}

	/**
	 * Reads update (pre-selected) levels and resets the grid to populate with
	 * the selected levels.
	 * 
	 * @param selectedLevels
	 */
	public void updateSelectedLevels(List<String> selectedLevels) {
		mySelectedLevels = selectedLevels;
		myImages.clear();
		for (String level : mySelectedLevels) {
			ImageView iv = new ImageView(new Image(level));
			myImages.add(iv);
		}
		resetGrid();
	}

	/**
	 * This method is only used for purposes of taking a screenshot to update the image.
	 */
	public void removeAddNewButton() {
		myGridPane.getChildren().remove(myAddNewButton);

	}

	/**
	 * Takes ordered List<String> of selected levels and maps its index to its name.
	 * @return
	 */
	public Map<Integer, String> getSelectedLevels() {
		Map<Integer, String> levelsMap = new HashMap<Integer, String>();
		for (int i = 0; i < mySelectedLevels.size(); i++) {
			levelsMap.put(i, mySelectedLevels.get(i));
		}

		return levelsMap;
	}

}
