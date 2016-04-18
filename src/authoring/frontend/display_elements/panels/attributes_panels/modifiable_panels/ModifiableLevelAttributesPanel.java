package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

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
		myAttributes = (List<String>) Arrays.asList("Waves", "Delay Between Waves", "Timer");
		super.assembleComponents();

	}

	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Level");
		return myAttributesMap;
	}

}
