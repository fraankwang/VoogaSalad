package authoring_environment.frontend.display_elements.panels.button_dashboards;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The StandardButtonDashboard contains a duplicate and delete option in
 * addition to the save and reset changes option.
 * 
 * @author Frank
 *
 */

public class StandardButtonDashboard extends SimpleButtonDashboard {

	private Button myDuplicateButton;
	private Button myDeleteButton;

	public StandardButtonDashboard(int height, int width) {
		super(height, width);

	}

	@Override
	protected void initializeComponents() {
		mySaveButton = new Button("Save");
		mySaveButton.setPrefHeight(myHeight);
		mySaveButton.setPrefWidth(myWidth);
		myResetButton = new Button("Reset");
		myResetButton.setPrefHeight(myHeight);
		myResetButton.setPrefWidth(myWidth);
//		super.initializeComponents();
		myDuplicateButton = new Button("Duplicate");
		myDuplicateButton.setPrefHeight(myHeight);
		myDuplicateButton.setPrefWidth(myWidth);
		myDeleteButton = new Button("Delete");
		myDeleteButton.setPrefHeight(myHeight);
		myDeleteButton.setPrefWidth(myWidth);
	}

	@Override
	protected void assembleComponents() {
		VBox leftButtons = new VBox(myDuplicateButton, myResetButton);
		VBox rightButtons = new VBox(myDeleteButton, mySaveButton);
		HBox myButtons = new HBox(leftButtons, rightButtons);
		myNode = myButtons;

	}

}
