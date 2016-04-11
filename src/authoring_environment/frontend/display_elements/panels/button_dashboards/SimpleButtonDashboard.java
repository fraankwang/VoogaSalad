package authoring_environment.frontend.display_elements.panels.button_dashboards;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The SimpleButtonDashboard only contains a save and reset button.
 * 
 * @author Frank
 *
 */

public class SimpleButtonDashboard extends ButtonDashboard {

	private Button myResetButton;

	public SimpleButtonDashboard(int height, int width) {
		super(height, width);

	}

	@Override
	protected void initializeComponents() {
		mySaveButton = new Button("Save");
		mySaveButton.setPrefHeight(600);
		mySaveButton.setPrefWidth(600);
		myResetButton = new Button("Reset");
		myResetButton.setPrefHeight(600);
		myResetButton.setPrefWidth(600);

	}

	@Override
	protected void assembleComponents() {
		HBox myButtons = new HBox(mySaveButton, myResetButton);
		myNode = myButtons;
	}

}
