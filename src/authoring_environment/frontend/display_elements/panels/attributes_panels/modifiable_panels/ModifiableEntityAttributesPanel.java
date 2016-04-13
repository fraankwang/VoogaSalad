package authoring_environment.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

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
		ComboBox cb = new ComboBox();
		cb.getItems().addAll(new Label("Tower"), new Label("Enemy"));
		cb.setPrefWidth(600);
		myAttributesGridPane.add(cb, 1, 0);
		myAttributesGridPane.add(new Label("Entity Type"), 0, 0); //
		myAttributesGridPane.add(new Label("Name"), 0, 1);
		myAttributesGridPane.add(new Label("Attack Damage Value"), 0, 2);
		myAttributesGridPane.add(new Label("Attack Rate"), 0, 3);
		myAttributesGridPane.add(new Label("Movement Speed"), 0, 4);
		myAttributesGridPane.add(new Label("Armor"), 0, 5);
		myAttributesGridPane.add(new Label("Health"), 0, 6);
		myAttributesGridPane.add(new Label("Rotate Speed"), 0, 7);
		myAttributesGridPane.add(new Label("Cost"), 0, 8);
		myAttributesGridPane.add(new Label("Collidable"), 0, 9); //true false
		myAttributesGridPane.add(new Label("Moves on Path"), 0, 10); //true false
		myAttributesGridPane.add(new Label("Path name"), 0, 11); //true false
		myAttributesGridPane.add(new Label("Random movement"), 0, 12); //true false
		
		// add more attributes later

	}

}
