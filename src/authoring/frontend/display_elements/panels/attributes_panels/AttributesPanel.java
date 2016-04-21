package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.*;

import authoring.frontend.display_elements.panels.Panel;

/**
 * All attribute panels have a map of attribute names to values, as well as a
 * list of attributes names.
 * 
 * @author Frank
 *
 */

public abstract class AttributesPanel extends Panel {

	protected static final int FONT_SIZE = 14;
	protected List<String> myAttributes;
	protected Map<String, String> myAttributesMap;

	public AttributesPanel(int height, int width) {
		super(height, width);
	}

}
