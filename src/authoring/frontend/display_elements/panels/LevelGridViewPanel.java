package authoring.frontend.display_elements.panels;

import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.editor_features.ObjectChooser;
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
	private Button myResetButton;
	
	public LevelGridViewPanel(double height, double width, TabDisplay tabDisplay) {
		super(height, width, tabDisplay);
		myResetButton = new Button("Reset");
	}
	
	@Override
	protected void assembleComponents() {
		myAddNewButton.setStyle(
				"-fx-wrap-text: true; -fx-background-insets: 0,1,2,3; -fx-background-radius: 3,2,2,2;-fx-padding: 12 30 12 30;-fx-text-fill: white;-fx-font-size: 10px;-fx-background-color:#515D7B,linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);");

		myAddNewButton.setPrefSize(80, 80);

		VBox vbox = new VBox();
		
		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);
		((GridPanelBar) myPanelBar).setDescription("Levels");
		((GridPanelBar) myPanelBar).setFontSize(9);
		myResetButton = new Button("Reset");
		myResetButton.setOnAction(e -> {
			myImages.clear();
			mySelectedLevels.clear();
			resetGrid();
		});
		((GridPanelBar) myPanelBar).addButton(myResetButton);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
		resetGrid();
	}

	public void updatePossibleLevels(Map<String, String> possibleLevels) {
		myPossibleLevels = possibleLevels;
		updateAddNewButton(myPossibleLevels);
	}

	public void updateSelectedLevels(List<String> selectedLevels) {
		mySelectedLevels = selectedLevels;
		myImages.clear();
		for (String level : mySelectedLevels) {
			ImageView iv = new ImageView(new Image(level));
			myImages.add(iv);
		}
		resetGrid();
	}

	private void updateAddNewButton(Map<String, String> possibleItems) {
		myAddNewButton.setOnAction(e -> {
			ObjectChooser chooser = new ObjectChooser();
			chooser.initialize();
			chooser.updateList(possibleItems);

			String chosen = chooser.openChooser();
			
			ImageView iv = new ImageView(new Image(possibleItems.get(chosen)));
			addImage(iv);
		});
		
	}
}
