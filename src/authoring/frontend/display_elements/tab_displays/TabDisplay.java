package authoring.frontend.display_elements.tab_displays;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * The TabDisplay superclass acts as a container for it's Grid and corresponding
 * editor. TabDisplays implement the Observer interface, thus each TabDisplay
 * contains an update method. The GameTabDisplay does not require this as there
 * is only the name information to keep track of. Each TabDisplay also contains
 * access to its corresponding EditorDisplay, which is opened in by it's Grid
 * requiring the EditorDisplay to be opened with pre-populated data (i.e. when
 * duplicating something).
 * 
 * @author Frank
 *
 */

public abstract class TabDisplay implements ITabDisplay {

	protected EditorDisplay myEditorDisplay;
	protected Grid myGrid;
	protected IAuthoringView myController;
	private int myTabIndex;

	public TabDisplay(int index, IAuthoringView controller) {
		myTabIndex = index;
		myController = controller;
	}

	@Override
	public Node getNode() {
		return myGrid.getNode();
	}

	@Override
	public void openEditorDisplay(Map<String, String> info) {
		myEditorDisplay.edit(info);
	}

	public int getTabIndex() {
		return myTabIndex;
	}

	/**
	 * Default attributes are defined by each TabDisplay upon
	 * UnmodifiableAttributesPanel instantiation. An empty map with keys set as
	 * these attributes are necessary to set the action for the Add New button
	 * within the GridViewPanel, which shouldn't have access to the
	 * UnmodifiableAttributesPanels, thus it must access it through this method.
	 * 
	 * @return
	 */
	public abstract Map<String, String> getDefaultAttributesMap();

	/**
	 * This method must be abstracted so that both the EditorGrid and TabGrid
	 * may define their own hot keys. EditorGrid has hot keys for saving and
	 * resetting, while TabGrid has hot keys to duplicate and delete.
	 */
	public abstract void initializeHotKeys();

	public EditorDisplay getEditor() {
		return myEditorDisplay;
	}
}
