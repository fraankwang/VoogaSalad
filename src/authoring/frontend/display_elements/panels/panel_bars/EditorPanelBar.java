package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.EditorViewPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * The EditorPanelBar contains a button to upload new image, a
 * description/stats, and the functionality to zoom in or out of the image.
 * 
 * @author Frank
 *
 */

public class EditorPanelBar extends PanelBar {

	public EditorPanelBar(double height, double width, EditorViewPanel view) {
		super(height, width);
	}

	@Override
	protected void assembleComponents() {
		myGridBar.getChildren().add(myDescription);
		myNode = myGridBar;
	}

	@Override
	public Button addButton(String label, EventHandler<ActionEvent> action) {
		Button b = new Button(label);
		b.setOnAction(action);
		b.setFont(new Font(10));
		myGridBar.getChildren().add(b);
		return b;
	}

	public void removeButtons(int from, int to) {
		myGridBar.getChildren().remove(from, to);
	}

	@Override
	public void setDescription(String description) {
		myDescription.setText("You are currently editing your " + description);

	}

}
