package authoring.frontend.display_elements.grid_factories;

import authoring.controller.IController;
import authoring.frontend.display_elements.panels.Panel;

/**
 * The EditorGridFactory superclass is responsible for creating the additional
 * modifiable attributes and Rules Panels UI elements.
 * 
 * 
 * @author Frank
 *
 */

public abstract class EditorGridFactory extends GridFactory {

	public EditorGridFactory(IController controller) {
		super(controller);
	}

	/**
	 * @return instantiated and formatted myRulesPanel
	 */
	public abstract Panel createRulesPanel();

	/**
	 * @return instantiated and formatted myModifiableAttributesPanel
	 */
	public abstract Panel createModifiableAttributesPanel();
}
