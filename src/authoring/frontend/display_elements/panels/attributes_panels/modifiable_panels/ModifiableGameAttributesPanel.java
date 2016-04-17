package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableGameAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableGameAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();

	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();
		myAttributesGridPane.add(new Label("Game Type"), 0, 0);
		myAttributesGridPane.add(new Text("Number of Players"), 0, 1);
		myAttributesGridPane.add(new Label("Number of Starting Lives"), 0, 2);
		myAttributesGridPane.add(new Label("Number of Lives For Defeat"), 0, 3);
		myAttributesGridPane.add(new Label("Game Timer"), 0, 4);
		myAttributesGridPane.add(new Label("Starting Resources"), 0, 5);

		List<String> attributeNames = (List<String>) Arrays.asList("Game Type", "Number of Players",
				"Number of Starting Lives", "Number of Lives For Defeat", "Game Timer", "Starting Resources");
		//assembleRows(myAttributesGridPane, attributeNames);
	}

	@Override
	public Map<String,String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Game");
		return myAttributesMap;
	}
}
