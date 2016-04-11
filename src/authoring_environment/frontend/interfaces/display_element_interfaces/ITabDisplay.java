package authoring_environment.frontend.interfaces.display_element_interfaces;

import authoring_environment.frontend.display_elements.editor_displays.EditorDisplay;

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

	public void openEditorDisplay();
}
