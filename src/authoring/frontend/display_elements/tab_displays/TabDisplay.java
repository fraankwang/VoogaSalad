package authoring.frontend.display_elements.tab_displays;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * The TabDisplay superclass acts as a container for it's Grid and corresponding
 * editor. TabDisplays implement the Observer interface, thus each TabDisplay contains an update method.
 * 
 * @author Frank
 *
 */

public abstract class TabDisplay implements ITabDisplay {

//	private static final int EDITOR_SCENE_WIDTH = 1200;
//	private static final int EDITOR_SCENE_HEIGHT = 800;
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
		System.out.println("*****2. TabDisplay: Editor opened with default attribute data:");
		System.out.println(info);
		myEditorDisplay.edit(info);
	}

	public int getTabIndex() {
		return myTabIndex;
	}
	
	/**
	 * Default attributes are defined by each TabDisplay.
	 * @return
	 */
	public abstract Map<String, String> getDefaultAttributesMap();

	public abstract void initializeHotKeys();

	public EditorDisplay getEditor() {
		return myEditorDisplay;
	}
}
