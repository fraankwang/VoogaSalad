package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.Panel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * The PanelBar is the object on top of the GridViewPanel or
 * EditorViewPanel. It acts as a mini-controller and display for the panel
 * specifically.
 * 
 * @author Frank
 *
 */

public abstract class PanelBar extends Panel {

	protected HBox myGridBar;
	protected Label myDescription;

	public PanelBar(double height, double width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		myGridBar = new HBox();
		myGridBar.setAlignment(Pos.CENTER_LEFT);
		myGridBar.setSpacing(10);

		myDescription = new Label();
		myDescription.setFont(new Font(25));
	}

	public abstract void setDescription(String description);
	
	public void setFontSize(int font) {
		myDescription.setFont(new Font(font));
	}
	
	public void addButtonToBar(Button b) {
		myGridBar.getChildren().add(b);
	}
	
	
}
