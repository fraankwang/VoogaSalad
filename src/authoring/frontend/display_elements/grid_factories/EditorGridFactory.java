package authoring.frontend.display_elements.grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

/**
 * The EditorGridFactory superclass is responsible for creating the additional
 * modifiable attributes.
 * 
 * @author Frank
 *
 */

public abstract class EditorGridFactory extends GridFactory {

	public EditorGridFactory(IAuthoringView controller) {
		super(controller);
	}

	/**
	 * @return instantiated and formatted myModifiableAttributesPanel
	 */
	public abstract ModifiableAttributesPanel createModifiableAttributesPanel();
}
