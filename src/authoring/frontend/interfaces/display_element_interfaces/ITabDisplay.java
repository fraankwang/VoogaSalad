package authoring.frontend.interfaces.display_element_interfaces;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import javafx.scene.image.ImageView;

/**
 * TabDisplays are the primary game components that the user switches between.
 * The various Displays contain information regarding the user-created game
 * options, as well as an option for the user to access the game component's
 * editor.
 * 
 * @author benchesnut
 *
 */

public interface ITabDisplay extends IDisplayElement, Observer {

	public String getName();
	
	public int getTabIndex();

	void openEditorDisplay(List<Map<String, String>> info);
	
}
