package authoring.frontend.display_elements.tab_displays;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * The TabDisplay superclass acts as a container for it's Grid and corresponding
 * editor.
 * 
 * @author Frank
 *
 */

public abstract class TabDisplay implements ITabDisplay {

	private static final int EDITOR_SCENE_WIDTH = 1200;
	private static final int EDITOR_SCENE_HEIGHT = 800;
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
	
	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) arg;
		myGrid.setAttributesPanel(data);

	}
	
	public Map<String, String> getAttributesMap() {
		return ((TabGrid) myGrid).getAttributesMap();
	}
}
