package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.ComponentSelector;

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
		assembleInputRows();
	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

	}

	@Override
	public void setAttributes(Map<String, String> info) {
		super.setAttributes(info);
		ComponentSelector selector = new ComponentSelector();
		selector.initialize();
		myInputMap = selector.setComponents(myInputMap, myAttributes);
		System.out.println(
				"*****2. ModifiableAttrPanel: myAttributesMap set with given unmodifiableattributespanel outputs:");
		System.out.println(myAttributesMap);
		refreshAttributes();
		
	}
	
	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Entity");
		return myAttributesMap;
	}

}
