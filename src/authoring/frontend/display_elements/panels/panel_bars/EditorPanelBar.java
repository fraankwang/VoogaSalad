package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.EditorViewPanel;
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

	public EditorPanelBar(double height, double width, EditorViewPanel view) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		myDescription = new HBox();
	}

	@Override
	protected void assembleComponents() {
		HBox hbox = new HBox();
		hbox.getChildren().add(myDescription);
		myNode = hbox;
	}
	
	@Override
	public Button addButton(String label, EventHandler<ActionEvent> action) {
		Button b = new Button(label);
		b.setOnAction(action);
		myDescription.getChildren().add(b);
		return b;
	}
	
	public void removeButtons(int from, int to) {
		myDescription.getChildren().remove(from, to);
	}

}
