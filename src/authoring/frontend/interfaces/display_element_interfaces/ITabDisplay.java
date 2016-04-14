package authoring.frontend.interfaces.display_element_interfaces;

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

	public String getName();
	
	public int getTabIndex();
	
}
