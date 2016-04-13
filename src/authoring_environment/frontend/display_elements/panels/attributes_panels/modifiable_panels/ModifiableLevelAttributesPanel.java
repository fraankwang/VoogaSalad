package authoring_environment.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.Label;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableLevelAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableLevelAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();

	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();
		myAttributesGridPane.add(new Label("Waves"), 0, 0);
		myAttributesGridPane.add(new Label("Timer"), 0, 1);

	}

}
