package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.ILevelsTabDisplay;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * The LevelsTabDisplay contains multiple tabs of a set of user-created levels depending on
 * which Mode is selected (sets of levels correspond to each Mode created).
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelsTabDisplay implements ILevelsTabDisplay {

	private TabPane myLevelsTabPane;

	public LevelsTabDisplay() {
		myLevelsTabPane = new TabPane();

	}

	@Override
	public IEditorDisplay getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
