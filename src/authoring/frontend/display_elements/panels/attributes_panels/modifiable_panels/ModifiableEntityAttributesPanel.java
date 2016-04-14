package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class ModifiableEntityAttributesPanel extends ModifiableAttributesPanel {

	private static final int FONT_SIZE = 21;
	private static final int RIGHT_COLUMN_WIDTH = 120;

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

		List<String> attributeNames = (List<String>) Arrays.asList("Genre", "Name", "Damage Value", "Attack Rate",
				"Movement Speed", "Armor", "Health", "Rotate Speed", "Cost", "Bounty", "Collidable", "Moves On Path",
				"Path Name", "Random Movement");

		assembleRows(myAttributesGridPane, attributeNames);

	}

	private void assembleRows(GridPane gridPane, List<String> attributes) {
		myMap = new HashMap<String, Control>();

		for (int i = 0; i < attributes.size(); i++) {
			Text text = new Text(attributes.get(i));
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setEditable(true);
//			tf.setMaxWidth(RIGHT_COLUMN_WIDTH);
			gridPane.add(text, 0, i);
			gridPane.add(tf, 1, i);
			myMap.put(attributes.get(i), tf);
		}

	}

	
	
	

}
