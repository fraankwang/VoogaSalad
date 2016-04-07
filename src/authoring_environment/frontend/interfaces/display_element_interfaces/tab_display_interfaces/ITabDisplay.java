package authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;

/**
 * 
 * @author benchesnut
 *
 */

public interface ITabDisplay extends IDisplayElement {

	public IEditorDisplay getEditor();
}
