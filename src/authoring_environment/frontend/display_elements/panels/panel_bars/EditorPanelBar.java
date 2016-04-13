package authoring_environment.frontend.display_elements.panels.panel_bars;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The EditorPanelBar contains a button to upload new image, a
 * description/stats, and the functionality to zoom in or out of the image.
 * 
 * @author Frank
 *
 */

public class EditorPanelBar extends PanelBar {

	private HBox myDescription;
	private Button myUploadImageButton;
	private HBox myZoomControls;
	private Button myZoomOutButton, myZoomInButton;

	public EditorPanelBar(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		myDescription = new HBox();
		myUploadImageButton = new Button();
		myZoomControls = new HBox();
		myZoomOutButton = new Button("-");
		myZoomInButton = new Button("+");
	}

	@Override
	protected void assembleComponents() {
		HBox hbox = new HBox();
		//myDescription.getChildren().add(myUploadImageButton);
		myZoomControls.getChildren().addAll(myZoomOutButton, myZoomInButton);
		hbox.getChildren().addAll(myDescription, myZoomControls);
		myNode = hbox;
	}
	
	public void addButton(String label, EventHandler<ActionEvent> action) {
		Button b = new Button(label);
		b.setOnAction(action);
		myDescription.getChildren().add(b);
	}

}
