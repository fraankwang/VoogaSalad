package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class ModifiableEntityAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableEntityAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		assembleInputRows();
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
