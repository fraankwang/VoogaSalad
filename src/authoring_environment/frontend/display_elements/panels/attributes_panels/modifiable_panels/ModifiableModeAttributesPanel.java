package authoring_environment.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
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
		myAttributesGridPane.add(new Label("Easy"), 0, 0);
		myAttributesGridPane.add(new Label("Medium"), 0, 1);
		myAttributesGridPane.add(new Label("Hard"), 0, 2);
	}

}
