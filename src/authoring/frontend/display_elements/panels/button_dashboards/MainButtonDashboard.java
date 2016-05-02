package authoring.frontend.display_elements.panels.button_dashboards;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The MainButtonDashboard contains a duplicate and delete option in addition to
 * the save and reset changes option.
 * 
 * @author Frank
 *
 */

public class MainButtonDashboard extends ButtonDashboard {

	private Button myDuplicateButton;
	private Button myDeleteButton;

	public MainButtonDashboard(int height, int width) {
		super(height, width);

	}

	@Override
	protected void initializeComponents() {
		myDuplicateButton = new Button("Duplicate");
		myDuplicateButton.setPrefHeight(myHeight);
		myDuplicateButton.setPrefWidth(myWidth);
		myDeleteButton = new Button("Delete");
		myDeleteButton.setPrefHeight(myHeight);
		myDeleteButton.setPrefWidth(myWidth);

	}

	@Override
	protected void assembleComponents() {
		HBox myButtons = new HBox(myDuplicateButton, myDeleteButton);
		myNode = myButtons;

	}

	public Button getDeleteButton() {
		return myDeleteButton;
	}

	public Button getDuplicateButton() {
		return myDuplicateButton;
	}

}
