package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableModeAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();

	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();
		myAttributesGridPane.add(new Label("Mode"), 0, 0);
		ComboBox cb = new ComboBox();
		cb.getItems().addAll(new Text("Easy"), new Text("Medium"), new Text("Hard"));
		cb.setPrefWidth(600);
		myAttributesGridPane.add(cb, 1, 0);
	}

}
