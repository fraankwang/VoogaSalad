package authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces;

import authoring_environment.frontend.interfaces.IDisplayEntity;
import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;

/**
 * This interface handles the editing of various entities and their
 * components.
 * @author benchesnut, Frank
 *
 */

public interface IEditorDisplay extends IDisplayElement {

	/**
	 * Takes in as a parameter an entity and returns the (possibly)
	 * updated version of that entity.
	 * @param oldEntity
	 * @return newEntity
	 */
	IDisplayEntity edit(IDisplayEntity oldEntity);
}
