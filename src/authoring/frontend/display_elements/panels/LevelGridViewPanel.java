package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		mySelectedLevels.add("level1");
		mySelectedLevels.add("level2");
		mySelectedLevels.add("level3");
		mySelectedLevels.add("level4");
		mySelectedLevels.add("level5");
		myPossibleLevels = new HashMap<String, String>();
		myChooser = new ObjectChooser();
		myChooser.initialize();
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
	}
	
	@Override
	protected void assembleComponents() {
		myAddNewButton = new Button("Add New Level to Mode");
		myAddNewButton.setStyle(
				"-fx-wrap-text: true; -fx-background-insets: 0,1,2,3; -fx-background-radius: 3,2,2,2;-fx-padding: 12 30 12 30;-fx-text-fill: white;-fx-font-size: 30px;-fx-background-color:#515D7B,linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);");

		myAddNewButton.setPrefSize(300, 300);
		myAddNewButton.setOnAction(e -> {
			myChooser.clear();
			updatePossibleLevels(myController.getLevels());
			String chosen = myChooser.openChooser();
			mySelectedLevels.add(chosen);
			System.out.println("*******just added level: " + chosen);
			System.out.println("*******mySelectedLevels updated to: " + mySelectedLevels);
			ImageView iv = new ImageView(new Image(myPossibleLevels.get(chosen)));
			myCurrentImage = chosen;
			addImage(iv);
		});

		VBox vbox = new VBox();
		myImages = new ArrayList<ImageView>();
		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);

		myPanelBar.setDescription("Levels for this Mode");
		myPanelBar.setFontSize(15);
		
		myDeleteButton = new Button("Delete");
		formatDeleteButton();

		((GridPanelBar) myPanelBar).addButtonToBar(myDeleteButton);

		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
		resetGrid();
	}

	/**
	 * Specifications for a delete button and formatting.
	 */
	private void formatDeleteButton() {
		// TODO: figure out deletion
		myDeleteButton.setOnAction(e -> {
			mySelectedLevels.remove(myCurrentImage);
			updateSelectedLevels(mySelectedLevels);
			myImages.remove(myCurrImage);
			resetGrid();
		});

	}

	/**
	 * Takes in all the names of the levels created by the user mapped to their
	 * image file paths.
	 * 
	 * @param possibleLevels
	 */
	public void updatePossibleLevels(Map<String, String> possibleLevels) {
		myPossibleLevels = possibleLevels;
		myChooser.updateList(possibleLevels);
	}

	/**
	 * Reads update (pre-selected) levels and resets the grid to populate with
	 * the selected levels.
	 * 
	 * @param selectedLevels
	 */
	public void updateSelectedLevels(List<String> selectedLevels) {
		System.out.println("******* mySelectedLevels: " + mySelectedLevels);
		myImages.clear();
		myPossibleLevels = myController.getLevels();
		for (String level : selectedLevels) {
			ImageView iv = new ImageView(new Image(myPossibleLevels.get(level)));
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
		System.out.println("*******Selected levels: " + mySelectedLevels);
		Map<Integer, String> levelsMap = new HashMap<Integer, String>();
		for (int i = 0; i < mySelectedLevels.size(); i++) {
			levelsMap.put(i, mySelectedLevels.get(i));
		}
		return levelsMap;
	}

}
