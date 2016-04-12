package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.editor_displays.EditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	protected IController myController;

	public TabDisplay(IController controller) {
		myController = controller;
	}

	@Override
	public Node buildNode() {
		initialize();
		return myGrid.buildNode();
	}

	@Override
	public void openEditorDisplay() {
		Stage editorStage = new Stage();
		BorderPane root = new BorderPane();
		root.setCenter(myEditorDisplay.buildNode());
		Scene editorScene = new Scene(root, EDITOR_SCENE_WIDTH, EDITOR_SCENE_HEIGHT, Color.WHITE);
		editorStage.setScene(editorScene);
		editorStage.show();
	}

}
