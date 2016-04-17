package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.EditorViewPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private HBox myZoomControls;
	private Button myZoomOutButton, myZoomInButton;
	private EditorViewPanel myViewPanel;

	public EditorPanelBar(double height, double width, EditorViewPanel view) {
		super(height, width);
		myViewPanel = view;
	}

	@Override
	protected void initializeComponents() {
		myDescription = new HBox();
		myZoomControls = new HBox();
		myZoomOutButton = new Button("-");
		myZoomInButton = new Button("+");
	}

	@Override
	protected void assembleComponents() {
		HBox hbox = new HBox();
		
		// zooms don't work quite right yet
		//myZoomOutButton.setOnAction(e -> myViewPanel.zoomOut());
		//myZoomInButton.setOnAction(e -> myViewPanel.zoomIn());
		myZoomControls.getChildren().addAll(myZoomOutButton, new Label("Zoom"),  myZoomInButton);
		hbox.getChildren().addAll(myDescription, myZoomControls);
		myNode = hbox;
	}
	
	public void addButton(String label, EventHandler<ActionEvent> action) {
		Button b = new Button(label);
		b.setOnAction(action);
		myDescription.getChildren().add(b);
	}

}
