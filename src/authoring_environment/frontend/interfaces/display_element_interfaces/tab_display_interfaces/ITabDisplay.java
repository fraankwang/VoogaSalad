package authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;

/**
 * TabDisplays are the primary game components that the user switches between.
 * The various Displays contain information regarding the user-created game
 * options, as well as an option for the user to access the game component's
 * editor.
 * 
 * @author benchesnut
 *
 */

public interface ITabDisplay extends IDisplayElement {

	public IEditorDisplay getEditor();
}
