package authoring_environment.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class ModifiableEntityAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableEntityAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();
		myAttributesGridPane.add(new Label("Entity Type"), 0, 0);
		ComboBox cb = new ComboBox();
		cb.getItems().addAll(new Label("Tower"), new Label("Enemy"));
		cb.setPrefWidth(600);
		myAttributesGridPane.add(cb, 1, 0);
		myAttributesGridPane.add(new Label("Name"), 0, 1);
		myAttributesGridPane.add(new Label("Attack"), 0, 2);
		myAttributesGridPane.add(new Label("Attack Speed"), 0, 3);
		myAttributesGridPane.add(new Label("Movement Speed"), 0, 4);
		myAttributesGridPane.add(new Label("Armor"), 0, 5);
		// add more attributes later

	}

}
