package authoring.frontend.interfaces.display_element_interfaces;

import java.util.List;
import java.util.Map;

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

	public void updateAll(List<Map<String, String>> modes, List<Map<String, String>> levels,
			List<Map<String, String>> entities);
}
