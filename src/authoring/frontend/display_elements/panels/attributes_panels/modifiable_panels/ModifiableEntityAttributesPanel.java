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
		myAttributes = (List<String>) Arrays.asList("Genre", "Name", "DamageComponent", "FiringComponent",
				"MovementComponent", "Armor", "HealthComponent", "RotationComponent", "Cost", "Bounty",
				"CollisionComponent", "Random Movement");
		assembleRows();
	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

	}

	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Entity");
		return myAttributesMap;
	}

}
