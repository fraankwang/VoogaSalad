package authoring.frontend.display_elements.tab_displays;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	public void openEditorDisplay(ImageView image, Map<String, String> info) {
		myEditorDisplay.edit(image, info);
	}

	public int getTabIndex() {
		return myTabIndex;
	}
}
