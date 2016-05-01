package authoring.frontend.interfaces.display_element_interfaces;

/**
 * The ITabBarElement allows for the easy creation of additional TabBar types,
 * provided they contain the relevant game components defined as methods in this
 * interface.
 * 
 * @author Frank, benchesnut
 *
 */

public interface ITabBarElement extends IDisplayElement {

	public void show(ITabDisplay display);

	public ITabDisplay getModesTabDisplay();

	public ITabDisplay getLevelsTabDisplay();

	public ITabDisplay getEntitiesTabDisplay();

	/**
	 * After the stage is displayed, this method traces back to each aspect of
	 * the displays to set hotkeys.
	 */
	public void initializeHotKeys();
}
