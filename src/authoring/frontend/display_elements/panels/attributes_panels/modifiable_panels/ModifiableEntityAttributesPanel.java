package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

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
		List<String> attributeNames = (List<String>) Arrays.asList("Genre", "Name", "Damage Value", "Attack Rate",
				"Movement Speed", "Armor", "Health", "Rotate Speed", "Cost", "Bounty", "Collidable", "Moves On Path",
				"Path Name", "Random Movement");

		assembleRows(myAttributesGridPane, attributeNames);

	}

	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Entity");
		return myAttributesMap;
	}

}
