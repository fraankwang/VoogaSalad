package authoring.frontend.display_elements.panels.button_dashboards;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The EditorButtonDashboard only contains a save and reset button.
 * 
 * @author Frank
 *
 */

public class EditorButtonDashboard extends ButtonDashboard {

	private Button myResetButton;
	private Button mySaveButton;

	public EditorButtonDashboard(int height, int width) {
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

	}

	@Override
	protected void assembleComponents() {
		VBox myButtons = new VBox(myResetButton, mySaveButton);
		myNode = myButtons;
	}

	public Button getResetButton() {
		return myResetButton;
	}

	public Button getSaveButton() {
		return mySaveButton;
	}

}
